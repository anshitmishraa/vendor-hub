package com.project.VendorHub;

import com.project.VendorHub.constants.Constants;
import com.project.VendorHub.data.seeding.DummyVendorDataSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class VendorHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorHubApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataSeeder(DummyVendorDataSeeder dataSeeder) {
		return args -> {
			dataSeeder.seedDummyVendors();
		};
	}

}
