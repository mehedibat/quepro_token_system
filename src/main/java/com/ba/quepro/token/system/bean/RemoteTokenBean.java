package com.ba.quepro.token.system.bean;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RemoteTokenBean {
    private Integer scId;
    private String rtDate;
    private String rtTime;
    private String issuedDate;
    private String webToken;
    private String tokenNo;
    private String refData;
    private Boolean confirm;
    private String webTokenTime;
    private String serverTime;
    private Boolean confirmSmsToCust;
    private String smsTime;
    private Boolean confirmEmailToManage;
    private String emailTime;
}
