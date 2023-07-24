package com.ba.quepro.token.system.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceInfoBean {
    private Integer serviceId;
    private String serviceName;
    private String alternativeName;
    private String description;
    private Integer priority;
    private Integer serviceCategoryId;
    private Integer state;
    private Integer scId;
    private String servicePrefix;
    private Date benCHMarkTime;
    private Boolean isDependent;
    private Integer takenReceiptTopSpace;
    private Integer enableServicePledge;
    private Integer enableServicePLEDGE;
    private Boolean webEnable;
    private Boolean serviceEnable;
    private String smsServiceCode;
    private Integer totalServiceDuration;
    private Integer totalService;
    private String serviceLocation;
    private Boolean multiServOnSameTk;
    private Boolean backofficeService;
    private Boolean type;
    private Integer feedbackWeight;
    private String serviceImage;
    private Integer departmentId;
    private String createOn;
    private Integer createBy;

}
