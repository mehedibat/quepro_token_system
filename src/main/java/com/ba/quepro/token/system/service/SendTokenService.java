package com.ba.quepro.token.system.service;

import com.ba.quepro.token.system.bean.RemoteTokenBean;
import com.ba.quepro.token.system.bean.ServiceCenterInfo;
import com.ba.quepro.token.system.bean.ServiceInfoBean;
import com.ba.quepro.token.system.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

@Slf4j
@Service
public class SendTokenService {
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    private String SERVICE_DATA = "SELECT * FROM service_info";
    private String SERVICE_CENTER_DATA = "SELECT * FROM service_center_info";
    private String REMOTE_TOKEN_INSERT_QUERY = "INSERT INTO  REMOTE_TOKEN\n" +
            "            (SC_ID, RT_DATE, RT_TIME, ISSUED_DATE, WEB_TOKEN,\n" +
            "            TOKEN_NO, REF_DATA, CONFIRM, WEB_TOKEN_TIME, SERVICE_TIME,\n" +
            "            CONFIRM_SMS_TO_CUST, SMS_TIME, CONFIRM_EMAIL_TO_MANAGER, EMAIL_TIME)" +
            "VALUES (\n" +
            "            :SC_ID, :RT_DATE, :RT_TIME, :ISSUED_DATE, :WEB_TOKEN,\n" +
            "            :TOKEN_NO, :REF_DATA, :CONFIRM, :WEB_TOKEN_TIME, :SERVICE_TIME,\n" +
            "            :CONFIRM_SMS_TO_CUST, :SMS_TIME, :CONFIRM_EMAIL_TO_MANAGER, :EMAIL_TIME)";

    public SendTokenService(EntityManager entityManager, EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManager;
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<ServiceInfoBean> getServiceData() throws Exception {
        Query q = entityManager.createNativeQuery(SERVICE_DATA, Tuple.class);
        List<Tuple> result = q.getResultList();
        List<Map<String, Object>> dataMap = getSelectQueryDataMap(result);

        List<ServiceInfoBean> list = new ArrayList<>();

        for (Map<String, Object> map : dataMap) {
            ServiceInfoBean bean = ServiceInfoBean.builder()
                    .serviceId(((Integer) map.getOrDefault("SERVICE_ID", 0)))
                    .serviceName(((String) map.getOrDefault("SERVICE_NAME", "")))
                    .alternativeName(((String) map.getOrDefault("ALTERNATIVE_NAME", "")))
                    .description(((String) map.getOrDefault("DESCRIPTION", "")))
                    .priority(((Integer) map.getOrDefault("PRIORITY", 0)))
                    .serviceCategoryId(((Integer) map.getOrDefault("SERVICE_CATEGORY_ID", 0)))
                    .state(((Integer) map.getOrDefault("STATE", 0)))
                    .scId(((Integer) map.getOrDefault("SC_ID", 0)))
                    .servicePrefix(((String) map.getOrDefault("SERVICE_PREFIX", "")))
                    .benCHMarkTime((DateUtils.convertStringToDate(map.getOrDefault("BENCHMARK_TIME", "").toString())))
                    .isDependent(((Boolean) map.getOrDefault("IS_DEPENDENT", "")))
                    .takenReceiptTopSpace(((Integer) map.getOrDefault("TKEN_RECEIPT_TOP_SPACE", 0)))
                    .enableServicePledge(((Integer) map.getOrDefault("ENABLE_SERVICE_PLEDGE", 0)))
                    .webEnable(((Boolean) map.getOrDefault("WEB_ENABLE", null)))
                    .serviceEnable(((Boolean) map.getOrDefault("SERVICE_ENABLE", null)))
                    .totalServiceDuration(((Integer) map.getOrDefault("TOTAL_SERVICE_DURATION", "")))
                    .totalService(((Integer) map.getOrDefault("TOTAL_SERVICE", 0)))
                    .serviceLocation(((String) map.getOrDefault("SERVICE_LOCATION", "")))
                    .multiServOnSameTk(((Boolean) map.getOrDefault("MULTI_SERV_ON_SAME_TK", null)))
                    .backofficeService(((Boolean) map.getOrDefault("BACKOFFICE_SERVICE", null)))
                    .type(((Boolean) map.getOrDefault("TYPE", "")))
                    .feedbackWeight(((Integer) map.getOrDefault("FEEDBACK_WEIGHT", 0)))
                    .serviceImage(((String) map.getOrDefault("SERVICE_IMAGE", "")))
                    .departmentId(((Integer) map.getOrDefault("DEPARTMENT_ID", 0)))
                    .createOn(((String) map.getOrDefault("CREATE_ON", "")))
                    .createBy(((Integer) map.getOrDefault("CREATE_BY", 0)))
                    .build();

            list.add(bean);
        }

        return  list;

    }


    public List<ServiceCenterInfo> getServiceCenterData() {
        Query q = entityManager.createNativeQuery(SERVICE_CENTER_DATA, Tuple.class);
        List<Tuple> result = q.getResultList();
        List<Map<String, Object>> dataMap = getSelectQueryDataMap(result);
        log.info("dataMap : {} ",dataMap);

        List<ServiceCenterInfo> list = new ArrayList<>();

        for (Map<String, Object> map : dataMap) {
            ServiceCenterInfo bean = ServiceCenterInfo.builder()
                    .serviceCenterId(((Integer) map.getOrDefault("SC_ID", null)))
                    .serviceCenterName(((String) map.getOrDefault("SC_NAME", "")))
                    .build();

            list.add(bean);
        }

        return  list;

    }


    @Transactional
    public void saveRemoteTokenInfo(RemoteTokenBean bean) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery(REMOTE_TOKEN_INSERT_QUERY);
        query.setParameter("SC_ID", bean.getScId());
        query.setParameter("RT_DATE", bean.getRtDate());
        query.setParameter("RT_TIME", bean.getRtTime());
        query.setParameter("ISSUED_DATE", bean.getIssuedDate());
        query.setParameter("WEB_TOKEN", bean.getWebToken());
        query.setParameter("TOKEN_NO", bean.getTokenNo());
        query.setParameter("REF_DATA", bean.getRefData());
        query.setParameter("CONFIRM", bean.getConfirm());
        query.setParameter("WEB_TOKEN_TIME", bean.getWebTokenTime());
        query.setParameter("SERVICE_TIME", bean.getServerTime());
        query.setParameter("CONFIRM_SMS_TO_CUST", bean.getConfirmSmsToCust());
        query.setParameter("SMS_TIME", bean.getSmsTime());
        query.setParameter("CONFIRM_EMAIL_TO_MANAGER", bean.getConfirmEmailToManage());
        query.setParameter("EMAIL_TIME", bean.getEmailTime());
        int executeUpdate = query
                .executeUpdate();
        entityManager.getTransaction().commit();
        System.out.println("executeUpdate => " + executeUpdate);
    }



    public List<Map<String, Object>> getSelectQueryDataMap(List<Tuple> result) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Tuple row : result) {
            // Get Column Names
            List<String> keys = new ArrayList<>();
            List<TupleElement<?>> elements = row.getElements();
            for (TupleElement<?> element : elements) {
                keys.add(element.getAlias());
            }
            Map<String, Object> map = new HashMap<>();
            for (String key : keys) {
                map.put(key, row.get(key));
            }
            list.add(map);
        }

        log.info("dataMap: {}", list);
        return list;
    }
}
