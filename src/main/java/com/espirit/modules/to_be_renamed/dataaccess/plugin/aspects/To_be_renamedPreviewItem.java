package com.espirit.modules.to_be_renamed.dataaccess.plugin.aspects;

import com.espirit.modules.to_be_renamed.To_be_renamedPOJO;
import com.espirit.modules.to_be_renamed.connector.To_be_renamedConnector;
import com.espirit.modules.to_be_renamed.project.To_be_renamedProjectApp;
import com.espirit.modules.to_be_renamed.project.To_be_renamedProjectConfig;
import com.espirit.modules.to_be_renamed.util.FormUtil;
import de.espirit.common.base.Logging;
import de.espirit.firstspirit.access.BaseContext;
import de.espirit.firstspirit.access.Language;
import de.espirit.firstspirit.agency.*;
import de.espirit.firstspirit.client.plugin.report.JavaClientExecutableReportItem;
import de.espirit.firstspirit.client.plugin.report.ReportContext;
import de.espirit.firstspirit.forms.Form;
import de.espirit.firstspirit.forms.FormData;
import de.espirit.firstspirit.ui.operations.ShowFormDialogOperation;
import de.espirit.firstspirit.webedit.WebeditUiAgent;
import de.espirit.firstspirit.webedit.plugin.report.WebeditExecutableReportItem;

import javax.swing.*;
import java.io.IOException;

/**
 * Basic class to handle report icons, i.e. click event, etc.. Please see FirstSpirit API for more information.
 */
public class To_be_renamedPreviewItem implements JavaClientExecutableReportItem<To_be_renamedPOJO>, WebeditExecutableReportItem<To_be_renamedPOJO> {

    private static final Class<?> LOGGER = To_be_renamedPreviewItem.class;

    @Override
    public boolean isVisible(ReportContext<To_be_renamedPOJO> context) {
        return true;
    }

    @Override
    public boolean isEnabled(ReportContext<To_be_renamedPOJO> context) {
        return true;
    }

    @Override
    public String getLabel(ReportContext<To_be_renamedPOJO> context) {
        return null;
    }

    @Override
    public String getIconPath(ReportContext<To_be_renamedPOJO> context) {
        return null;
    }

    @Override
    public Icon getIcon(ReportContext<To_be_renamedPOJO> context) {
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
    public void execute(ReportContext<To_be_renamedPOJO> context) {

        //do something when a report icon is clicked

        To_be_renamedConnector to_be_renamedConnector = null;

        try {
            to_be_renamedConnector = to_be_renamedConnector.getInstance(context);
        } catch (IOException ioe) {
            Logging.logError("IO Exception while getting Connector.", ioe, LOGGER);
        }

        Object element = context.getObject();
        To_be_renamedPOJO object = null;
        if (element != null) {
            if (element instanceof To_be_renamedPOJO) {
                object = (To_be_renamedPOJO) element;
            }
        }

        if (context.is(BaseContext.Env.WEBEDIT)) {

            //do something when a report icon is clicked within the Content Creator
            //this shows how to open a form to collect some data using the FormUtil utility class

            final String domComponent = To_be_renamedProjectConfig.values(context, To_be_renamedProjectApp.class).getString(To_be_renamedProjectConfig.DOM_COMPONENT);
            final String textComponent = To_be_renamedProjectConfig.values(context, To_be_renamedProjectApp.class).getString(To_be_renamedProjectConfig.TEXT_COMPONENT);

            final OperationAgent operationAgent = context.requireSpecialist(OperationAgent.TYPE);
            final LanguageAgent languageAgent = context.requireSpecialist(LanguageAgent.TYPE);
            final ShowFormDialogOperation showFormDialogOperation = operationAgent.getOperation(ShowFormDialogOperation.TYPE);

            final WebeditUiAgent webEditUiAgent = context.requestSpecialist(WebeditUiAgent.TYPE);
            final Language masterLanguage = languageAgent.getMasterLanguage();

            Language previewLanguage = masterLanguage;
            if (webEditUiAgent != null) {
                previewLanguage = webEditUiAgent.getPreviewLanguage();
            }

            final String gomSource = FormUtil.getTo_be_renamedForm(context);
            final FormsAgent formsAgent = context.requireSpecialist(FormsAgent.TYPE);
            final Form form = formsAgent.getForm(gomSource);

            // Pre-populate fields:
            FormData preFormData = form.createFormData();

            String pre_dom = object.getVar();
            if (pre_dom != null) {
                preFormData.get(previewLanguage, domComponent).set(pre_dom);
            }

            String pre_text = object.getVar();
            if (pre_text != null) {
                preFormData.get(previewLanguage, textComponent).set(pre_text);
            }

            showFormDialogOperation.setDefaults(preFormData);

            showFormDialogOperation.setTitle("To_be_renamed Form");

            showFormDialogOperation.setPreselectedLanguage(previewLanguage);

            final FormData resultFormData = showFormDialogOperation.perform(form, languageAgent.getLanguages());

            if (resultFormData != null && to_be_renamedConnector != null) {

                //do something to send the collected data to the third party API using the connector
                //e.g. edit or remove an entry
            }

        } else {

            //do something when a report icon is clicked within the Site Architect

        }
    }
}