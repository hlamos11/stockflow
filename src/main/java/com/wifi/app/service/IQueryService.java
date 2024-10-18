package com.wifi.app.service;


import com.wifi.app.objects.GeneratorHistDTO;
import com.wifi.app.objects.MatDTO;
import com.wifi.app.objects.ResultQueryDTO;
import com.wifi.app.objects.SucursalDetail;

import java.math.BigInteger;
import java.util.List;

public interface IQueryService {
    List<SucursalDetail> JPQLQuery();
    List<Object[]> JPQLQueryEstablishmentTopFive();
    List<Object[]> JPQLQueryInstallationsForMonth();
    BigInteger JPQLQueryClientsTotal();
    BigInteger JPQLQueryEstablishments(int id, String name);
    BigInteger JPQLQueryEstablishmentByClientId(int client_id);
    Integer JPQLQueryMaxStepMovementMaterial(int materialId);

    BigInteger JPQLQueryChartMaterialByStoreAndInventory(int idStore, int idInventory);

    List<Object[]> JPQLQueryFindMaterialByStoreId(int id);

    List<Object[]> JPQLQueryChartUsersMovements();
    List<Object[]> JPQLQueryChartTopFiveMaterialUsed();

    List<Object[]> JPQLQueryChartMovementsByStore();

    List<Object[]> JPQLQueryChartMovementsByStoreAndExit();

    List<Object[]> JPQLQueryChartTopFiveMaterialByStore(Integer id);

    List<MatDTO> JPQLQueryMat(int id);

    List<GeneratorHistDTO> JPQLQueryGeneratorHist(int id);
}
