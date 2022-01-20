package egc.atmservice.configuration;


import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .packagesToScan("egc.atmservice.controller")
                .group("ATM Service").build();
    }
}
