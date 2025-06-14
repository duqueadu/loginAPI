package com.prueba.loginAPI.infrastructure.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.media.*;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";
    private static final String PUBLIC_API_GROUP = "public-endpoints";
    private static final String PRIVATE_API_GROUP = "private-endpoints";
    private static final String SESSION_VALIDATION_SCHEME = "sessionAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API LOGIN")
                        .version("1.0.0")
                        .description("""
                            ## Flujo de Autenticación
                            
                            ###  Endpoints Públicos:
                            - POST /api/authorization/CrearUsuario
                            - POST /api/authorization/login
                            
                            ###  Endpoints Protegidos:
                            - Todos los demás endpoints bajo /api/**
                             USUARIOS DE PRUEBA 
                                {usuario = prueba2@prueba.com,clve = 12345678}
                                {usuario = prueba1@prueba.com,clave =12345678}

                            ###  Instrucciones:
                            1. Registrar usuario en /api/authorization/CrearUsuario
                            2. Iniciar sesión en /api/authorization/login
                            3. Introducir el token JWT recibido en el botón Authorize
                            4. Acceder a los endpoints protegidos
                            
                            ### 🛡 Validación de Sesión:
                            Todos los endpoints protegidos validan:
                            - Token JWT válido en el header Authorization
                            - Sesión activa con token almacenado
                            """))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Token JWT obtenido al iniciar sesión"))
                        .addSecuritySchemes(SESSION_VALIDATION_SCHEME,
                                new SecurityScheme()
                                        .name(SESSION_VALIDATION_SCHEME)
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.COOKIE)
                                        .name("JSESSIONID")
                                        .description("ID de sesión del servidor")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group(PUBLIC_API_GROUP)
                .pathsToMatch(
                        "/api/authorization/CrearUsuario",
                        "/api/authorization/logout",
                        "/api/authorization/login"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi privateApi() {
        return GroupedOpenApi.builder()
                .group(PRIVATE_API_GROUP)
                .pathsToMatch("/api/**")
                .pathsToExclude(
                        "/api/authorization/CrearUsuario",
                        "/api/authorization/logout",
                        "/api/authorization/login"
                )
                .addOpenApiCustomizer(privateApiCustomizer())
                .build();
    }

    private OpenApiCustomizer privateApiCustomizer() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> {
                pathItem.readOperations().forEach(operation -> {
                    // Configurar seguridad JWT + Sesión
                    operation.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
                    operation.addSecurityItem(new SecurityRequirement().addList(SESSION_VALIDATION_SCHEME));

                    // Mejorar descripción con detalles de validación
                    String sessionValidationDesc = """
                            \n\n🔐 **Validaciones Requeridas:**
                            - **Token JWT**: En header Authorization (Bearer token)
                            - **Sesión Activa**: Token almacenado en sesión del servidor
                            - **Códigos de Error:**
                              - `401 Unauthorized`: Token inválido o sesión expirada
                              - `403 Forbidden`: Token válido pero sin permisos suficientes
                            """;

                    operation.setDescription((operation.getDescription() == null ? "" : operation.getDescription()) + sessionValidationDesc);

                    // Crear el objeto MediaType correctamente
                    MediaType mediaType = new MediaType();
                    mediaType.example(SwaggerConfig.createSessionErrorResponse());

                    // Crear el objeto Content correctamente
                    Content content = new Content();
                    content.addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, mediaType);

                    // Añadir posibles respuestas de error
                    operation.getResponses().addApiResponse(
                            String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            new ApiResponse()
                                    .description("Token inválido o sesión no activa")
                                    .content(content)
                    );
                });
            });
        };
    }

    /**
     * Método para crear respuesta de error de sesión consistente
     */
    public static Map<String, Object> createSessionErrorResponse() {
        return Map.of(
                "status", "error",
                "code", HttpStatus.UNAUTHORIZED.value(),
                "message", "Acceso no autorizado",
                "details", "No hay sesión activa o el token es inválido",
                "solution", "Por favor inicie sesión en /api/authorization/login",
                "documentation", "/swagger-ui.html#/authorization-controller/loginUsingPOST"
        );
    }
}