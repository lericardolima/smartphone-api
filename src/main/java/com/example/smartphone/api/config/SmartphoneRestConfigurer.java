package com.example.smartphone.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.example.smartphone.api.validator.BeforeCreateSmartphoneValidator;

@Configuration
public class SmartphoneRestConfigurer implements RepositoryRestConfigurer {

	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
		v.addValidator("beforeCreate", new BeforeCreateSmartphoneValidator());
		v.addValidator("beforeSave", new BeforeCreateSmartphoneValidator());
	}

}
