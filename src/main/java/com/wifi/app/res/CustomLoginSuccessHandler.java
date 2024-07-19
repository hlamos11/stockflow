package com.wifi.app.res;



import com.wifi.app.entity.User;
import com.wifi.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        AppUsersDetails userDetails = (AppUsersDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        log.info(">>CustomLoginSuccessHandler ******************************************************** Desbloquear Usuario - user.getFail_attempt() : {}", user.getFail_attempt());

        if (user.getFail_attempt() > 0) {
            userService.resetFailLockTime(user.getUsername());
        }


        super.setDefaultTargetUrl("/authenticated");


        super.onAuthenticationSuccess(request, response, authentication);
    }
}
