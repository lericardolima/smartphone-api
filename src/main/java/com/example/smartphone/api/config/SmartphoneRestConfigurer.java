package com.example.smartphone.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.example.smartphone.api.validator.BeforeCreateSmartphoneValidator;

@Configuration
public class SmartphoneRestConfigurer implements RepositoryRestConfigurer {
	
	@Autowired
	private BeforeCreateSmartphoneValidator beforeCreateSmartphoneValidator;

	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
		v.addValidator("beforeCreate", beforeCreateSmartphoneValidator);
		v.addValidator("beforeSave", beforeCreateSmartphoneValidator);
	}

}
