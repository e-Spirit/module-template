package com.espirit.modules.to_be_renamed.dataaccess.plugin;

import com.espirit.modules.to_be_renamed.dataaccess.plugin.aspects.To_be_renamedReportItemsProvidingAspect;
import com.espirit.modules.to_be_renamed.dataaccess.plugin.aspects.To_be_renamedReportingAspect;
import com.espirit.modules.to_be_renamed.dataaccess.session.To_be_renamedDataAccessSessionBuilder;
import com.espirit.modules.to_be_renamed.project.To_be_renamedProjectApp;
import com.espirit.moddev.components.annotations.PublicComponent;
import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import com.espirit.ps.psci.genericconfiguration.GenericConfigPanel;
import de.espirit.firstspirit.access.*;
import de.espirit.firstspirit.agency.Image;
import de.espirit.firstspirit.client.plugin.dataaccess.DataAccessPlugin;
import de.espirit.firstspirit.client.plugin.dataaccess.DataAccessSessionBuilder;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.DataAccessAspectMap;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.DataAccessAspectType;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.ReportItemsProviding;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.Reporting;

/**
 * Basic class to provide a Data Access Plugin that can be used within FirstSpirit, i.e. reports,
 * input components, etc..  Please see FirstSpirit API for more information.
 */

@PublicComponent(name = "DevconCloudinary1DataAccessPlugin")
public class To_be_renamedDataAccessPlugin implements DataAccessPlugin<To_be_renamedPOJO> {

	private final DataAccessAspectMap _aspects = new DataAccessAspectMap();

	private static final Class<?> LOGGER = To_be_renamedDataAccessPlugin.class;

	@Override
	public void setUp(BaseContext context) {
		if (GenericConfigPanel.isInstalled(To_be_renamedProjectApp.class, context)) {
			_aspects.put(Reporting.TYPE, new To_be_renamedReportingAspect(context));
			_aspects.put(ReportItemsProviding.TYPE, new To_be_renamedReportItemsProvidingAspect());
		}
	}

	@Override
	public void tearDown() {

	}

	@Override
	public DataAccessSessionBuilder<To_be_renamedPOJO> createSessionBuilder() {
		return new To_be_renamedDataAccessSessionBuilder();
	}

	@Override
	public <A> A getAspect(DataAccessAspectType<A> aspectType) {
		return _aspects.get(aspectType);
	}

	@Override
	public Image<?> getIcon() {
		return null;
	}

	@Override
	public String getLabel() {
		return "To_be_renamed";
	}
}
