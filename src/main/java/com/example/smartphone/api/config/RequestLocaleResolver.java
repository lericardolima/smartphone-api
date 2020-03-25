package com.example.smartphone.api.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

public class RequestLocaleResolver extends AcceptHeaderLocaleResolver {

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String locale = request.getHeader("Accept-Language");
		if (!StringUtils.hasText(locale)) {
			return Locale.US;
		}

		return Locale.forLanguageTag(locale);
	}
}