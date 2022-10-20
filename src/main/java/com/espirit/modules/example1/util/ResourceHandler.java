package com.espirit.modules.example1.util;

import de.espirit.common.base.Logging;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceHandler {

	private static final Class<?> LOGGER = ResourceHandler.class;

	private final ResourceBundle resourceBundle;
	private final String bundleName;

	private ResourceHandler(final ResourceBundle resourceBundle, final String bundleName) {
		this.resourceBundle = resourceBundle;
		this.bundleName = bundleName;
	}

	public static ResourceHandler load(final String bundleName, final Locale locale) {
		ResourceBundle rb = ResourceBundle.getBundle(bundleName, locale);

		return new ResourceHandler(ResourceBundle.getBundle(bundleName, locale), bundleName);
	}

	public String getString(final String key) {
		try {
			return resourceBundle.getString(key);
		} catch (final MissingResourceException e) {
			Logging.logError(String.format("missing key '%s' in '%s'", key, bundleName), e, LOGGER);
			return '[' + key + ']';
		}
	}

	public String getString(final String key, final Object... params) {
		try {
			return MessageFormat.format(resourceBundle.getString(key), params);
		} catch (final MissingResourceException e) {
			Logging.logError(String.format("missing key '%s' in '%s'", key, bundleName), e, LOGGER);
			return '[' + key + ']';
		}
	}

}
