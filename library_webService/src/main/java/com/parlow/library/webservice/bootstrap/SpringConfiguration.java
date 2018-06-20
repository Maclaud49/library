package com.parlow.library.webservice.bootstrap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
@ComponentScan("com.parlow.library")
@ImportResource("classpath:/bootstrapContext.xml")
public class SpringConfiguration {

}
