package com.wust.spring.boot.multi.tenant.demo.model;

import com.wust.spring.boot.multi.tenant.demo.contant.TenantStatus;
import lombok.Data;

@Data
public class Tenant {
    private String name;
    private String description;
    private TenantStatus status;
    private Long tenantId;
}
