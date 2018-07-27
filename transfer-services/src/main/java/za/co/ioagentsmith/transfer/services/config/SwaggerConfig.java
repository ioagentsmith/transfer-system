package za.co.ioagentsmith.transfer.services.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Component
public class SwaggerConfig {

    @Value("${server.servlet.context-path}")
    private String ROOT_CONTEXT;

    @Bean
    public Docket getCommunityApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("transfer-system")
                .select()
                .apis(RequestHandlerSelectors.basePackage("za.co.ioagentsmith.transfer.services.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
