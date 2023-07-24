package com.ba.quepro.token.system.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceCenterInfo {
    private Integer serviceCenterId;
    private String serviceCenterName;
}
