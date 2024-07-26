package com.wifi.app.controllers;



import com.google.gson.Gson;
import com.wifi.app.entity.*;
import com.wifi.app.objects.MatDTO;
import com.wifi.app.objects.MaterialDTO;
import com.wifi.app.objects.ResponseCountDTO;
import com.wifi.app.service.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigInteger;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.wifi.app.controllers.HomeController.GLOBAL_USER_NAME;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
@RequiredArgsConstructor
public class MaterialsController {

    private static final Logger log = LoggerFactory.getLogger(MaterialsController.class);
    private final MaterialService materialService;
    private final DestinationService destinationService;
    private final StoreService storeService;
    private final BrandService brandService;
    private final InventoryMaterialService inventoryMaterialService;
    private final SiteService siteService;

    private Model GlobalModel;

    @Autowired
    QueryService queryservice;

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/materials-management")
    public String getall(Model model){

        GlobalModel = model;

        ArrayList<Integer> list = new ArrayList<>();

        list.add(8);
        /*List<Store> storeList = storeService.getStoreList();



        List<Store> list1 = storeService.findByIdIn(list);*/

        List<Store> list1 = storeService.findByIdNotIn(list);


        ArrayList<ResponseCountDTO> responseCountDTOArrayList = new ArrayList<>();



        for(Store stores : list1){
            ResponseCountDTO responseCountDTO = new ResponseCountDTO();
            responseCountDTO.setCount(materialService.getMaterialCountByStoreId(stores.getId()));
            responseCountDTO.setName(stores.getName());
            responseCountDTOArrayList.add(responseCountDTO);
        }


        model.addAttribute("dataMaterialByStore",list1);
        model.addAttribute("dataMaterialCountByStore",responseCountDTOArrayList);
        return "materials-management";
    }

    @GetMapping("/register-material")
    public String registerMaterial(@ModelAttribute MaterialDTO materialDTO, Model model){

        List<Store> stores = storeService.getStoreList();
        List<Brand> brand = brandService.getBrandListAll();
        List<InventoryMaterial> inventory =  inventoryMaterialService.getListInventoryMaterial();
        List<Site> site = siteService.getSiteList();

        model.addAttribute("dataStore",stores);
        model.addAttribute("dataBrand",brand);
        model.addAttribute("dataInventory",inventory);
        model.addAttribute( "materialDTO", materialDTO);
        model.addAttribute("dataSite",site);

        return "register-material";
    }

    @PostMapping("/register-material")
    public String save(@Validated MaterialDTO materialDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(materialService.materialExists(materialDTO.getSn())){
            bindingResult.addError(new FieldError("materialDTO", "sn",
                    "El material ya esta registrado"));
        }

        if(bindingResult.hasErrors()){
            return "register-material";
        }

        if (GLOBAL_USER_NAME != null ){
            materialDTO.setUser(GLOBAL_USER_NAME);
        }else {
            materialDTO.setUser("nullRegMat");
        }

        materialDTO.setSiteId(8);
        materialDTO.setEnabled(true);


        Integer inventoryId = materialDTO.getInventoryMaterialId();
        InventoryMaterial inventoryMaterial = inventoryMaterialService.getInventoryMaterialById(inventoryId);

        materialService.register(materialDTO);

        Integer totalEnable = materialService.getMaterialCountByEnabledAndInventoryMaterialId(true, inventoryId);
        Integer totalDisabled = materialService.getMaterialCountByEnabledAndInventoryMaterialId(false, inventoryId);

        inventoryMaterial.setCount(totalEnable + totalDisabled);
        inventoryMaterial.setCountEnabled(totalEnable);
        inventoryMaterial.setCountUsed(totalDisabled);
        inventoryMaterialService.save(inventoryMaterial);

        redirectAttributes.addFlashAttribute("message", "Material Registrado");

        return "redirect:/register-material";

    }

    @PostMapping("/delete_material")
    public String delete(@Validated Integer id, Model model, RedirectAttributes redirectAttributes) {

       String  rd = deleteMaterial(id );

        redirectAttributes.addFlashAttribute("message", "Material Eliminado");

       return rd;
    }

    @PostMapping("/view_material")
    public String view(@Validated Integer id, Model model, RedirectAttributes redirectAttributes) {

        List<Material> materialList = materialService.findMaterialByStoreId(id);
        model.addAttribute("dataMaterialList", materialList);

        return "redirect:/materials-management";
    }
    @GetMapping("/materials-detail")
    public String get(Model model) {

        return "materials-detail";
    }


    @PostMapping("/materials-detail")
    public String viewMaterialByStoreId(@Validated Integer id, Model model) {

        System.out.println("post id: "+id);

        List<Material> materialList = materialService.findMaterialByStoreId(id);

        System.out.println("post materialList : "+materialList);

        model.addAttribute("dataMaterialByStoreId", materialList);

        return "materials-detail";
    }




    @RequestMapping(value="/searchMaterialByStore", params = "id", method = GET)
    @ResponseBody
    public String searchMaterialByStore(@RequestParam("id") int id){

        //List<Object[]> listMat = queryservice.JPQLQueryFindMaterialByStoreId(id);
        List<MatDTO> list = queryservice.JPQLQueryMat(id);
        System.out.println("JPQLQueryMat: " + list);
       // List <Material> list = materialService.findMaterialByStoreId(id);

        JSONArray jsonArrayMat = new JSONArray();
        //JSONArray jsonArrayMat1 = new JSONArray();

        JSONObject jsonObject = new JSONObject ();

        jsonArrayMat.put(list);
        //jsonArrayMat1.put(listMat);

        jsonObject.put("ListMat", jsonArrayMat);
        //jsonObject.put("ListMat1", jsonArrayMat1);

        log.info(">> jsonObject.toString()********************** : {}",jsonObject.toString());


        return jsonObject.toString();

    }

    private String deleteMaterial (Integer id){

        Material material = materialService.findMaterialById(id);
        String rd = null;
        Integer inventoryMaterialId = material.getInventoryMaterial().getId();

        materialService.deleteMaterialById(id);

        InventoryMaterial inventoryMaterial = inventoryMaterialService.getInventoryMaterialById(inventoryMaterialId);


        Integer totalEnable = materialService.getMaterialCountByEnabledAndInventoryMaterialId(true, inventoryMaterialId);
        Integer totalDisabled = materialService.getMaterialCountByEnabledAndInventoryMaterialId(false, inventoryMaterialId);


        inventoryMaterial.setCount(totalEnable + totalDisabled);
        inventoryMaterial.setCountEnabled(totalEnable);
        inventoryMaterial.setCountUsed(totalDisabled);
        inventoryMaterialService.save(inventoryMaterial);

        switch (inventoryMaterial.getName()) {
            case "FWA":
                rd= "redirect:/finventory-management";
                break;
            case "WIFI":
                rd= "redirect:/winventory-management";
                break;
            case "RAN":
                rd= "redirect:/rinventory-management";
                break;
            case "CELL-FI":
                rd= "redirect:/cinventory-management";
                break;
            default:
                rd= "redirect:/materials-management";
                break;
        }

        return rd;
    }

}
