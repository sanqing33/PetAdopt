package com.petadopt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.petadopt.mapper")
public class PetAdoptionSystemSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetAdoptionSystemSpringbootApplication.class, args);
    }

}
