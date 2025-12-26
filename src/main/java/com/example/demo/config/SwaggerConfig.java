// // package com.example.demo.config;

// // import io.swagger.v3.oas.models.OpenAPI;
// // import io.swagger.v3.oas.models.servers.Server;
// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import java.util.List;

// // @Configuration
// // public class SwaggerConfig {

// //     @Bean
// //     public OpenAPI customOpenAPI() {
// //         return new OpenAPI()
// //                 // You need to change the port as per your server
// //                 .servers(List.of(
// //                         new Server().url("https://9116.pro604cr.amypo.ai/")
                        
// //                 ));
// //         }
// // }


// package com.example.demo.config;

// import io.swagger.v3.oas.models.Components;
// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;
// import io.swagger.v3.oas.models.security.SecurityScheme;
// import io.swagger.v3.oas.models.servers.Server;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.util.List;

// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()

//                 /* ------------------------------
//                  * API Information
//                  * ------------------------------ */
//                 .info(new Info()
//                         .title("JWT Demo API")
//                         .version("1.0")
//                         .description("Simple JWT Demo Project for Students"))

//                 /* ------------------------------
//                  * Server Configuration
//                  * ------------------------------ */
//                 .servers(List.of(
//                         new Server().url("https://9116.pro604cr.amypo.ai/")
//                 ))

//                 /* ------------------------------
//                  * Security Configuration
//                  * ------------------------------ */
//                 .components(new Components()
//                         .addSecuritySchemes("bearerAuth",
//                                 new SecurityScheme()
//                                         .type(SecurityScheme.Type.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")
//                                         .description("Enter JWT token")));
//     }
// }




package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Learning Platform API")
                        .version("1.0")
                        .description("JWT secured APIs")
                )
                .addSecurityItem(
                        new SecurityRequirement().addList("BearerAuth")
                )
                .components(
                        new Components()
                                .addSecuritySchemes("BearerAuth",
                                        new SecurityScheme()
                                                .name("BearerAuth")
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}
