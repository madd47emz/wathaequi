package com.example.msgestiondocument;

import com.example.msgestiondocument.Dao.PaperRepo;
import com.example.msgestiondocument.Entity.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class MsGestionDocumentApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(MsGestionDocumentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}
