package com.thph.popup_sample.impl;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.xmlrpc.XmlRpcException;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PopupProgramNodeView implements SwingProgramNodeView<PopupProgramNodeContribution> {
	
	// Style guide library
	private JLabel labelonPopup = new JLabel("Sample Popup");
	
	private JPanel programnnodeViewPanel;
	private int buttonWidth = 80;
	
	private JFrame framePopup = new JFrame("Request Pose");
	private JButton buttonOK = new JButton("OK");

	private int screenHeight = 0;
	private int screenWidth = 0;

	private int frameXLocation = 0;
	private int frameYLocation = 0;

	private int frameX = 0;
	private int frameY = 0;
	

	@Override
	public void buildUI(JPanel panel, ContributionProvider<PopupProgramNodeContribution> provider) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		//sets panel.
		this.setProgramnnodeViewPanel(panel);
		// java - get screen size using the Toolkit class
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// the screen height
		this.screenHeight = (int) screenSize.getHeight();
		// the screen width
		this.screenWidth = (int) screenSize.getWidth();
		
		
		this.frameX = this.screenWidth / 4;
		this.frameY = this.screenHeight / 3;

		this.frameXLocation = screenWidth / 2 - this.frameX / 4 + 200;
		this.frameYLocation = screenHeight / 2 - this.frameY / 3;

		//calls the handler for OK button.
		this.handleButtonEvents(provider);
		
	}
	
	
	/**
	 * Method for activating the popup. Calls by the RequestProgramNodeConribution
	 * class in openview method.
	 * 
	 * @param panel
	 */
	public void openPopopView(JPanel panel) {
		
		buttonOK.getModel().setPressed(false);
		;

		this.createPopup();

		getFramePopup().setVisible(true);

	}

	/**
	 * create a popup on the programnode JPanel x,y(0,0).
	 * 
	 * @param panel
	 * @return
	 */
	private void createPopup() {
		
		JPanel jpanel = new JPanel();
		jpanel.add(createPopupLayout());

		getFramePopup().setSize(this.frameX, this.frameY);
		getFramePopup().setLocation(this.frameXLocation, this.frameYLocation);

		this.getFramePopup().add(jpanel, SwingConstants.CENTER);

	}
	
	
	/**
	 * Creates a box with five TCP orientation buttons.
	 * 
	 * @return Box containing the buttons.
	 */
	private Box createPopupLayout() {
		Box box = Box.createVerticalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		buttonOK.setSize(new Dimension(100,50));

		Box boxTOP = Box.createHorizontalBox();

		Box boxSTOP = Box.createHorizontalBox();

		boxTOP.add(labelonPopup);
		boxSTOP.add(buttonOK);
		
		box.add(boxTOP);
		box.add(createVerticalSpacing(50));
		box.add(boxSTOP);

		return box;
	}
	
	/**
	 * Create handlers for buttons.
	 */
	private void handleButtonEvents(final ContributionProvider<PopupProgramNodeContribution> provider) {


		// Button listener for OK buttons.
		this.buttonOK.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				ButtonModel model = (ButtonModel) e.getSource();

				if (model.isPressed()) {
					try {
						provider.get().getInstallation().getXmlRpcDaemonInterface().cancelPopup();
					} catch (XmlRpcException e1) {
						e1.printStackTrace();
					} catch (UnknownResponseException e1) {
						e1.printStackTrace();
					}

					provider.get().setPopupStillEnabled(false);
					getFramePopup().setVisible(false);
				}

			}
		});

	}
	
	public JPanel getProgramnnodeViewPanel() {
		return programnnodeViewPanel;
	}
	
	private void setProgramnnodeViewPanel(JPanel programnnodeViewPanel) {
		this.programnnodeViewPanel = programnnodeViewPanel;
	}


	public JFrame getFramePopup() {
		return framePopup;
	}

	/**
	 * Create a vertical spacing.
	 * 
	 * @param spacesize
	 * @return
	 */
	private Component createVerticalSpacing(int spacesize) {
		return Box.createRigidArea(new Dimension(0, spacesize));
	}
}