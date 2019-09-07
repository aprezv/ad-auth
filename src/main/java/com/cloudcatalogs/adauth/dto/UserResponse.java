package com.cloudcatalogs.adauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 10/17/18.
 */
@Data
public class UserResponse {
    @JsonIgnore
    private Long id;
    private String name;
    private String email;
    private String profileImageUrl;
    private Map<String, String> details;
    private List<String> roles;
    private Boolean changePasswordAtNextLogon;

}
