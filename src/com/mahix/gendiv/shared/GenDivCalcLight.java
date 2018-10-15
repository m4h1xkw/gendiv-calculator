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

package com.mahix.gendiv.shared;


import java.io.Serializable;
import java.util.Date;


/**
 * Stores calculation data to exchange between server and client
 * 
 * @author m4h1xkw
 *
 */
public class GenDivCalcLight implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1876606264315719370L;
	
	
	public static final int STATE_NEW = 0;
	public static final int STATE_IN_PROGRESS = 1;
	public static final int STATE_FINISHED = 2;
	
	public static final String STATE_STRING_NEW = "NEW";
	public static final String STATE_STRING_IN_PROGRESS = "IN PROGESS";
	public static final String STATE_STRING_FINISHED = "FINISHED";

	
	private static long lastId = 0;
	
	private String id;

	private String title;
	private String state;
	private String startDate = "---";
	private String endDate = "---";
	private String outputMsg="";
	private String overallHe="---";
	private String percentage="0%";
	private String numberOfFragments = "-";
	private String numberOfIndividuals = "-";
	private String numberOfFragsToPick = "-";

	private int n=0;
	private int j=0;
	
	
	
	/**
	 * 
	 */
	public GenDivCalcLight() {
		this.id = ""+lastId++;
		this.state = GenDivCalcLight.STATE_STRING_NEW;
		this.startDate = new Date().toString();
		this.title = "New calculation";
	}
	
	
	
	/**
	 * 
	 * @param title
	 * @param state
	 * @param percentage
	 * @param startDate
	 * @param n
	 * @param j
	 * @param overallHe
	 * @param numFragments
	 * @param numIndividuals
	 * @param endDate
	 * @param numFragsToPick
	 */
	public GenDivCalcLight(String title, String state, String percentage, String startDate, String n, String j, String overallHe, String numFragments, String numIndividuals, String endDate, String numFragsToPick) {
		this.id = ""+lastId++;
		
		this.title = title;
		this.startDate = startDate;
		this.n = Integer.parseInt(n);
		this.j = Integer.parseInt(j);
		this.percentage = percentage;
		this.state = state;
		this.overallHe = overallHe;
		
		this.numberOfFragments = numFragments;
		this.numberOfIndividuals = numIndividuals;

		this.numberOfFragsToPick = numFragsToPick;

		this.endDate = endDate;
		
		this.outputMsg += "New calculation added (n=" + n + "; j="+j+")";
		//this.result = 0;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getStateAsString(){
		return state;
	}

	
	
	/**
	 * 
	 * @return
	 */
	public String getOverallHe(){
		return overallHe;
	}

	
	
	/**
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	
	
	/**
	 * 
	 * @return
	 */
	public String getCurrentPercentage(){
		return percentage;
	}

	
	
	/**
	 * 
	 * @return
	 */
	public String getEndDate(){
		return this.endDate;
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
	public String getStartDate() {
		return startDate;
	}

	
	
	/**
	 * 
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
	public String getNumberOfFragments() {
		return numberOfFragments;
	}


	
	/**
	 * 
	 * @param numberOfFragments
	 */
	public void setNumberOfFragments(String numberOfFragments) {
		this.numberOfFragments = numberOfFragments;
	}

	
	
	/**
	 * 
	 * @return
	 */
	public String getNumberOfIndividuals() {
		return numberOfIndividuals;
	}

	
	
	/**
	 * 
	 * @param numberOfIndividuals
	 */
	public void setNumberOfIndividuals(String numberOfIndividuals) {
		this.numberOfIndividuals = numberOfIndividuals;
	}

	
	
	/**
	 * 
	 * @return
	 */
	public String getNumberOfFragsToPick() {
		return numberOfFragsToPick;
	}

	
	
	/**
	 * 
	 * @param numberOfFragsToPick
	 */
	public void setNumberOfFragsToPick(String numberOfFragsToPick) {
		this.numberOfFragsToPick = numberOfFragsToPick;
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
