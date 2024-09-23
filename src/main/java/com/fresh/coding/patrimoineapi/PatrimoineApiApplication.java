package com.fresh.coding.patrimoineapi;

import com.fresh.coding.patrimoineapi.model.Patrimoine;
import com.fresh.coding.patrimoineapi.services.PatrimoineService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatrimoineApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatrimoineApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatrimoineService patrimoineService) {
        return args -> {
            Patrimoine foundPatrimoine1 = patrimoineService.findByName("Bob");
            System.out.println(foundPatrimoine1);
        };
    }
}
