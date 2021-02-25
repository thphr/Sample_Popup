package com.thph.popup_sample.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.ContributionConfiguration;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class PopupInstallationNodeService
		implements SwingInstallationNodeService<PopupInstallationNodeContribution, PopupInstallationNodeView> {

	public PopupInstallationNodeService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return "Popup sample";
	}

	@Override
	public PopupInstallationNodeView createView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated method stub
		return new PopupInstallationNodeView(apiProvider);
	}

	@Override
	public PopupInstallationNodeContribution createInstallationNode(InstallationAPIProvider apiProvider,
			PopupInstallationNodeView view, DataModel model, CreationContext context) {
		// TODO Auto-generated method stub
		return new PopupInstallationNodeContribution(apiProvider, view, model, context);
	}

}