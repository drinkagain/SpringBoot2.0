package com.jiuxian.report.biz;

import com.jiuxian.bean.BeanUtil;
import com.jiuxian.report.SpringbootReportApplicationTests;
import com.jiuxian.report.dao.ReportSqlDao;
import com.jiuxian.report.entity.ReportSql;
import com.jiuxian.report.service.ReportSqlService;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

public class ReportDaoTest extends SpringbootReportApplicationTests {
    @Autowired
    private ReportSqlDao dao;
    @Autowired
    private ReportSqlService sqlService;
    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void ReportSqlSave() {
        ReportSql reportSql = new ReportSql();
        reportSql.setName("工厂、车间");
        //String resultSql = "select  a.id as plantId,b.id as workShopId,a.no as plantCode,b.no as workshopCode," +
        //    " a.NAME_CN as plantName,b.NAME_CN as workShopName from tm_plant a inner join tm_workshop b on a.id = b.tm_plant_Id";
        String resultSql = "SELECT CASE id WHEN 1 THEN 'A' WHEN 70 THEN" +
                "'B' WHEN 75 THEN 'C' ELSE  id  END AS id, NO AS NO, name_cn AS nameCn" +
                " FROM  tm_plant";
        reportSql.setOriginalSql(resultSql.toUpperCase());
        sqlService.saveReportSqlAndColumns(reportSql);
        System.out.println(reportSql.getResultSql());
        System.out.println(reportSql.getCountSql());
    }

    @Test
    @Transactional
    public void getReportSql() {
        List<ReportSql> all = dao.findAll();
        Session session = entityManager.unwrap(Session.class);
        for (ReportSql sql : all) {
            NativeQuery nativeQuery = session.createNativeQuery(sql.getResultSql());
            nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List<Map<String, Object>> resultList = BeanUtil.cast(nativeQuery.getResultList());
            resultList.forEach(map -> map.forEach((key, value) -> {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println(key);
                System.out.println(value);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }));

            System.out.println(resultList);
            NativeQuery nativeQuery1 = session.createNativeQuery(sql.getCountSql());
            System.out.println(nativeQuery1.getSingleResult());
        }
    }

    @Test
    public void reportSqlTest() {
        List<ReportSql> all = dao.findAll();
        all.forEach((bean) -> {
            String sql = bean.getResultSql();
            System.out.println(sql);
        });
    }

}