package com.espirit.modules.example1.dataaccess.plugin;

import com.espirit.modules.example1.dataaccess.plugin.aspects.ExampleReportItemsProvidingAspect;
import com.espirit.modules.example1.dataaccess.plugin.aspects.ExampleReportingAspect;
import com.espirit.modules.example1.dataaccess.session.ExampleDataAccessSessionBuilder;
import com.espirit.modules.example1.project.ExampleProjectApp;
import com.espirit.moddev.components.annotations.PublicComponent;
import com.espirit.modules.example1.ExamplePOJO;
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
public class ExampleDataAccessPlugin implements DataAccessPlugin<ExamplePOJO> {

	private final DataAccessAspectMap _aspects = new DataAccessAspectMap();

	private static final Class<?> LOGGER = ExampleDataAccessPlugin.class;

	@Override
	public void setUp(BaseContext context) {
		if (GenericConfigPanel.isInstalled(ExampleProjectApp.class, context)) {
			_aspects.put(Reporting.TYPE, new ExampleReportingAspect(context));
			_aspects.put(ReportItemsProviding.TYPE, new ExampleReportItemsProvidingAspect());
		}
	}

	@Override
	public void tearDown() {

	}

	@Override
	public DataAccessSessionBuilder<ExamplePOJO> createSessionBuilder() {
		return new ExampleDataAccessSessionBuilder();
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
		return "Example";
	}
}
