package fr.insa.ms.Interface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InterfaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InterfaceApplication.class, args);
    }
}
