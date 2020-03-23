package com.example.smartphone.api.validator;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.smartphone.api.model.Smartphone;

public class BeforeCreateSmartphoneValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Smartphone.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Smartphone smartphone = (Smartphone) target;

		if (checkIfBlank(smartphone.getCode())) {
			errors.rejectValue("code", null, "Deve ser informado o código do celular.");
		} else if (checkLength(smartphone.getCode(), 8, 8)) {
			errors.rejectValue("code", null, "O código do celular deve ter 8 caracteres.");
		}

		if (checkIfBlank(smartphone.getModel())) {
			errors.rejectValue("model", null, "Deve ser informado o modelo do celular.");
		} else if (checkLength(smartphone.getModel(), 2, 255)) {
			errors.rejectValue("model", null, "O modelo do celular deve ter entre 2 e 255 caracteres.");
		}

		if (checkIfNull(smartphone.getPrice())) {
			errors.rejectValue("price", null, "Deve ser informado o preço do celular.");
		} else if (checkIfNegative(smartphone.getPrice())) {
			errors.rejectValue("price", null, "O preço não deve ser menor que 0.");
		}

		if (checkIfBlank(smartphone.getBrand())) {
			errors.rejectValue("brand", null, "Deve ser informado o nome da marca do celular.");
		} else if (checkLength(smartphone.getBrand(), 2, 255)) {
			errors.rejectValue("brand", null, "O nome da marca deve ter entre 2 e 255 caracteres.");
		}

		if (checkIfBlank(smartphone.getPhoto())) {
			errors.rejectValue("photo", null, "Deve ser informado a URL da imagem do celular.");
		} else if (checkLength(smartphone.getPhoto(), 2, 255)) {
			errors.rejectValue("photo", null, "A URL da imagem deve ter entre 2 e 255 caracteres.");
		}

		if (checkIfNull(smartphone.getStartDate())) {
			errors.rejectValue("startDate", null, "Deve ser informada a data de início da venda do celular.");
		} else if (checkIfBefore(smartphone.getStartDate(), LocalDate.of(2018, Month.DECEMBER, 25))) {
			errors.rejectValue("startDate", null, "A data de início da venda não deve ser anterior à 25/12/2018.");
		}

		if (checkIfNull(smartphone.getEndDate())) {
			errors.rejectValue("endDate", null, "Deve ser informada a data final da venda do celular.");
		} else if (checkIfNull(smartphone.getStartDate())
				|| checkIfBefore(smartphone.getEndDate(), smartphone.getStartDate())) {
			errors.rejectValue("endDate", null, "A data final da venda deve não deve ser anterior à data de início.");
		}

		if (checkIfNull(smartphone.getColor())) {
			errors.rejectValue("color", null, "Deve ser informada a cor do celular.");
		}
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
