package com.espirit.modules.example1.util;

import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.agency.Image;
import de.espirit.firstspirit.agency.ImageAgent;

import javax.swing.*;

/**
 * Utility class that provides icons for buttons. Depending on the environment, SiteArchitect or ContentCreator,
 * matching formats are returned.
 */
public class ExampleIcons {

	/**
	 * The constant that contains the SiteArchitect pattern path to the icons.
	 */
	private static final String JC_PATTERN = "/example/%s.png";
	/**
	 * The constant that contains the ContentCreator pattern path to the icons.
	 */
	private static final String WE_PATTERN = "example/%s.png";

	private ExampleIcons() {
		throw new IllegalStateException("Utility class");
	}

	private static Icon getIcon(String baseName) {
		return new ImageIcon(ExampleIcons.class.getResource(String.format(JC_PATTERN, baseName)));
	}

	private static Image<?> getImageIcon(BaseContext context, String baseName) {
		try {
			ImageAgent imageAgent = context.requireSpecialist(ImageAgent.TYPE);
			if (context.is(BaseContext.Env.WEBEDIT)) {
				return imageAgent.getImageFromUrl(String.format(WE_PATTERN, baseName));
			} else {
				return imageAgent.getImageFromIcon(getIcon(baseName));
			}
		} catch (Exception e) {
			context.logError(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Gets an icon for an active button.
	 *
	 * @param context the FirstSpirit context
	 * @return the active Image
	 */
	public static Image<?> getActive(BaseContext context) {
		return getImageIcon(context, "cp-flag");
	}

	/**
	 * Gets an icon for an inactive button
	 *
	 * @param context the FirstSpirit context
	 * @return the inactive Image
	 */
	public static Image<?> getInactive(BaseContext context) {
		return getImageIcon(context, "cp-flag");
	}

	/**
	 * Gets posting icon.
	 *
	 * @param context the FirstSpirit context
	 * @return the posting Image
	 */
	public static Image<?> getPosting(BaseContext context) {
		return getImageIcon(context, "cp-flag");
	}
}
