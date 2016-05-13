package org.courseregistration.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
class BaseController extends WebMvcConfigurerAdapter {
    /*@Bean
    RelProvider relProvider() {
        new JsonRootRelProvider();
    }*/
}
