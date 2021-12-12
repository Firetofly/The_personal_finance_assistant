/*
 * Copyright (c)
 */

package firefly;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@ComponentScan(basePackages = "firefly")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Application {

    public static final Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        logger.info("Application has been started");
    }

}
