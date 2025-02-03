package MusicShow.ProjetoWeb;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi apiGroup() {
        return GroupedOpenApi.builder()
                .group("musico-api")
                .pathsToMatch("/api/**") // Inclui todos os endpoints relacionados a musicos
                .build();
    }

    @Bean
    public io.swagger.v3.oas.models.OpenAPI customOpenAPI() {
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info()
                        .title("API Music Show - Cadastro de Músicos")
                        .description("Sistema de cadastro de músicos")
                        .version("V1")
                        .contact(new Contact()
                                .name(" ")
                                .url(" ")
                                .email(" ")));
    }
}
