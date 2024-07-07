package com.algaworks.algafood.core.openapi;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.openapi.model.CozinhasModelOpenApi;
import com.algaworks.algafood.api.openapi.model.PageableModelOpenApi;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.core.util.AnnotationsUtils;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.modelmapper.internal.typetools.TypeResolver;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class SpringDocConfig implements WebMvcConfigurer {

//    @Bean
//    public GroupedOpenApi apiDocket() {
//        return GroupedOpenApi.builder()
//                .group("springshop-public")
//                .pathsToMatch("/public/**")
//                .build();
//    }

    @Bean
    public OpenAPI springAlgafoodOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Algafood API Documentation")
                        .description("Algafood API Documentation for example view")
                        .version("v0.0.1"));
    }

    @Bean
    public OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
        ResolvedSchema resolvedSchema = ModelConverters.getInstance()
                .resolveAsResolvedSchema(new AnnotatedType(Problem.class));
        SpringDocUtils.getConfig()
                .replaceWithClass(Pageable.class, PageableModelOpenApi.class)
                .replaceWithClass(TypeResolver.resolveRawClass(Page.class, CozinhaModel.class), CozinhasModelOpenApi.class);

        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations()
                    .forEach(operation -> addErrorToApi(operation, openApi.getComponents())));
            openApi.schema(resolvedSchema.schema.getName(), resolvedSchema.schema);
        };
    }

    private void addErrorToApi(Operation operation, Components components) {
        MediaType mediaType = new MediaType().schema(AnnotationsUtils.resolveSchemaFromType(Problem.class, components,
                null));

        operation.getResponses()
                .addApiResponse("500", new ApiResponse()
                        .description("Erro interno no servidor")
                        .content(new Content().addMediaType(APPLICATION_JSON_VALUE, mediaType))
                )
                .addApiResponse( "400", new ApiResponse()
                        .description("Requisição inválida (erro do cliente)")
                        .content(new Content().addMediaType(APPLICATION_JSON_VALUE, mediaType))
                )
                .addApiResponse("406", new ApiResponse()
                        .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .content(new Content().addMediaType(APPLICATION_JSON_VALUE, mediaType))
                )
                .addApiResponse("415", new ApiResponse()
                        .description("Requisição recusada porque o corpo está em um formato não suportado")
                        .content(new Content().addMediaType(APPLICATION_JSON_VALUE, mediaType))
                );
    }
}