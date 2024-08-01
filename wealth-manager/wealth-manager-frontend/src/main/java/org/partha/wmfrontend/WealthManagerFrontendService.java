package org.partha.wmfrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("wmclient.client")
@SpringBootApplication
public class WealthManagerFrontendService {

    public static void main(String[] args) {
        SpringApplication.run(WealthManagerFrontendService.class);
    }
}
