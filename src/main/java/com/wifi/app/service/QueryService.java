package com.wifi.app.service;



import com.wifi.app.objects.GeneratorHistDTO;
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

        //log.info(">> typedQuery ******************************************************* : {}", typedQuery);

        TypedQuery<SucursalDetail> queryTipado = em.createQuery(typedQuery, SucursalDetail.class);
        List<SucursalDetail> list = queryTipado.getResultList();

        em.close();

        //log.info(">> list Sucursales ******************************************************* : {}", list);

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
    public List<Object[]> JPQLQueryChartTopFiveMaterialUsed() {
        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT COUNT(*) AS count,  \n" +
                "       a.model\n" +
                "FROM material a INNER JOIN movements_material b ON  a.id = b.material_id \n" +
                "AND b.type_movement = 'SALIDA'\n" +
                "GROUP BY model\n" +
                "ORDER BY count DESC\n" +
                "LIMIT 5 ");

        //log.info(">> queryYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY ******************************************************* : {}", query);

        List<Object[]> list =(List<Object[]>)query.getResultList();

        em.close();
        return list;
    }

    @Override
    public List<Object[]> JPQLQueryChartMovementsByStore() {
        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT COUNT(*) AS count,  \n" +
                "       s.name\n" +
                "FROM movements_material m INNER JOIN store s ON  m.store_id = s.id \n" +
                "GROUP BY store_id\n" +
                "ORDER BY count DESC;");

        //log.info(">> JPQLQueryChartMovementsByStore: query ******************************************************* : {}", query);

        List<Object[]> list =(List<Object[]>)query.getResultList();

        em.close();
        return list;
    }

    @Override
    public List<Object[]> JPQLQueryChartMovementsByStoreAndExit() {
        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("select count(*) as count, m.model, s.name "
                + " from  movements_material a, material m, store s "
                + " where a.material_id = m.id "
                + " and s.id = a.store_id "
                + " and a.type_movement = 'SALIDA' "
                + "group by m.model, a.store_id "
                + "ORDER BY count DESC "
                + "LIMIT 5;");

        //log.info(">> JPQLQueryChartMovementsByStoreAndExit: query ******************************************************* : {}", query);

        List<Object[]> list =(List<Object[]>)query.getResultList();

        em.close();
        return list;
    }

    @Override
    public List<Object[]> JPQLQueryChartTopFiveMaterialByStore(Integer id) {
        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT Count(*) AS count, "
                + "       mm.material_id, "
                + "       m.description, "
                + "       m.model "
                + "FROM   movements_material mm, "
                + "       material m "
                + "WHERE  mm.store_id = " + id
                + "       AND m.id = mm.material_id "
                + "GROUP  BY m.model "
                + "ORDER  BY count DESC "
                + "LIMIT  1;");

        //log.info(">> JPQLQueryChartTopFiveMaterialByStore: query ******************************************************* : {}", query);

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
    public List<GeneratorHistDTO> JPQLQueryGeneratorHist(int id) {
        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery( "SELECT d.created_at as fecha, site_id as id, s.name as sitio, p.name as provincia, p.region as region, m.tank_level as maxNivel, d.current_level as nivelActual, d.refill as suministro, d.hours_worked as horas, d.estimated_amount as costo, "
                + "round((d.current_level / m.tank_level * 100),2) as nivelPrevio,  round((((d.current_level + d.refill) * 100) /  m.tank_level),2) AS nivelFinal, m.tank_level - d.current_level AS recargar "
                + "from mobil_generator m, mobil_generator_detail d, province p, site s "
                + "where m.site_id = " + id
                + " and m.id = d.mobil_generator_id "
                + "and m.province_id = p.id "
                + "and m.site_id = s.id "
                + "order by fecha asc;");

        List<GeneratorHistDTO> list =(List<GeneratorHistDTO>)query.getResultList();

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

        //log.info(">> query        ******************************************************* : {}", query);
        BigInteger count = (BigInteger) query.getSingleResult();
        //log.info(">> count        ******************************************************* : {}", count);
        em.close();
        return count;
    }

    @Override
    public  BigInteger JPQLQueryEstablishmentByClientId(int client_id){

        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("SELECT COUNT(*) AS COUNT\n" +
                "FROM establishment\n" +
                "WHERE status = 1 AND client_id = "+ client_id + ";" );


        //log.info(">> JPQLQueryEstablishmentByClientId     query   ******************************************************* : {}", query);
        BigInteger  count = (BigInteger) query.getSingleResult();
        //log.info(">> JPQLQueryEstablishmentByClientId     count   ******************************************************* : {}", count);
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
