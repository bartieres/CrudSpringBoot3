package bartieres.crud.springboot3.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer").bearerFormat("JWT");

        Components components = new Components()
                .addSecuritySchemes("bearer-key", securityScheme);

        Info info = new Info()
                .title("Bartieres.med API")
                .description("API Rest da aplicação Bart SpringBoot3, contendo as funcionalidades de CRUD de médicos " +
                        "e de pacientes, além de agendamento e cancelamento de consultas")
                .contact(new Contact()
                        .name("Time Backend")
                        .email("backend@bart.med"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://bartieres.med/api/licenca"));

        return new OpenAPI().components(components).info(info);
    }
}
