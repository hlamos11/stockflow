package com.wifi.app.controllers;


import com.wifi.app.entity.TypeUser;
import com.wifi.app.entity.User;
import com.wifi.app.entity.UserHist;
import com.wifi.app.objects.AuthorityDTO;
import com.wifi.app.objects.ChangepasswordDTO;
import com.wifi.app.objects.UserDTO;
import com.wifi.app.repository.TipeUserRepository;
import com.wifi.app.repository.UserRepository;
import com.wifi.app.service.AuthorityService;
import com.wifi.app.service.UserHistService;
import com.wifi.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static com.wifi.app.controllers.HomeController.GLOBAL_USER_NAME;
import static com.wifi.app.res.NewDateExpired.NewDate;

@Controller
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    private  final UserService userService;
    private  final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TipeUserRepository typeUserRepository;
    private final AuthorityService authorityService;
    private final UserHistService userHistService;




    @Autowired
    public AccountController(UserService userService, BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, TipeUserRepository typeUserRepository, AuthorityService authorityService, UserHistService userHistService){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.typeUserRepository = typeUserRepository;
        this.authorityService = authorityService;
        this.userHistService = userHistService;
    }

    @GetMapping("/users")
    public  String list(Model model){
        List<User> user = userService.getUsertList();
        model.addAttribute("usersall", user);
        return "users";
    }

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }


    @GetMapping("/register")
    public String register(@ModelAttribute UserDTO userDTO, Model model){

        List<TypeUser> typeuser = typeUserRepository.findAll();
        model.addAttribute( "typeuser", typeuser);
        model.addAttribute( "userDTO", userDTO);
        return "register";
    }

    @GetMapping("/changepassword")
    public String changepassword(@ModelAttribute ChangepasswordDTO changepasswordDTO, Model model){
        model.addAttribute( "changepasswordDTO", changepasswordDTO);
        return "changepassword";
    }


    @PostMapping("/register")
    public String save(@Validated UserDTO userDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        //log.info(">> SqlDate : {}", SqlDate)
        List<TypeUser> typeuser = typeUserRepository.findAll();

        Date SqlDate = NewDate(userDTO.getTypeuser());
        userDTO.setEnabled(true);
        userDTO.setDate_expired(SqlDate);

        //1.1 valida si el usuario existe
        if(userService.userExists(userDTO.getUsername())){
            bindingResult.addError(new FieldError("userDTO", "username",
                    "El usuario ya esta registrado"));
        }

        //1.1 Valida si las contraseñas son iguales
        if(userDTO.getPassword() != null && userDTO.getRpassword() != null){
            if(!userDTO.getPassword().equals(userDTO.getRpassword())){
                bindingResult.addError(new FieldError( "userDTO", "rpassword",
                        "La contraseña no coincide"));
            }
        }

        if(bindingResult.hasErrors()){
            model.addAttribute( "typeuser", typeuser);
            return "register";
        }

        //Mensaje de usuario Registrado
        redirectAttributes.addFlashAttribute("message", "Usuario Registrado");
        //log.info(">> userDTO : {}", userDTO.toString());
        userService.register(userDTO);
        Optional<User> user = userRepository.findUserByUsername(userDTO.getUsername());
        AuthorityDTO authorityDTO = new AuthorityDTO();

        authorityDTO.setId(user.get().getId());
        authorityDTO.setAuthority(user.get().getTypeuser());

        authorityService.register(authorityDTO);

        return  "redirect:/register";
    }


    @PostMapping("/changepassword")
    public String changepassword(@Validated ChangepasswordDTO changepasswordDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Authentication authentication){


        Optional<User> user = userRepository.findUserByUsername(authentication.getName());

        User user1 = userRepository.findUserById(user.get().getId());

        UserHist userHist = new UserHist();

        boolean isPasswordMatch = passwordEncoder.matches(changepasswordDTO.getPassword(), user1.getPassword());

        if (isPasswordMatch == false) {
            bindingResult.addError(new FieldError( "changepasswordDTO", "password",
                    "La contraseña no coincide"));
        }else{
            if(changepasswordDTO.getNpassword() != null && changepasswordDTO.getRpassword() != null){
                if(!changepasswordDTO.getNpassword().equals(changepasswordDTO.getRpassword())){
                    bindingResult.addError(new FieldError( "changepasswordDTO", "rpassword",
                            "Las contraseñas no coinciden"));
                }
            }
        }

        List<UserHist> userHistList = userHistService.getUserHistList(authentication.getName());

        int cont_pass = 0;

        for(UserHist str : userHistList)
        {
            boolean isPasswordMatch1 = passwordEncoder.matches(changepasswordDTO.getPassword(), str.getPassword());
            if(isPasswordMatch1){
                cont_pass = cont_pass +1;
            }
        }

        if(cont_pass >= 1){
            bindingResult.addError(new FieldError( "changepasswordDTO", "rpassword",
                    "Debe usar una contraseña diferente a las ultimas 10"));
        }

        if(bindingResult.hasErrors()){
            return "changepassword";
        }

        redirectAttributes.addFlashAttribute("message", "La contraseña se actualizo correctamente");
        //log.info(">> changepasswordDTO : {}", changepasswordDTO.toString());

        userHist.setUsername(user1.getUsername());
        userHist.setPassword(user1.getPassword());
        userHistService.save(userHist);

        String password = passwordEncoder.encode(changepasswordDTO.getNpassword());

        Date SqlDate = NewDate(user1.getTypeuser());
        user1.setPassword(password);
        user1.setDate_expired(SqlDate);

        userService.changepassword(user1);

        authentication.setAuthenticated(false);
        SecurityContextHolder.getContext().setAuthentication(null);


        return  "redirect:/authenticated";

    }

}
