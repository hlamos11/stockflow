package com.wifi.app.service;


import com.wifi.app.objects.SucursalDetail;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface IQueryService {
    List<SucursalDetail> JPQLQuery();
    List<Object[]> JPQLQueryEstablishmentTopFive();
    List<Object[]> JPQLQueryInstallationsForMonth();
    BigInteger JPQLQueryClientsTotal();
    BigInteger JPQLQueryEstablishments(int id, String name);
    BigInteger JPQLQueryEstablishmentByClientId(int client_id);
    Integer JPQLQueryMaxStepMovementMaterial(int materialId);

    BigInteger JPQLQueryChartMaterialByStoreAndInventory(int idStore, int idInventory);
}
