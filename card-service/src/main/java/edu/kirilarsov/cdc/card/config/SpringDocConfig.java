package edu.kirilarsov.cdc.card.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDocConfig class for defining spring doc configuration.
 */
@Configuration
public class SpringDocConfig {

    /**
     * Custom open API configuration.
     *
     * @param appVersion     version info from main pom
     * @param appDescription description from main pom
     * @return custom OpenApi config
     */
    @Bean
    public OpenAPI customOpenApi(@Value("${application-version}") String appVersion,
                                 @Value("${application-description}") String appDescription) {
        return new OpenAPI().info(new Info().title("CDC API")
            .version(appVersion)
            .description(appDescription)
            .license(
                new License().name("CDC, All Rights reserved")
                    .url("https://www.apache.org/licenses/LICENSE-2.0")
            )
            .contact(
                new Contact().name("CDC development team")
                    .url("https://www.cdc.com")
                    .email("cdc@cdc.com")
            )
        );
    }

}
