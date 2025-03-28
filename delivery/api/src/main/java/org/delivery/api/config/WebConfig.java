/*
package org.delivery.api.config;

import java.util.List;

import org.delivery.api.interceptor.AuthorizationInterceptor;
import org.delivery.api.resolver.UserSessionResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final AuthorizationInterceptor interceptor;
	private final UserSessionResolver userSessionResolver;

	private final List<String> OPEN_API = List.of(
		"/open-api/**"
	);

	private final List<String> DEFAULT_EXCLUDE = List.of(
		"/",
		"favicon.ico",
		"/error"
	);

	private final List<String> SWAGGER = List.of(
		"/swagger-ui.html",
		"/swagger-ui/**",
		"/v3/api-docs/**"
	);

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
			.excludePathPatterns(OPEN_API)
			.excludePathPatterns(DEFAULT_EXCLUDE)
			.excludePathPatterns(SWAGGER);
	}

	@Override
	public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(userSessionResolver);
	}

}
*/
