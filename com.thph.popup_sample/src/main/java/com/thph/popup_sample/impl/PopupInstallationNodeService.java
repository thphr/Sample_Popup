package com.thph.popup_sample.impl;

import java.util.Locale;

import com.thph.popup_sample.impl.MyDaemonService;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.ContributionConfiguration;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class PopupInstallationNodeService
		implements SwingInstallationNodeService<PopupInstallationNodeContribution, PopupInstallationNodeView> {
	
	private final MyDaemonService myDaemonService;

	public PopupInstallationNodeService(MyDaemonService myDaemonService) {

		this.myDaemonService = myDaemonService; 
	
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {

	}

	@Override
	public String getTitle(Locale locale) {
		return "Popup Daemon Service";
	}

	@Override
	public PopupInstallationNodeView createView(ViewAPIProvider apiProvider) {
		return new PopupInstallationNodeView(apiProvider);
	}

	@Override
	public PopupInstallationNodeContribution createInstallationNode(InstallationAPIProvider apiProvider,
			PopupInstallationNodeView view, DataModel model, CreationContext context) {
		return new PopupInstallationNodeContribution(apiProvider, view, model, context,this.myDaemonService);
	}

}