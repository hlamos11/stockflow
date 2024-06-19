package com.wifi.app.res;

import com.wifi.app.entity.InventoryMaterial;
import com.wifi.app.entity.Material;
import com.wifi.app.entity.Site;
import com.wifi.app.entity.Store;
import com.wifi.app.objects.MovementDTO;
import com.wifi.app.service.InventoryMaterialService;
import com.wifi.app.service.MaterialService;
import com.wifi.app.service.SiteService;
import com.wifi.app.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class CalculateInventory {

    private static final Logger log = LoggerFactory.getLogger(CalculateInventory.class);
    private final InventoryMaterialService inventoryMaterialService;
    private final MaterialService materialService;

    private final SiteService siteService;
    private final StoreService storeService;

    @Autowired
    public CalculateInventory(InventoryMaterialService inventoryMaterialService, MaterialService materialService, SiteService siteService, StoreService storeService) {
        this.inventoryMaterialService = inventoryMaterialService;
        this.materialService = materialService;
        this.siteService = siteService;
        this.storeService = storeService;
    }


    public Integer calculateInventory(Integer materialId, Integer param, MovementDTO movementDTO) {

         try {

            Material material = materialService.findMaterialById(materialId);
            Integer inventoryMaterialId = material.getInventoryMaterial().getId();
            InventoryMaterial inventoryMaterial = inventoryMaterialService.getInventoryMaterialById(inventoryMaterialId);

            Store store;
            Site site;

            switch (param) {
                case 1:
                    //Entrada y Retorno
                    site = siteService.findSiteById(8);
                    store = storeService.findStoreById(movementDTO.getStoreId());
                    material.setEnabled(true);
                    material.setSite(site);
                    material.setStore(store);
                    break;
                case 2:
                    //Salida
                    store = storeService.findStoreById(8);
                    site = siteService.findSiteById(movementDTO.getSiteId());
                    material.setSite(site);
                    material.setEnabled(false);
                    material.setStore(store);
                    break;
                case 3:
                    //Traslado
                    store = storeService.findStoreById(movementDTO.getStoreId());
                    material.setStore(store);
                    materialService.save(material);
                    return 1;
                default:
                    break;
            }

            materialService.save(material);

            Integer totalEnabled = materialService.getMaterialCountByEnabledAndInventoryMaterialId(true, inventoryMaterialId);
            Integer totalDisabled = materialService.getMaterialCountByEnabledAndInventoryMaterialId(false, inventoryMaterialId);

            inventoryMaterial.setCountUsed(totalDisabled);
            inventoryMaterial.setCountEnabled(totalEnabled);

            inventoryMaterialService.save(inventoryMaterial);

            return 1;

        } catch (NumberFormatException e) {
            log.info("Error, calculateInventory: ", e.getCause());
            log.info("Error, calculateInventory: ", e.toString());
            e.printStackTrace();
            return -1;
        }
    }

}
