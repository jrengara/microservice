package com.microservice.restful;

import org.springframework.context.annotation.*;
import org.springframework.hateoas.client.*;
import org.springframework.hateoas.mediatype.collectionjson.*;
import org.springframework.plugin.core.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("JP", "jp.com", "jp@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Api Documentation", "Api Documentation",
            "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0");

    public static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>
            (Arrays.asList("application/json","application/xml"));


    @Primary
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

    @Bean
    public Docket api(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO).
                        produces(DEFAULT_PRODUCES_AND_CONSUMES).consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }


}
