package com.example.msgestiondocumentv2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsGestionDocumentV2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MsGestionDocumentV2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
