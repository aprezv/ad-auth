package com.cloudcatalogs.adauth.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * Created on 2019-01-26.
 */
@Data
public class PasswordChangeRequest {
    private String oldPassword;
    @Length(min = 6, max = 255)
    @NotBlank
    private String newPassword;
}
