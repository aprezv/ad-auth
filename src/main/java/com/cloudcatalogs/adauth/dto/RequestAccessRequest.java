package com.cloudcatalogs.adauth.dto;

import lombok.Data;

/**
 * Created on 2019-03-21.
 */
@Data
public class RequestAccessRequest {
    private String name;
    private String email;
}
