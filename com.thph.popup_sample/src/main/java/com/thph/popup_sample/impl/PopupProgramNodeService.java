package com.thph.popup_sample.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;


public class PopupProgramNodeService implements SwingProgramNodeService<PopupProgramNodeContribution, PopupProgramNodeView> {

	@Override
	public String getId() {

		return "popup_sample";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {

		configuration.setChildrenAllowed(false);
	
	}

	@Override
	public String getTitle(Locale locale) {
	
		return "Popup Sample";
	}

	@Override
	public PopupProgramNodeView createView(ViewAPIProvider apiProvider) {
		return new PopupProgramNodeView();

	}

	@Override
	public PopupProgramNodeContribution createNode(ProgramAPIProvider apiProvider, PopupProgramNodeView view,
			DataModel model, CreationContext context) {

		return new PopupProgramNodeContribution(apiProvider, view, model, context);
	}

}