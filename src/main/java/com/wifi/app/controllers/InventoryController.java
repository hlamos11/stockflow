package com.wifi.app.controllers;


import com.wifi.app.entity.InventoryMaterial;
import com.wifi.app.entity.Material;
import com.wifi.app.exception.NotFoundException;
import com.wifi.app.res.MaterialExcelExporter;
import com.wifi.app.service.InventoryMaterialService;
import com.wifi.app.service.MaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@Controller
public class InventoryController {

    private static final Logger log = LoggerFactory.getLogger(InventoryController.class);

    private  Integer LocalId = null;

    private final InventoryMaterialService inventoryMaterialService;
    private final MaterialService materialService;

    @Autowired
    public InventoryController(InventoryMaterialService inventoryMaterialService, MaterialService materialService) {
        this.inventoryMaterialService = inventoryMaterialService;
        this.materialService = materialService;
    }

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }


    @GetMapping("/inventory-management")
    public String getWifiInventory(Model model) {

        List<InventoryMaterial> inventoryMaterialList = inventoryMaterialService.getListInventoryMaterial();

        model.addAttribute("DataInventoryList", inventoryMaterialList);

        if (inventoryMaterialList == null) throw new NotFoundException();

        return "inventory-management";
    }

    @PostMapping("/search_inventory")
    public String search (@Validated Integer id) {

        LocalId = id;
        String rd = null;

        InventoryMaterial inventoryMaterial = inventoryMaterialService.getInventoryMaterialById(id);

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
                break;
        }

        return rd;
    }

    @GetMapping("/finventory-management")
    public String getFwaInventory(Model model) {

        model.addAttribute("dataMaterialInventoryList", getMaterialListByInventory(LocalId));
        model.addAttribute("id_material_inventory", LocalId);

        return "finventory-management";
    }

    @GetMapping("/rinventory-management")
    public String getRanInventory(Model model) {

        model.addAttribute("dataMaterialInventoryList", getMaterialListByInventory(LocalId));
        model.addAttribute("id_material_inventory", LocalId);

        return "rinventory-management";
    }

    @GetMapping("/cinventory-management")
    public String getCellInventory(Model model) {

        model.addAttribute("dataMaterialInventoryList", getMaterialListByInventory(LocalId));
        model.addAttribute("id_material_inventory", LocalId);

        return "cinventory-management";
    }

    @GetMapping("/winventory-management")
    public String getWiFiInventory(Model model) {

        model.addAttribute("dataMaterialInventoryList", getMaterialListByInventory(LocalId));
        model.addAttribute("id_material_inventory", LocalId);

        return "winventory-management";
    }

    @PostMapping("/export")
    public void exportToExcel(HttpServletResponse response, @Validated Integer id) throws IOException {

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=materials_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        //List<Material> materialList = materialService.findMaterialByInventoryMaterialId(id);

        MaterialExcelExporter excelExporter = new MaterialExcelExporter(getMaterialListByInventory(id));

        excelExporter.export(response);

    }

    public List<Material> getMaterialListByInventory(Integer id){
        return  materialService.findMaterialByInventoryMaterialId(id);
    }

}
