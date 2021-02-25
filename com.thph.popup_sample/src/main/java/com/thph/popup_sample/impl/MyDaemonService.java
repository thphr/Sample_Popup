package com.thph.popup_sample.impl;

import java.net.MalformedURLException;
import java.net.URL;

import com.ur.urcap.api.contribution.DaemonContribution;
import com.ur.urcap.api.contribution.DaemonService;

public class MyDaemonService implements DaemonService {

	private DaemonContribution daemonContribution;
	
	@Override
	public void init(DaemonContribution daemonContribution) {
		this.daemonContribution = daemonContribution;
		
		try {
			daemonContribution.installResource(new URL("file:t_daemon/"));
		} catch (MalformedURLException e) {
			System.out.println("Not able to install daemon resource");
			e.printStackTrace();
		}
	} 

	@Override
	public URL getExecutable() {
		try {
			return new URL("file:t_daemon/requestpose_xmlrpc.py");
		} catch (MalformedURLException e) {
			System.out.println("Not able to install daemon resource file");
			return null;
		}
	}

	public DaemonContribution getDaemon() {
		return this.daemonContribution;
	}

}