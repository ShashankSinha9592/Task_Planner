package org.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Task Scheduler API", version = "1.0", description = "Task Scheduler Information"),
        servers = {
                @Server(url = "/", description = "Task Scheduler Swagger URL")
        }
)
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
}