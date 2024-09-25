package practices.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@AutoConfiguration
public class WebSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSpringbootApplication.class, args);
    }

}
