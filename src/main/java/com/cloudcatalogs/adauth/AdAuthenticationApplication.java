package com.cloudcatalogs.adauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan({
        "com.cloudcatalogs.commons.exceptions.config",
        "com.cloudcatalogs.commons.config",
        "com.cloudcatalogs.userauthentication.services",
        "com.cloudcatalogs.userauthentication.exceptions",
        "com.cloudcatalogs.userauthentication.configs",
        "com.cloudcatalogs.fileuploader.services",
        "com.cloudcatalogs.adauth",
        "com.cloudcatalogs.emailsender.services"
})
@EntityScan(basePackages = {
        "com.cloudcatalogs.userauthentication.entities",
        "com.cloudcatalogs.portalapi.entities"
})
@EnableJpaRepositories(basePackages = {
        "com.cloudcatalogs.userauthentication.repositories",
})
@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy
@EnableScheduling
public class AdAuthenticationApplication {

    public static void main(String[] args) {

        SpringApplication.run(AdAuthenticationApplication.class, args);
    }

}
