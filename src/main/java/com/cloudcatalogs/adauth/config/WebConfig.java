package com.cloudcatalogs.adauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created on 2019-05-31.
 */
@Controller
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/public/admin/index.html");
        registry.addResourceHandler("/admin").addResourceLocations("classpath:/public/admin/index.html");
        registry.addResourceHandler("/admin**").addResourceLocations("classpath:/public/admin/index.html");

        registry.addResourceHandler("/**").addResourceLocations("classpath:/public/frontend/", "classpath:/public/admin/");

        registry.addResourceHandler("/").addResourceLocations("classpath:/public/frontend/index.html");

    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("/admin/index.html");
        registry.addViewController("/admin/").setViewName("/admin/index.html");
        registry.addViewController("/").setViewName("/frontend/index.html");
        registry.addViewController("").setViewName("/frontend/index.html");
    }

    @GetMapping(value = {"","/"})
    public ModelAndView home() {
        return new ModelAndView("redirect:/index.html");
    }
}
