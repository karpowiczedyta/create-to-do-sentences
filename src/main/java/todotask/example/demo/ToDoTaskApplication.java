package todotask.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.Validator;

@EnableAsync
@EnableConfigurationProperties(TaskConfigurationProperties.class)
@SpringBootApplication
public class ToDoTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoTaskApplication.class, args);
    }

    @Bean
    Validator validator() {
        return new LocalValidatorFactoryBean();
    }

}
