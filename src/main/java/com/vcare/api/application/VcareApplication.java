/**
 * @author nipunaggarwal
 *
 */
package com.vcare.api.application;

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
@ComponentScan(basePackages = { "com.vcare" })
public class VcareApplication {

	public static void main(String[] args) {
		SpringApplication.run(VcareApplication.class, args);
	}

}
