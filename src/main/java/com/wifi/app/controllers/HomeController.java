package com.wifi.app.controllers;






import com.wifi.app.entity.InventoryMaterial;
import com.wifi.app.entity.Store;
import com.wifi.app.entity.User;
import com.wifi.app.repository.UserRepository;
import com.wifi.app.service.InventoryMaterialService;
import com.wifi.app.service.MaterialService;
import com.wifi.app.service.QueryService;
import com.wifi.app.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static com.wifi.app.res.ConvertDateSql.convertDateString;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private final UserRepository userRepository;
    private final InventoryMaterialService inventoryMaterialService;
    private final MaterialService materialService;
    private final StoreService storeService;

    private final QueryService queryservice;
    public static String GLOBAL_USER_NAME = null;

    @GetMapping("/")
    public String index(Authentication authentication){
//        return authentication == null ? "index" : "redirect:/authenticated";
        return authentication == null ? "/login" : "redirect:/authenticated";
    }

    @GetMapping("/authenticated")
    public String authenticated(Authentication authentication,Model model) throws ParseException {

        model.addAttribute("liCountFWA", materialService.getMaterialCountByInventoryMaterialId(1));
        model.addAttribute("liCountWiFi", materialService.getMaterialCountByInventoryMaterialId(2));
        model.addAttribute("liCountRAN", materialService.getMaterialCountByInventoryMaterialId(3));
        model.addAttribute("liCountCellFi", materialService.getMaterialCountByInventoryMaterialId(4));

        String res = validatePass();

        return res;

    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        return authentication == null ? "login" : "redirect:/authenticated";

    }


    @RequestMapping("/chartMaterial")
    @ResponseBody
    public String getDataMaterialAll(){

        JSONObject jsonObject = new JSONObject ();

        Integer liCountFWAEnabled = materialService.getMaterialCountByEnabledAndInventoryMaterialId(true, 1);
        Integer liCountWiFiEnabled = materialService.getMaterialCountByEnabledAndInventoryMaterialId(true,2);
        Integer liCountRANEnabled = materialService.getMaterialCountByEnabledAndInventoryMaterialId(true,3);
        Integer liCountCellFiEnabled = materialService.getMaterialCountByEnabledAndInventoryMaterialId(true,4);

        Integer liCountFWAFalse = materialService.getMaterialCountByEnabledAndInventoryMaterialId(false,1);
        Integer liCountWiFiFalse = materialService.getMaterialCountByEnabledAndInventoryMaterialId(false,2);
        Integer liCountRANFalse = materialService.getMaterialCountByEnabledAndInventoryMaterialId(false,3);
        Integer liCountCellFiFalse = materialService.getMaterialCountByEnabledAndInventoryMaterialId(false,4);

        jsonObject.put("tFWA", liCountFWAEnabled);
        jsonObject.put("tWiFi", liCountWiFiEnabled);
        jsonObject.put("tRAN", liCountRANEnabled);
        jsonObject.put("tCELLFI", liCountCellFiEnabled);

        jsonObject.put("fFWA", liCountFWAFalse);
        jsonObject.put("fWiFi", liCountWiFiFalse);
        jsonObject.put("fRAN", liCountRANFalse);
        jsonObject.put("fCELLFI", liCountCellFiFalse);

        //log.info(">> jsonObject.toString()********************** : {}",jsonObject.toString());

        return jsonObject.toString();

    }

    @RequestMapping("/chartMaterialByStoreAndInventoryId")
    @ResponseBody
    public String getDataMaterialByStore(){

        List<InventoryMaterial> inventoryMaterialList = inventoryMaterialService.getListInventoryMaterial();
        List<Store> storeList = storeService.getStoreList();
        List<BigInteger> list = new ArrayList<>();

        JSONArray jsonArrayCount = new JSONArray();

        JSONObject jsonObject = new JSONObject ();

        for(Store stores : storeList){
            //System.out.println("Bodega id: "+stores.getId() + " Bodega nombre: " +  stores.getName());
            if(stores.getId() != 8){
                for(InventoryMaterial inventory : inventoryMaterialList){
                    BigInteger count = queryservice.JPQLQueryChartMaterialByStoreAndInventory(stores.getId(), inventory.getId());
                    //System.out.println("inventario id: "+inventory.getId() + " inventario nombre: " +  inventory.getName() + " Count:"+ count);
                    list.add(count);
                }
            }
        }

        jsonArrayCount.put(list);
        jsonObject.put("object", jsonArrayCount);

        log.info(">> jsonObject.toString()********************** : {}",jsonObject.toString());

        return jsonObject.toString();

    }


    private String validatePass (){
        Date date_expired, date_local;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            date_local = convertDateString(dtf.format(LocalDateTime.now()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Optional<User> user = userRepository.findUserByUsername(GLOBAL_USER_NAME);
        date_expired = user.get().getDate_expired();

        if(date_local.after(date_expired) ){
            return "redirect:/changepassword";
        }else {
            return "authenticated";
        }
    }


}
