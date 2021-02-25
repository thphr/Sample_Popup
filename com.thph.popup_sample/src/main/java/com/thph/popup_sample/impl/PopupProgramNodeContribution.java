package com.thph.popup_sample.impl;


import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.xmlrpc.XmlRpcException;
import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.domain.ProgramAPI;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;


public class PopupProgramNodeContribution implements ProgramNodeContribution {

	private final ProgramAPI programAPI;
	private final ProgramAPIProvider apiProvider;
	private final PopupProgramNodeView view;
	private final DataModel model;
	
	private boolean isPopupStillEnabled;
	private Timer uiTimer;

	public PopupProgramNodeContribution(ProgramAPIProvider apiProvider, PopupProgramNodeView view, DataModel model, CreationContext context) {
	
		this.programAPI = apiProvider.getProgramAPI();
		this.apiProvider = apiProvider;
		this.view = view;
		this.model = model;

	}

	@Override
	public void openView() {
		uiTimer = new Timer(true);
		uiTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						if (getInstallation().getXmlRpcDaemonInterface().isReachable()) {
							if (!isPopupStillEnabled() || !view.getFramePopup().isVisible()) {
								try {
									if (getInstallation().getXmlRpcDaemonInterface().isEnabled()) {
										view.openPopopView(view.getProgramnnodeViewPanel());
										setPopupStillEnabled(true);
									}
								} catch (XmlRpcException e) {
									e.printStackTrace();
								} catch (UnknownResponseException e) {
									e.printStackTrace();
								}
							}
						}

					}
				});
			}
		}, 0, 1000);
	}


	@Override
	public void closeView() {
		if (uiTimer != null) {
			uiTimer.cancel();
		}
	}

	@Override
	public String getTitle() {
		return "Popup Sample";
	}

	@Override
	public boolean isDefined() {
		return true;
	}

	@Override
	public void generateScript(ScriptWriter writer) {
		writer.appendLine(getInstallation().getXMLRPCVariable() + ".showpopup()");

		writer.assign("isEnabled", "True");
		writer.appendLine("while(isEnabled == True):");
		writer.assign("isEnabled", getInstallation().getXMLRPCVariable() + ".isEnabled()");
		writer.appendLine("sync()");
		writer.sleep(0.3);
		writer.appendLine("end");

	}
	
	
	public PopupInstallationNodeContribution getInstallation() {
		return apiProvider.getProgramAPI().getInstallationNode(PopupInstallationNodeContribution.class);
	}

	public void setPopupStillEnabled(boolean isPopupStillEnabled) {
		this.isPopupStillEnabled = isPopupStillEnabled;
	}

	public boolean isPopupStillEnabled() {
		return isPopupStillEnabled;
	}

}