package com.example.smartphone.api.config;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LocaleConfiguration implements WebMvcConfigurer {

	@Bean
	public LocaleResolver localeResolver() {
		return new RequestLocaleResolver();
	}

	@Bean
	public MessageSource messageSource() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/locale/messages");
		messageSource.setDefaultLocale(Locale.US);
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		return messageSource;
	}
}
