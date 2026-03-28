package com.wifi.app.controllers;






import com.wifi.app.entity.InventoryMaterial;
import com.wifi.app.entity.Store;
import com.wifi.app.entity.User;
import com.wifi.app.objects.MaterialTopFiveByStore;
import com.wifi.app.objects.ResultQueryDTO;
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
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.Arrays;
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

    @GetMapping("/")
    public String index(Authentication authentication){

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

        //log.info(">> jsonObject.toString()********************** : {}",jsonObject.toString());

        return jsonObject.toString();

    }

    @RequestMapping("/chartMovementsByUser")
    @ResponseBody
    public String getDataMovementsByUser(){

        List<Object[]> listU = queryservice.JPQLQueryChartUsersMovements();

        JSONArray jsonArrayCountUser = new JSONArray();
        jsonArrayCountUser.put(listU);
        JSONObject jsonObjectUser = new JSONObject ();
        jsonObjectUser.put("obj", jsonArrayCountUser);

        //log.info(">> jsonObjectUser.toString()********************** : {}",jsonObjectUser.toString());

        return jsonObjectUser.toString();

    }

    @RequestMapping("/chartTopFiveMaterialUsed")
    @ResponseBody
    public String getDataTopFiveMaterialUsed(){

        List<Object[]> listU = queryservice.JPQLQueryChartTopFiveMaterialUsed();

        JSONArray jsonArrayCountTopFive = new JSONArray();
        jsonArrayCountTopFive.put(listU);
        JSONObject jsonObject = new JSONObject ();
        jsonObject.put("objTopFive", jsonArrayCountTopFive);

        //log.info(">> jsonObjectUser.toString()********************** : {}",jsonObject.toString());

        return jsonObject.toString();

    }

    @RequestMapping("/chartMovementsByStore")
    @ResponseBody
    public String getDataMovementsByStore(){

        List<Object[]> listU = queryservice.JPQLQueryChartMovementsByStore();

        JSONArray jsonArrayCountTopFive = new JSONArray();
        jsonArrayCountTopFive.put(listU);
        JSONObject jsonObject = new JSONObject ();
        jsonObject.put("objMovementsByStore", jsonArrayCountTopFive);

        //log.info(">> jsonObject.toString()********************** : {}",jsonObject.toString());

        return jsonObject.toString();

    }

    @RequestMapping("/chartTopMaterialByMovementsStore")
    @ResponseBody
    public String getDataMovementsByStoreAndExit(){

        List<Store> listStore = storeService.getStoreList();
        MaterialTopFiveByStore [] arr = new MaterialTopFiveByStore [listStore.size()];

        for ( int i=0; i < listStore.size(); i++) {
            //System.out.println("store:  " + listStore.get(i).getId());
               // if(listStore.get(i).getId() !=8 ){
                    List<Object[]> resultQueryDTO = queryservice.JPQLQueryChartTopFiveMaterialByStore(listStore.get(i).getId());

                    boolean ans = resultQueryDTO.isEmpty();

                    if (ans == false){
                        for (Object[] result : resultQueryDTO) {
                            //System.out.println("result[0]:  " + result[0]);
                            Integer count = ((BigInteger) result[0]).intValue();
                            arr[i] = new MaterialTopFiveByStore(listStore.get(i).getName(), (String)result[2], count);
                        }
                    }else {
                        arr[i] = new MaterialTopFiveByStore(listStore.get(i).getName(), "", 0);
                    }
                //}
        }

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(arr);
        JSONObject jsonObject = new JSONObject ();
        jsonObject.put("objTopMaterialByMovementsStore", jsonArray);

        //log.info(">> jsonObject.toString()********************** : {}",jsonObject.toString());

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

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> user = userRepository.findUserByUsername(username);
        date_expired = user.get().getDate_expired();

        if(date_local.after(date_expired) ){
            return "redirect:/changepassword";
        }else {
            return "authenticated";
        }
    }


}
