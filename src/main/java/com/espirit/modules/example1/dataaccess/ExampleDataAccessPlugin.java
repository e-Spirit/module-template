package com.espirit.modules.example1.dataaccess;

import com.espirit.modules.example1.connector.ExampleConnector;
import com.espirit.modules.example1.util.ExampleIcons;
import com.espirit.modules.example1.project.ExampleProjectApp;
import com.espirit.modules.example1.project.ExampleProjectConfig;
import com.espirit.moddev.components.annotations.PublicComponent;
import com.espirit.modules.example1.ExamplePOJO;
import com.espirit.modules.example1.util.FormUtil;
import com.espirit.ps.psci.genericconfiguration.GenericConfigPanel;
import de.espirit.common.base.Logging;
import de.espirit.firstspirit.access.*;
import de.espirit.firstspirit.agency.*;
import de.espirit.firstspirit.agency.Image;
import de.espirit.firstspirit.client.plugin.dataaccess.DataAccessPlugin;
import de.espirit.firstspirit.client.plugin.dataaccess.DataAccessSessionBuilder;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.DataAccessAspectMap;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.DataAccessAspectType;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.ReportItemsProviding;
import de.espirit.firstspirit.client.plugin.dataaccess.aspects.Reporting;
import de.espirit.firstspirit.client.plugin.report.JavaClientExecutableReportItem;
import de.espirit.firstspirit.client.plugin.report.ReportContext;
import de.espirit.firstspirit.client.plugin.report.ReportItem;
import de.espirit.firstspirit.forms.Form;
import de.espirit.firstspirit.forms.FormData;
import de.espirit.firstspirit.ui.operations.ShowFormDialogOperation;
import de.espirit.firstspirit.webedit.WebeditUiAgent;
import de.espirit.firstspirit.webedit.plugin.report.WebeditExecutableReportItem;

import javax.swing.*;
import java.io.*;
import java.util.*;

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
		return new ExampleDataAccessSession.Builder();
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

	/**
	 * Basic class to set up a Content Creator Report. Please see FirstSpirit API for more information.
	 */
	public static class ExampleReportingAspect implements Reporting {

		private final BaseContext _context;

		private ExampleReportingAspect(BaseContext context) {
			_context = context;
		}

		@Override
		public Image<?> getReportIcon(boolean active) {

			if (_context.is(BaseContext.Env.WEBEDIT)) {
				return active ? ExampleIcons.getActive(_context) : ExampleIcons.getInactive(_context);
			} else {
				return active ? ExampleIcons.getInactive(_context) : ExampleIcons.getActive(_context);
			}
		}
	}

	public static class ExampleReportItemsProvidingAspect implements ReportItemsProviding<ExamplePOJO> {

		private final ExamplePreviewItem _clickItem;

		private ExampleReportItemsProvidingAspect() {
			_clickItem = new ExamplePreviewItem();
		}

		@Override
		public ReportItem<ExamplePOJO> getClickItem() {
			return _clickItem;
		}

		@Override
		public Collection<? extends ReportItem<ExamplePOJO>> getItems() {
			return Collections.emptyList();
		}

		/**
		 * Basic class to handle report icons, i.e. click event, etc.. Please see FirstSpirit API for more information.
		 */
		static class ExamplePreviewItem implements JavaClientExecutableReportItem<ExamplePOJO>, WebeditExecutableReportItem<ExamplePOJO> {

			@Override
			public boolean isVisible(ReportContext<ExamplePOJO> context) {
				return true;
			}

			@Override
			public boolean isEnabled(ReportContext<ExamplePOJO> context) {
				return true;
			}

			@Override
			public String getLabel(ReportContext<ExamplePOJO> context) {
				return null;
			}

			@Override
			public String getIconPath(ReportContext<ExamplePOJO> context) {
				return null;
			}

			@Override
			public Icon getIcon(ReportContext<ExamplePOJO> context) {
				return null;
			}

			/**
			 * This method is used to specify the performed action when a report item is clicked.
			 * Here, an overlay window with a video preview is shown when in Content Creator or a Browser window
			 * otherwise.
			 *
			 * @param context
			 */
			@Override
			public void execute(ReportContext<ExamplePOJO> context) {

				//do something when a report icon is clicked

				ExampleConnector exampleConnector = null;

				try {
					exampleConnector = ExampleConnector.getInstance(context);
				} catch (IOException ioe) {
					Logging.logError("IO Exception while getting Connector.", ioe, LOGGER);
				}

				Object element = context.getObject();
				ExamplePOJO example = null;
				if (element != null) {
					if (element instanceof ExamplePOJO) {
						example = (ExamplePOJO) element;
					}
				}

				if (context.is(BaseContext.Env.WEBEDIT)) {

					//do something when a report icon is clicked within the Content Creator
					//this example shows how to open a form to collect some data using the FormUtil utility class

					final String domComponent = ExampleProjectConfig.values(context, ExampleProjectApp.class).getString(ExampleProjectConfig.DOM_COMPONENT);
					final String textComponent = ExampleProjectConfig.values(context, ExampleProjectApp.class).getString(ExampleProjectConfig.TEXT_COMPONENT);

					final OperationAgent operationAgent = context.requireSpecialist(OperationAgent.TYPE);
					final LanguageAgent languageAgent = context.requireSpecialist(LanguageAgent.TYPE);
					final StoreElementAgent storeElementAgent = context.requireSpecialist(StoreElementAgent.TYPE);
					final StoreAgent storeAgent = context.requireSpecialist(StoreAgent.TYPE);
					final ResolutionAgent resolutionAgent = context.requireSpecialist(ResolutionAgent.TYPE);
					final ShowFormDialogOperation showFormDialogOperation = operationAgent.getOperation(ShowFormDialogOperation.TYPE);

					final WebeditUiAgent webEditUiAgent = context.requestSpecialist(WebeditUiAgent.TYPE);
					final Language masterLanguage = languageAgent.getMasterLanguage();

					Language previewLanguage = masterLanguage;
					if (webEditUiAgent != null) {
						previewLanguage = webEditUiAgent.getPreviewLanguage();
					}

					final String gomSource = FormUtil.getExampleForm(context);
					final FormsAgent formsAgent = context.requireSpecialist(FormsAgent.TYPE);
					final Form form = formsAgent.getForm(gomSource);

					// Pre-populate fields:
					FormData preFormData = form.createFormData();

					String pre_dom = example.getVar();
					if (pre_dom != null) {
						preFormData.get(previewLanguage, domComponent).set(pre_dom);
					}

					String pre_text = example.getVar();
					if (pre_text != null) {
						preFormData.get(previewLanguage, textComponent).set(pre_text);
					}

					showFormDialogOperation.setDefaults(preFormData);

					showFormDialogOperation.setTitle("Some Example Form");

					showFormDialogOperation.setPreselectedLanguage(previewLanguage);

					final FormData resultFormData = showFormDialogOperation.perform(form, languageAgent.getLanguages());

					if (resultFormData != null && exampleConnector != null) {

						//do something to send the collected data to the third party API using the connector
						//e.g. edit or remove an entry
					}

				} else {

					//do something when a report icon is clicked within the Site Architect

				}
			}
		}
	}
}