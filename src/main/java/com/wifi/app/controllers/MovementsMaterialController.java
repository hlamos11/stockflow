package com.wifi.app.controllers;

import com.wifi.app.entity.*;
import com.wifi.app.objects.MovementDTO;
import com.wifi.app.res.CalculateInventory;
import com.wifi.app.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.List;

import static com.wifi.app.controllers.HomeController.GLOBAL_USER_NAME;

@Controller
public class MovementsMaterialController {

    @Autowired
    QueryService queryservice;

    private final MovementsMaterialService movementsMaterialService;
    private final MaterialService materialService;
    private final DestinationService destinationService;
    private final StoreService storeService;
    private final ResponsibleMaterialService responsibleMaterialService;
    private final BrandService brandService;
    private final SiteService siteService;

    private final InventoryMaterialService inventoryMaterialService;
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);


    @Autowired
    public MovementsMaterialController(MovementsMaterialService movementsMaterialService, MaterialService materialService, DestinationService destinationService, StoreService storeService, ResponsibleMaterialService responsibleMaterialService, BrandService brandService, SiteService siteService, InventoryMaterialService inventoryMaterialService) {
        this.movementsMaterialService = movementsMaterialService;
        this.materialService = materialService;
        this.destinationService = destinationService;
        this.storeService = storeService;
        this.responsibleMaterialService = responsibleMaterialService;
        this.brandService = brandService;
        this.siteService = siteService;
        this.inventoryMaterialService = inventoryMaterialService;
    }

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }


    @GetMapping("/movements-material")
    public String get(Model model) {

        List<MovementsMaterial> movementsMaterialList = movementsMaterialService.getMovementsMaterial();
        model.addAttribute("dataMovementsMaterial", movementsMaterialList);
        return "movements-material";
    }


    @GetMapping("/register-movement")
    public String registerMovements(@ModelAttribute MovementDTO movementDTO, Model model) {

        List<Material> materialList = materialService.getMaterialList();
        List<Store> storeList = storeService.findStoreByEnabled(true);
        List<Destination> destinationList = destinationService.getFindDestinationAll();
        List<Responsible> responsibleList = responsibleMaterialService.getResponsibleList();
        List<Site> site = siteService.getSiteList();
        List<Brand> brand = brandService.getBrandListAll();

        model.addAttribute("movementDTO", movementDTO);
        model.addAttribute("dataMaterialList", materialList);
        model.addAttribute("dataStore", storeList);
        model.addAttribute("dataDestination", destinationList);
        model.addAttribute("dataResponsible", responsibleList);
        model.addAttribute("dataSite", site);
        model.addAttribute("dataBrand", brand);


        return "register-movement";
    }

    @GetMapping("/brandId")
    public String findMaterialByBrand(@RequestParam(value = "brandId", required = true) Long brandId) {

        //log.info("prueba brandID:", brandId);
        return "redirect:/register-movement";
        //return materialService.findMaterialByBrandId(1);
    }


    @PostMapping("/register-movement")
    public String register(@Validated MovementDTO movementDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws ParseException {

        CalculateInventory calculateInventory = new CalculateInventory(inventoryMaterialService, materialService, siteService, storeService);
        Integer respMovement = movementsByMaterial(movementDTO.getMaterialId(), movementDTO.getTypeMovement());
        Integer resp = 0;

        if (GLOBAL_USER_NAME != null ){
            movementDTO.setUser(GLOBAL_USER_NAME);
        }else {
            movementDTO.setUser("nullRegMov");
        }


        if (respMovement == 0) {
            movementDTO.setStep(1);
        } else if (respMovement == 1) {
            Integer maxStep = findMaxStep(movementDTO.getMaterialId());
            movementDTO.setStep(maxStep + 1);
        }


        if (respMovement == 1 || respMovement == 0) {
            //Se puede registrar

            if (movementDTO.getDateReturn() == null) {
                movementDTO.setDateReturn(null);
            }

            if (movementDTO.getDateIn() == null) {
                movementDTO.setDateIn(null);
            }

            if (movementDTO.getDateTransfer() == null) {
                movementDTO.setDateTransfer(null);
            }

            if (movementDTO.getDateOut() == null) {
                movementDTO.setDateOut(null);
            }

            if (movementDTO.getStoreId() == null) {
                movementDTO.setStoreId(8);
            }
            if (movementDTO.getSiteId() == null) {
                movementDTO.setSiteId(8);
            }

        } else if (respMovement == -1) {
            //no se puede
            redirectAttributes.addFlashAttribute("message", "No se puede registrar el mismo tipo de movimiento que el ultimo registrado");
            return "redirect:/register-movement";
        }

        //movementsMaterialService.register(movementDTO);

        if (movementDTO.getTypeMovement().equals("SALIDA")) {
            resp = calculateInventory.calculateInventory(movementDTO.getMaterialId(), 2, movementDTO);
        }

        if (movementDTO.getTypeMovement().equals("RETORNO")) {
            resp = calculateInventory.calculateInventory(movementDTO.getMaterialId(), 1, movementDTO);
        }

        if (movementDTO.getTypeMovement().equals("TRASLADO")) {
            resp = calculateInventory.calculateInventory(movementDTO.getMaterialId(), 3, movementDTO);
        }

        if (resp == 1) {
            movementsMaterialService.register(movementDTO);
            redirectAttributes.addFlashAttribute("message", "Movimiento registrado");
        } else if (resp == -1) {
            redirectAttributes.addFlashAttribute("message", "No se pudo registrar el movimiento");
        }

        return "redirect:/register-movement";

    }


    public Integer movementsByMaterial(Integer materialId, String typeMovement) {
        try {

            Integer maxStep = findMaxStep(materialId);
            MovementsMaterial movementsMaterial = movementsMaterialService.findMovementsMaterialByMaterialIdAndStep(materialId, maxStep);

            if (movementsMaterial == null) {
                return 0;
            }
            if (typeMovement.equals(movementsMaterial.getTypeMovement())) {
                return -1;
            }

            return 1;

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Integer findMaxStep(Integer id) {
        try {
            Integer maxStep = (Integer) queryservice.JPQLQueryMaxStepMovementMaterial(id);
            return maxStep;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
