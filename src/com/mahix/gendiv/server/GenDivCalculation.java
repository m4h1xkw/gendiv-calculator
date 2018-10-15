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

package com.mahix.gendiv.server;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.mahix.gendiv.shared.GenDivCalcLight;



/**
 * 
 * @author m4h1xkw
 *
 */
public class GenDivCalculation {
	
	
	public static final int STATE_NEW = 0;
	public static final int STATE_IN_PROGRESS = 1;
	public static final int STATE_FINISHED = 2;
	
	public static final String STATE_STRING_NEW = "NEW";
	public static final String STATE_STRING_IN_PROGRESS = "IN PROGESS";
	public static final String STATE_STRING_FINISHED = "FINISHED";

	
	private static long lastId = 0;
	
	private String id;
	
	private String title;

	private int state;
	private Date startDate = null;
	private Date endDate;
	private String outputMsg="";
	private double result=0;

	private int n=0;
	private int j=0;
	private int numFrags=0;
	
	private ArrayList<ArrayList<Integer>> sampleData = new ArrayList<ArrayList<Integer>>();
	
	private IndiKalkThread kalkThread = new IndiKalkThread();
	
	
	
	/**
	 * 
	 */
	public GenDivCalculation() {
		this.id = ""+lastId++;
		this.state = GenDivCalcLight.STATE_NEW;
		this.setTitle("New calculation");
		this.startDate = new Date();
	}
	
	
	
	/**
	 * 
	 * @param title
	 * @param sample
	 * @param n
	 * @param j
	 * @param numFrags
	 */
	public GenDivCalculation(String title, String sample, String n, String j, String numFrags) {
		this.id = ""+lastId++;
		this.state = GenDivCalcLight.STATE_NEW;
		this.startDate = new Date();
		this.setTitle(title);
		this.n = Integer.parseInt(n);
		this.j = Integer.parseInt(j);
		this.numFrags = Integer.parseInt(numFrags);
		
		this.outputMsg += "New calculation added (n=" + n + "; j="+j+")";
		this.result = 0;
		
		this.sampleData = IKCalcHelper.getCSVData(sample);
		
		// Start new thread to run calculation in background
		this.kalkThread = new IndiKalkThread(this.sampleData, this.n,this.j, this.numFrags);
		kalkThread.start();
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getTotalHeValuesAsStringArray(){
		ArrayList<String> tempStrArray = new ArrayList<String>();
		if(kalkThread!=null){
			ArrayList<Double> totalHeValues = kalkThread.getTotalHeValues();
			if(totalHeValues!=null && totalHeValues.size()>0){
				for(int i=0; i<totalHeValues.size(); i++){
					tempStrArray.add(""+totalHeValues.get(i));
				}
			}
			
		}
		
		return tempStrArray;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public GenDivCalcLight getGenDivCalcLight(){
		return new GenDivCalcLight(getTitle(), getStateAsString(), getPercentage(), getStartDateAsString(), ""+this.n, ""+this.j, getOverallHe(), getNumberOfFragments(), getNumberOfIndividuals(), getEndDateAsString(), getNumFragsToPick());
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getEndDateAsString(){
		return kalkThread.getEndDate();
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getPercentage(){
		return ""+(Math.round(kalkThread.getPercentage()*100)/100.0) + "%";
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getNumberOfFragments(){
		if(this.sampleData!=null){
			return ""+this.sampleData.size();
		}else{
			return "-";
		}
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getNumberOfIndividuals(){
		if(this.sampleData!=null && this.sampleData.get(0)!=null){
			return ""+this.sampleData.get(0).size();
		}else{
			return "-";
		}
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getNumFragsToPick(){
		return ""+numFrags;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public int getState() {
		state = kalkThread.getCurrentState();
		return state;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getStateAsString(){
		state = kalkThread.getCurrentState();
		
		if(state==STATE_NEW){
			return STATE_STRING_NEW;
		}else if(state==STATE_IN_PROGRESS){
			return STATE_STRING_IN_PROGRESS;
		}else if(state==STATE_FINISHED){
			return STATE_STRING_FINISHED;
		}
		return "";
	}


	
	/**
	 * 
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}

	
	
	/**
	 * 
	 * @return
	 */
	public String getOverallHe(){
		return kalkThread.getOverallHe();
	}


	
	/**
	 * 
	 * @return
	 */
	public String getOutputMsg() {
		return outputMsg;
	}



	/**
	 * 
	 * @param outputMsg
	 */
	public void setOutputMsg(String outputMsg) {
		this.outputMsg = outputMsg;
	}



	/**
	 * 
	 * @return
	 */
	public double getResult() {
		return result;
	}



	/**
	 * 
	 * @param result
	 */
	public void setResult(double result) {
		this.result = result;
	}

	
	
	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	
	
	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	
	
	/**
	 * 
	 * @return
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getStartDateAsString() {
		if(startDate!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			return sdf.format(startDate.getTime());
		}else{
			return "---";
		}
	}

	
	
	/**
	 * 
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
	/**
	 * 
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	
	/**
	 * 
	 * @return
	 */
	public int getN() {
		return n;
	}

	
	
	/**
	 * 
	 * @param n
	 */
	public void setN(int n) {
		this.n = n;
	}


	
	/**
	 * 
	 * @return
	 */
	public int getJ() {
		return j;
	}

	
	
	/**
	 * 
	 * @param j
	 */
	public void setJ(int j) {
		this.j = j;
	}


	
	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	
	
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
