package com.wifi.app.controllers;



import com.wifi.app.entity.*;
import com.wifi.app.objects.MaterialDTO;
import com.wifi.app.service.*;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.wifi.app.controllers.HomeController.GLOBAL_USER_NAME;


@Controller
public class MaterialsController {

    private static final Logger log = LoggerFactory.getLogger(MaterialsController.class);
    private final MaterialService materialService;
    private final DestinationService destinationService;
    private final StoreService storeService;
    private final BrandService brandService;
    private final InventoryMaterialService inventoryMaterialService;

    private final SiteService siteService;

    @Autowired
    public MaterialsController(MaterialService materialService, DestinationService destinationService, StoreService storeService, BrandService brandService, InventoryMaterialService inventoryMaterialService, SiteService siteService) {
        this.materialService = materialService;
        this.destinationService = destinationService;
        this.storeService = storeService;
        this.brandService = brandService;
        this.inventoryMaterialService = inventoryMaterialService;
        this.siteService = siteService;
    }

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/materials-management")
    public String getall(Model model){

        List<Material> materialList = materialService.getMaterialList();
        model.addAttribute("DataMaterialList", materialList);
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

        return "redirect:/materials-management";
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
