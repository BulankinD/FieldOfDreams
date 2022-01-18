package com.fieldofdreams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
@SpringBootApplication
@EnableSwagger2
@EnableRetry
public class Application
{
    public static void main(String[] args) {
        System.out.println("Hello world");
        SpringApplication.run(Application.class, args);
    }
}
