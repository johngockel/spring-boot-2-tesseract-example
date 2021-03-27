package de.johngockel.examples.tess;

import de.johngockel.examples.tess.config.TesseractProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TesseractProperties.class)
public class SpringBoot2TesseractExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2TesseractExampleApplication.class, args);
    }

}
