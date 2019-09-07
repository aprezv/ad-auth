package com.cloudcatalogs.adauth.dto;

import lombok.Data;

/**
 * Created on 2019-03-04.
 */
@Data
public class MenuItemRequest {
    private Long id;
    private String name;
    private Integer displayOrder;
    private MenuItemRequest parent;
    private String color;
    private String description;
    private Boolean showInNavBar;
    private String shortName;
    private String link;
    private String logoUrl;
}
