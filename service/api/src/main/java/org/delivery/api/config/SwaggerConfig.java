package org.delivery.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.core.jackson.ModelResolver;

@Configuration
public class SwaggerConfig {

	@Bean
	public ModelResolver modelResolver(final ObjectMapper objectMapper) {
		return new ModelResolver(objectMapper);
	}

}
