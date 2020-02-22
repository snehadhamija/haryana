/**
 * @author nipunaggarwal
 *
 */
package com.haryanaindustries.api.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author nipunaggarwal
 *
 */
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = { "com.haryanaindustries" })
public class HaryanaindustriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaryanaindustriesApplication.class, args);
	}

}
