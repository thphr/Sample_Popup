package com.thph.popup_sample.impl;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class XmlRPCdaemonInterface {

	private final XmlRpcClient client;

	public XmlRPCdaemonInterface(String host, int port) {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setEnabledForExtensions(true);
		try {
			config.setServerURL(new URL("http://" + host + ":" + port + "/RPC2"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		config.setConnectionTimeout(1000); // 1s
		this.client = new XmlRpcClient();
		this.client.setConfig(config);
	}

	public boolean isReachable() {
		try {
			this.client.execute("isEnabled", new ArrayList<String>());
			return true;
		} catch (XmlRpcException e) {
			return false;
		}
	}
	
	
	public boolean showPopup() throws XmlRpcException, UnknownResponseException {
		Object result  = client.execute("showpopup", new ArrayList<String>());
		return processBoolean(result);
	}
	
	public boolean cancelPopup() throws XmlRpcException, UnknownResponseException {
		Object result = client.execute("cancelpopup", new ArrayList<String>());
		return processBoolean(result);
	}
	
	public boolean isEnabled() throws XmlRpcException, UnknownResponseException {
		Object result = client.execute("isEnabled", new ArrayList<String>());
		return processBoolean(result);
	}
	
	private boolean processBoolean(Object response) throws UnknownResponseException {
		if (response instanceof Boolean) {
			Boolean val = (Boolean) response;
			return val.booleanValue();
		} else {
			throw new UnknownResponseException();
		}
	}

	private String processString(Object response) throws UnknownResponseException {
		if (response instanceof String) {
			return (String) response;
		} else {
			throw new UnknownResponseException();
		}
	}

}
