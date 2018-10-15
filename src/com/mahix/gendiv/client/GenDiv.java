/*
 * MIT License
 * 
 * Copyright (c) 2013 m4h1xkw
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.mahix.gendiv.client;


import java.util.ArrayList;
import com.mahix.gendiv.shared.GenDivCalcLight;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GenDiv implements EntryPoint {
	
	private static final int REFRESH_INTERVAL = 3000; // ms
	private Timer refreshTimer;
	
	final TextBox tbTitle = new TextBox();
	final TextBox tbRepN = new TextBox();
	final TextBox tbRepJ = new TextBox();
	final TextBox tbNumFrags = new TextBox();
	
	DialogBox dbCalcResult = new DialogBox();
	
	private TextArea taSample = new TextArea();

	
	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	
	private final FlexTable ftResults = new FlexTable();
	
	private final FlexTable ftCalcResult = new FlexTable();

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		VerticalPanel mainPanel = new VerticalPanel();
		
		final Button buttonNewCalc = new Button("New calculation");

		buttonNewCalc.addStyleName("sendButton");

		ftResults.setWidth("100%");
		mainPanel.add(buttonNewCalc);
		mainPanel.add(ftResults);
		
	    
		// Create the 'new calculation' popup dialog box
		final DialogBox dbNewCalc = new DialogBox();
		dbNewCalc.setText("Create new calculation");
		dbNewCalc.setAnimationEnabled(true);
		
		final Button buttonStart = new Button("Start");
		final Button buttonCancel = new Button("Cancel");
		
		// We can set the id of a widget by accessing its Element
		buttonStart.getElement().setId("startButton");
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.addStyleName("dialogVPanel");
		vPanel.add(new HTML("<b>Insert sample data (csv):</b>"));
		taSample.setSize("400px", "200px");
		
		//Text Box 'Title'
		vPanel.add(new HTML("<br><b>Title</b>"));
		tbTitle.setText(GDClientConstants.DEFAULT_VALUE_TITLE);
		vPanel.add(tbTitle);
		taSample.setText(GDClientConstants.DEFAULT_VALUE_DATA);
		vPanel.add(taSample);
		vPanel.add(new HTML("<br><b>Number of fragments to pick:</b>"));
		tbNumFrags.setText(GDClientConstants.DEFAULT_VALUE_NUMBER_OF_FRAGMENTS_TO_PICK);
		vPanel.add(tbNumFrags);
		vPanel.add(new HTML("<br><b>Number of repetitions n:</b>"));
		tbRepN.setText(GDClientConstants.DEFAULT_VALUE_N);
		vPanel.add(tbRepN);
		vPanel.add(new HTML("<br><b>Number of repetitions j:</b>"));
		tbRepJ.setText(GDClientConstants.DEFAULT_VALUE_J);
		vPanel.add(tbRepJ);
		vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		vPanel.add(buttonCancel);
		vPanel.add(buttonStart);
		dbNewCalc.setWidget(vPanel);
		
		// DialogBox 'New Calculation'
		// Add a handler to close the DialogBox
		buttonCancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dbNewCalc.hide();
			}
		});
		// Add a handler to close the DialogBox
		buttonStart.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				greetingService.createNewCalculation(tbTitle.getText(), taSample.getText(), tbRepN.getText(), tbRepJ.getText(), tbNumFrags.getText(),
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								
							}

							public void onSuccess(String result) {
								
							}
						});
				dbNewCalc.hide();
			}
		});
		// Add a handler to close the DialogBox
		buttonNewCalc.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dbNewCalc.show();
			}
		});
		
		
		// Create the 'show result' popup dialog box
		dbCalcResult = new DialogBox();
		dbCalcResult.setText("Calculation result");
		dbCalcResult.setAnimationEnabled(true);
		final Button buttonClose = new Button("Close");
		// We can set the id of a widget by accessing its Element
		buttonClose.getElement().setId("okButton");
		VerticalPanel vpCalcResult = new VerticalPanel();
		vpCalcResult.addStyleName("dialogVPanel");
		vpCalcResult.add(ftCalcResult);
		vpCalcResult.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		vpCalcResult.add(buttonClose);
		dbCalcResult.setWidget(vpCalcResult);
		
		// Add a handler to close the DialogBox
		buttonClose.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dbCalcResult.hide();
			}
		});
				
		
		
		// Create the 'cancel calculation' popup dialog box
		final DialogBox dbCancelCalc = new DialogBox();
		dbCancelCalc.setText("Cancel calculation...");
		dbCancelCalc.setAnimationEnabled(true);
		final Button buttonYes = new Button("Yes");
		final Button buttonNo = new Button("No");
		// We can set the id of a widget by accessing its Element
		buttonYes.getElement().setId("yesButton");
		buttonNo.getElement().setId("noButton");
		VerticalPanel vpCancelCalc = new VerticalPanel();
		vpCancelCalc.addStyleName("dialogVPanel");
		vpCancelCalc.add(new HTML("<b>Cancel calculation - are you sure?</b>"));
		vpCancelCalc.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		vpCancelCalc.add(buttonYes);
		vpCancelCalc.add(buttonNo);
		dbCancelCalc.setWidget(vpCancelCalc);
		
		
		
		// Setup timer to refresh list automatically.
	    refreshTimer = new Timer() {
	      @Override
	      public void run() {
	        refreshResultList();
	      }
	    };
	    refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
	    
	    
		RootPanel.get("gdata").add(mainPanel);
		RootPanel.get("waitText").setVisible(false);

	}
	
	
	
	/**
	 * 
	 */
	private void refreshResultList() {
	    
		greetingService.getCalculationsList(new AsyncCallback<ArrayList<GenDivCalcLight>>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
			}

			@Override
			public void onSuccess(ArrayList<GenDivCalcLight> result) {
				if(result!=null){
					resetResultTable(ftResults);
					for(int i=0; i<result.size(); i++){
						GenDivCalcLight tempCalc = result.get(i);
						if(tempCalc!=null){
							addResultRow(ftResults, tempCalc.getTitle(), tempCalc.getStateAsString(), tempCalc.getStartDate().toString(), tempCalc.getNumberOfFragments(), tempCalc.getNumberOfIndividuals(), tempCalc.getNumberOfFragsToPick(),""+tempCalc.getN(), ""+tempCalc.getJ(), tempCalc.getCurrentPercentage(), tempCalc.getEndDate(),tempCalc.getOverallHe());
						}
					}
				}
			}
		});

	}
	
	
	
	
	/**
	 * Add a row to the flex table.
	 */
	private void addResultRow(FlexTable flexTable, String title, String state, String startDate, String numFragments, String numIndividuals, String numFragsToPick, String n, String j, String percentage, String endDate, String overallHe) {
		final int numRows = flexTable.getRowCount();	
		flexTable.setText(numRows, 0, ""+title);
		flexTable.setText(numRows, 1, ""+state);
		flexTable.setText(numRows, 2, ""+startDate);
		flexTable.setText(numRows, 3, ""+ numFragments);
		flexTable.setText(numRows, 4, ""+ numIndividuals);
		flexTable.setText(numRows, 5, ""+ numFragsToPick);
		flexTable.setText(numRows, 6, ""+ n);
		flexTable.setText(numRows, 7, ""+ j);
		flexTable.setText(numRows, 8, ""+percentage);
		flexTable.setText(numRows, 9, ""+endDate);
		flexTable.setText(numRows, 10, ""+ overallHe);

		Image imgEquals = new Image();
		if(state.equals(GenDivCalcLight.STATE_STRING_FINISHED)){
			imgEquals = new Image(ServerInfo.ImagePath + "icon_info.png");
			imgEquals.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					showCalculationResult(numRows-1);	// minus 1, because row=0 is the headline
				}
			});
	
		}else{
	
			imgEquals = new Image(ServerInfo.ImagePath + "icon_cross.png");
			imgEquals.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					cancelCalculation(numRows-1);	// minus 1, because row=0 is the headline
				}
			});
	
		}
	    
	    flexTable.setWidget(numRows, 11, imgEquals);
	    
	}
	  

	
	/**
	 * 
	 * @param flexTable
	 */
	private void resetResultTable(FlexTable flexTable){
		flexTable.removeAllRows();
	    flexTable.setText(0, 0, "Title");
	    flexTable.setText(0, 1, "State");
	    flexTable.setText(0, 2, "Start date");
	    flexTable.setText(0, 3, "Fragments");
	    flexTable.setText(0, 4, "Individuals");
		    
	    flexTable.setText(0, 5, "Frags to pick");
	    flexTable.setText(0, 6, "N");
	    flexTable.setText(0, 7, "J");
	    flexTable.setText(0, 8, "Percentage");
	    flexTable.setText(0, 9, "End date");
	    flexTable.setText(0, 10, "Overall He");
	    flexTable.setText(0, 11, "Action");

	}
	
	
	
	/**
	 * 
	 * @param id
	 */
	private void cancelCalculation(int id){
		// Todo!  
	}
	  
	
	
	/**
	 * 
	 * @param id
	 */
	private void showCalculationResult(int id){
		greetingService.getCalculationResult(id, new AsyncCallback<ArrayList<String>>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				dbCalcResult.setText("Remote Procedure Call - Failure");
				dbCalcResult.center();
			}
			
			public void onSuccess(ArrayList<String> result) {
				dbCalcResult.setText("Calculation result");
				ftCalcResult.removeAllRows();
				if(result!=null && result.size()>0){
					for(int i=0; i<result.size(); i++){
						ftCalcResult.setWidget(i, 0, new Label("Total_He_" + (i+2)));
						ftCalcResult.setWidget(i, 1, new Label(""+result.get(i)));
					}
				}else{
					ftCalcResult.setWidget(0, 0, new Label("No data received, sorry."));
				}
				dbCalcResult.center();
			}
		});
	}
	
}


