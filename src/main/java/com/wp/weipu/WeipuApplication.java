package com.wp.weipu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
public class WeipuApplication {
	private static final Logger logger= LoggerFactory.getLogger(WeipuApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(WeipuApplication.class, args);
		logger.info("\n--------------------------------------------\n"+
		  "|                                            |\n"+
		  "|      spring boot start                     |\n"+
		  "|                                            |\n"+
		  "---------------------------------------------");
	}
}
