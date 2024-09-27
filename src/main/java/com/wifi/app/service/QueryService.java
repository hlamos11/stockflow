package com.wifi.app.service;



import com.wifi.app.objects.MatDTO;
import com.wifi.app.objects.SucursalDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.List;

@Service
public class QueryService implements IQueryService{

    private static final Logger log = LoggerFactory.getLogger(QueryService.class);


    @Autowired
    EntityManagerFactory emf;



    @Override
    public List<SucursalDetail> JPQLQuery() {

        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin( );


//Query que solo trae los resultados
//        Query query = em.createQuery("Select " +"d.id,d.idclient, d.name, d.province, d.status, s.countap"
//                + " from Sucursalesdetalle s inner join Sucursales d on s.id=d.id");
//        List<SucursalDetail> list =(List<SucursalDetail>)query.getResultList();
        @SuppressWarnings("unchecked")
//        String typedQuery = " SELECT NEW com.wifi.app.objects.SucursalDetail(d.id, s.ids, d.idclient, d.name, d.province, " +
//                "d.status, s.countap) FROM Sucursalesdetalle s INNER JOIN Sucursales d ON s.id=d.id";
        String typedQuery = "SELECT NEW com.wifi.app.objects.SucursalDetail( s.id,\n" +
                "       c.name,\n" +
                "       s.nameestablishment,\n" +
                "       s.product,\n" +
                "       s.purpple,\n" +
                "       s.status,\n" +
                "       s.datesend,\n" +
                "       s.dateoperative,\n" +
                "       s.lastmille,\n" +
                "       d.countap,\n" +
                "       d.modelap,\n" +
                "       d.countpower,\n" +
                "       d.countswitch,\n" +
                "       d.modelswitch\n" +
                " ) FROM EstablishmentDetail d, Establishment s, Clients c\n" +
                "WHERE s.client_id = c.id\n" +
                "AND s.id = d.idestablishment";

        log.info(">> typedQuery ******************************************************* : {}", typedQuery);

        TypedQuery<SucursalDetail> queryTipado = em.createQuery(typedQuery, SucursalDetail.class);
        List<SucursalDetail> list = queryTipado.getResultList();

        em.close();

        log.info(">> list Sucursales ******************************************************* : {}", list);

        return list;

    }

    @Override
    public List<Object[]> JPQLQueryEstablishmentTopFive() {

        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT COUNT(e.client_id) AS count,\n" +
                "       c.name\n" +
                "FROM establishment e INNER JOIN clients c ON e.client_id = c.id\n" +
                "GROUP BY name\n" +
                "ORDER BY count DESC  \n"+
                "LIMIT 5 ");
        List<Object[]> list =(List<Object[]>)query.getResultList();

        em.close();
        return list;
    }

    @Override
    public List<Object[]> JPQLQueryFindMaterialByStoreId(int id) {

        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT sn, part, model, description\n" +
                "FROM material\n" +
                "WHERE store_id = "+ id );
        List<Object[]> list =(List<Object[]>)query.getResultList();

        em.close();
        return list;
    }

   /* @Override
    public List<Object[]> JPQLQueryChartUsersMovements() {
        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT COUNT(*) AS count,  \n" +
                "       m.user\n" +
                "FROM movements_material m \n" +
                "GROUP BY user\n" +
                "ORDER BY count DESC");

        List<Object[]> list =(List<Object[]>)query.getResultList();


        em.close();
        return list;
    }*/

    @Override
    public List<Object[]> JPQLQueryChartUsersMovements() {
        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT COUNT(*) AS count,  \n" +
                "       m.user\n" +
                "FROM movements_material m \n" +
                "GROUP BY user");

        List<Object[]> list =(List<Object[]>)query.getResultList();


        em.close();
        return list;
    }

    @Override
    public List<MatDTO> JPQLQueryMat(int id) {
        EntityManager em = emf.createEntityManager();

        String typedQuery = "SELECT NEW com.wifi.app.objects.MatDTO( m.sn,\n" +
                "       m.part,\n" +
                "       m.model,\n" +
                "       m.description\n" +
                " ) FROM Material m\n" +
                " WHERE m.store = " + id ;

        TypedQuery<MatDTO> queryTipado = em.createQuery(typedQuery, MatDTO.class);
        List<MatDTO> list = queryTipado.getResultList();

        em.close();

        return list;
    }


    @Override
    public List<Object[]> JPQLQueryInstallationsForMonth() {

        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT count(*) AS COUNT,\n" +
                "       dateoperative\n" +
                "FROM establishment\n" +
                "WHERE dateoperative BETWEEN\n" +
                "    (SELECT date_add(curdate(), interval - day(curdate()) + 1 DAY)) AND\n" +
                "    (SELECT last_day(curdate()))\n" +
                "GROUP BY dateoperative");
        List<Object[]> list =(List<Object[]>)query.getResultList();
        em.close();
        return list;
    }

    @Override
    public BigInteger JPQLQueryClientsTotal() {

        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT COUNT(*) AS COUNT\n" +
                "FROM clients\n" +
                "WHERE enabled = 1");
        BigInteger count = (BigInteger) query.getSingleResult();
        em.close();
        return count;
    }

    @Override
    public  BigInteger JPQLQueryEstablishments(int id, String name){

        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT COUNT(*) AS COUNT\n" +
                "FROM establishment\n" +
                "WHERE client_id = "+ id + "\n" +
                "AND nameestablishment = '"+ name + "';" );

        log.info(">> query        ******************************************************* : {}", query);
        BigInteger count = (BigInteger) query.getSingleResult();
        log.info(">> count        ******************************************************* : {}", count);
        em.close();
        return count;
    }

    @Override
    public  BigInteger JPQLQueryEstablishmentByClientId(int client_id){

        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT COUNT(*) AS COUNT\n" +
                "FROM establishment\n" +
                "WHERE status = 1 AND client_id = "+ client_id + ";" );


        log.info(">> JPQLQueryEstablishmentByClientId     query   ******************************************************* : {}", query);
        BigInteger  count = (BigInteger) query.getSingleResult();
        log.info(">> JPQLQueryEstablishmentByClientId     count   ******************************************************* : {}", count);
        em.close();
        return count;
    }

    @Override
    public Integer JPQLQueryMaxStepMovementMaterial(int materialId) {

        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT MAX(step) AS MAX\n" +
                "FROM movements_material\n" +
                "WHERE material_id = "+ materialId + ";" );

        Integer  count = (Integer) query.getSingleResult();
        em.close();
        return count;

    }

    @Override
    public BigInteger JPQLQueryChartMaterialByStoreAndInventory(int idStore, int idInventory) {

        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT COUNT(*) AS COUNT\n" +
                "FROM material\n" +
                "WHERE store_id = "+ idStore + "\n" +
                "AND inventory_material_id = "+ idInventory + ";" );

        BigInteger count = (BigInteger) query.getSingleResult();
        em.close();
        return count;
    }


}
