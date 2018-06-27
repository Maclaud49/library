package com.parlow.library.webservice.bootstrap;

import com.parlow.library.webservice.service.impl.AuthImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
@ComponentScan("com.parlow.library")
@ImportResource("classpath:/bootstrapContext.xml")
public class SpringConfiguration {

    private static final Logger logger = LogManager.getLogger(AuthImpl.class);



}
