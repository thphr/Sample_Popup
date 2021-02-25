package com.thph.popup_sample.impl;


import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class PopupProgramNodeView implements SwingProgramNodeView<PopupProgramNodeContribution> {

	public PopupProgramNodeView() {

	}

	@Override
	public void buildUI(JPanel panel, ContributionProvider<PopupProgramNodeContribution> provider) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	}


}