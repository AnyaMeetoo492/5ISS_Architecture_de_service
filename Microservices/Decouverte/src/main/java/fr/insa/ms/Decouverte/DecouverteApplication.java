package fr.insa.ms.Decouverte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DecouverteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DecouverteApplication.class, args);
	}

}
