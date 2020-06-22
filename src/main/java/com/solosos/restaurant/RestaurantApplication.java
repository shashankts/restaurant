package com.solosos.restaurant;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;

@SpringBootApplication
@EnableAsync
public class RestaurantApplication {

    public static void main(String[] args) throws TesseractException {
        SpringApplication.run(RestaurantApplication.class, args);

    }

}
