package com.espirit.modules.to_be_renamed.util;

import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.agency.Image;
import de.espirit.firstspirit.agency.ImageAgent;

import javax.swing.*;

/**
 * Utility class that provides icons for buttons. Depending on the environment, SiteArchitect or ContentCreator,
 * matching formats are returned.
 */
public class To_be_renamedIcons {

	/**
	 * The constant that contains the SiteArchitect pattern path to the icons.
	 */
	private static final String JC_PATTERN = "/to_be_renamed/%s.png";
	/**
	 * The constant that contains the ContentCreator pattern path to the icons.
	 */
	private static final String WE_PATTERN = "to_be_renamed/%s.png";

	private To_be_renamedIcons() {
		throw new IllegalStateException("Utility class");
	}

	private static Icon getIcon(String baseName) {
		return new ImageIcon(To_be_renamedIcons.class.getResource(String.format(JC_PATTERN, baseName)));
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
	 * Gets snippet icon.
	 *
	 * @param context the FirstSpirit context
	 * @return the snippet Image
	 */
	public static Image<?> getSnippet(BaseContext context) {
		return getImageIcon(context, "cp-flag");
	}
}
