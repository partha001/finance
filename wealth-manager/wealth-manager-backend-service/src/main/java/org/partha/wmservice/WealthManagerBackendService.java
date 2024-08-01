package org.partha.wmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("org.partha.wmcommon.*")
@EnableJpaRepositories("org.partha.wmservice")
@SpringBootApplication
public class WealthManagerBackendService {

    public static void main(String[] args) {
        SpringApplication.run(WealthManagerBackendService.class);
    }
}
