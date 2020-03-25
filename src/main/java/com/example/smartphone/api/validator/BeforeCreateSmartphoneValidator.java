package com.example.smartphone.api.validator;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.smartphone.api.model.Smartphone;

@Component
public class BeforeCreateSmartphoneValidator implements Validator {

	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean supports(Class<?> clazz) {
		return Smartphone.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Smartphone smartphone = (Smartphone) target;

		if (checkIfBlank(smartphone.getCode())) {
			errors.rejectValue("code", null, getMessage("smartphone.code.validation.not.empty"));
		} else if (checkLength(smartphone.getCode(), 8, 8)) {
			errors.rejectValue("code", null, getMessage("smartphone.code.validation.length"));
		}

		if (checkIfBlank(smartphone.getModel())) {
			errors.rejectValue("model", null, getMessage("smartphone.model.validation.not.empty"));
		} else if (checkLength(smartphone.getModel(), 2, 255)) {
			errors.rejectValue("model", null, getMessage("smartphone.model.validation.length"));
		}

		if (checkIfNull(smartphone.getPrice())) {
			errors.rejectValue("price", null, getMessage("smartphone.price.validation.not.empty"));
		} else if (checkIfNegative(smartphone.getPrice())) {
			errors.rejectValue("price", null, getMessage("smartphone.price.validation.not.negative"));
		}

		if (checkIfBlank(smartphone.getBrand())) {
			errors.rejectValue("brand", null, getMessage("smartphone.brand.validation.not.empty"));
		} else if (checkLength(smartphone.getBrand(), 2, 255)) {
			errors.rejectValue("brand", null, getMessage("smartphone.brand.validation.length"));
		}

		if (checkIfBlank(smartphone.getPhoto())) {
			errors.rejectValue("photo", null, getMessage("smartphone.photo.validation.not.empty"));
		} else if (checkLength(smartphone.getPhoto(), 2, 255)) {
			errors.rejectValue("photo", null, getMessage("smartphone.photo.validation.length"));
		}

		if (checkIfNull(smartphone.getStartDate())) {
			errors.rejectValue("startDate", null, getMessage("smartphone.start_date.validation.not.empty"));
		} else if (checkIfBefore(smartphone.getStartDate(), LocalDate.of(2018, Month.DECEMBER, 25))) {
			errors.rejectValue("startDate", null, getMessage("smartphone.start_date.validation.invalid.value"));
		}

		if (checkIfNull(smartphone.getEndDate())) {
			errors.rejectValue("endDate", null, getMessage("smartphone.end_date.validation.not.empty"));
		} else if (checkIfNull(smartphone.getStartDate())
				|| checkIfBefore(smartphone.getEndDate(), smartphone.getStartDate())) {
			errors.rejectValue("endDate", null, getMessage("smartphone.end_date.validation.invalid.value"));
		}

		if (checkIfNull(smartphone.getColor())) {
			errors.rejectValue("color", null, getMessage("smartphone.color.validation.not.empty"));
		}
	}

	private String getMessage(String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}

	private boolean checkIfBefore(LocalDate date, LocalDate limit) {
		return date == null || limit == null || date.isBefore(limit);
	}

	private boolean checkIfNegative(Float value) {
		return value < 0;
	}

	private boolean checkIfNull(Object obj) {
		return obj == null;
	}

	private boolean checkLength(String value, int min, int max) {
		return value == null || min > value.length() || max < value.length();
	}

	private boolean checkIfBlank(String value) {
		return value == null || value.trim().isEmpty();
	}

}
