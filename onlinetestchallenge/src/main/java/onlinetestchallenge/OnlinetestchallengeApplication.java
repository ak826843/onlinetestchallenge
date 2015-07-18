package onlinetestchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.onlinetestchallenge.components", "com.onlinetestchallenge.controllers" })
@EnableJpaRepositories(basePackages =  { "com.onlinetestchallenge.components", "com.onlinetestchallenge.repositories" })
@EntityScan(basePackages = "com.onlinetestchallenge.models")
public class OnlinetestchallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinetestchallengeApplication.class, args);
    }
    
}
