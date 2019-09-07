package com.cloudcatalogs.adauth.resources;

import com.cloudcatalogs.adauth.utils.AuthUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 10/17/18.
 */
@RestController
@RequestMapping(value = "/users")
public class UserResource {



    @ResponseBody
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity getLoggedUser(){

        Object currentUser = AuthUtils.getCurrentUser();
        return ResponseEntity.ok(currentUser);
    }

}
