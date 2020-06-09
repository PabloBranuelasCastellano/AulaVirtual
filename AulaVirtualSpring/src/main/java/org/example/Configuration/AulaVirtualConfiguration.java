package org.example.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AulaVirtualConfiguration {

    @Bean
    public OpenAPI customOpenApi(){
        Contact contact=new Contact();
        contact.setEmail("pablo.branuelas@campusfp.es");
        contact.setName("Pablo ");


        return new OpenAPI()
            .components(new Components())
            .info(new Info()
            .title("Biblioteca Spring")
            .contact(contact)
            .version("1.0")
            .description("Biblioteca Spring con Microservicios"));
    }
}
