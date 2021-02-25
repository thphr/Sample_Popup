package com.thph.popup_sample.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.ur.urcap.api.contribution.DaemonService;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;

/**
 * Hello world activator for the OSGi bundle URCAPS contribution
 *
 */
public class Activator implements BundleActivator {
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Activator says Hello Popup Sample!");
		
		MyDaemonService myDaemonService = new MyDaemonService();
		PopupInstallationNodeService popupInstallationNodeService = new PopupInstallationNodeService(myDaemonService);
		
		bundleContext.registerService(DaemonService.class, myDaemonService, null);
		bundleContext.registerService(SwingInstallationNodeService.class,popupInstallationNodeService, null);
		bundleContext.registerService(SwingProgramNodeService.class, new PopupProgramNodeService(), null);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Activator says Goodbye Popup Sample!");
	}
}

