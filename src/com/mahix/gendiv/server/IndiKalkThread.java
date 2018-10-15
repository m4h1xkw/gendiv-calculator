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


/**
 * Thread to perform the calculation
 * 
 * ToDo: Change to logger instead of system-out
 * 
 * @author m4h1xkw
 *
 */
public class IndiKalkThread extends Thread{

	public static int STATE_NEW = 0;
	public static int STATE_CALCULATING = 1;
	public static int STATE_FINISHED = 2;
	
	private long n;
	private long j;
	private long loopCount=0;
	private int numberOfFragmentsToPick;
	private ArrayList<ArrayList<Integer>> data;
	private ArrayList<Integer> randFrags;
	private ArrayList<Integer> randInds;
	
	private ArrayList<Double> totalHeValues = new ArrayList<Double>();
	
	private int numberOfMaxFragments;
	private int numberOfMaxIndividuals;
	
	private int state = STATE_NEW;
	private String overallHe = "---";
	
	private Date endDate = null;

	
	
	/**
	 * 
	 */
	public IndiKalkThread(){
		this.n = 50;
		this.j = 50;
		this.numberOfFragmentsToPick = 8;		
	}
	
	
	
	/**
	 * 
	 * @param data
	 * @param n
	 * @param j
	 * @param numberOfFragmentsToPick
	 */
	public IndiKalkThread(ArrayList<ArrayList<Integer>> data, long n, long j, int numberOfFragmentsToPick){
		this.data = data;
		this.n = n;
		this.j = j;
		this.numberOfFragmentsToPick = numberOfFragmentsToPick;
	}

	
	
	/**
	 * 
	 */
	private void init(){
		numberOfMaxFragments = data.size();
		System.out.println("numberOfMaxFragments = " + numberOfMaxFragments);
		numberOfMaxIndividuals = data.get(0).size();
		System.out.println("numberOfMaxIndividuals = " + numberOfMaxIndividuals);		
	}
	
	
	
	/**
	 * 
	 */
	public void run(){
		
		init();
		double he = 0;
		double totalHe = 0;
		double overallHe = 0;
		
		//Print out status
		System.out.println("--- --- ---");
		System.out.println("numberOfMaxFragments = " + numberOfMaxFragments);
		System.out.println("numberOfMaxIndividuals = " + numberOfMaxIndividuals);
		System.out.println("n = " + n);
		System.out.println("j = " + j);
		System.out.println("--- --- ---");
		
		
		this.state = STATE_CALCULATING;
		long startTime = System.currentTimeMillis();
		
		
		// Choose suitable random number generator
		//IKHighQualityRandom randomGenerator = new IKHighQualityRandom();
		//Random randomGenerator = new Random();
		IKXORShiftRandom randomGenerator = new IKXORShiftRandom();
		
		overallHe = 0;
		for(int numberOfIndividualsToPick=2; numberOfIndividualsToPick<=numberOfMaxIndividuals; numberOfIndividualsToPick++){
			totalHe = 0;
			for(long i=0; i<j; i++){
				he=0;
				
				randFrags = IKCalcHelper.getRandomIntegerList(numberOfFragmentsToPick, 0, numberOfMaxFragments-1);
				for(long k=0; k<n; k++){
					 
					randInds = IKCalcHelper.getRandomIntegerList(randomGenerator, numberOfIndividualsToPick, 0, numberOfMaxIndividuals-1);
					
					he+=IKCalcHelper.calcHe(data, randFrags, randInds);
					loopCount++;
				}
				totalHe += he / n;
				
			}
			overallHe += totalHe / j;
			totalHeValues.add(totalHe / j);	//Add to result array
			System.out.println("He_total_" + numberOfIndividualsToPick + " = " + (totalHe / j));
		}
		overallHe = overallHe / numberOfMaxIndividuals;
		
		
		long endTime = System.currentTimeMillis() - startTime;
		this.endDate = new Date();
		this.state = STATE_FINISHED;
		this.overallHe = ""+overallHe;
		
		
		System.out.println("--- --- ---");
		System.out.println("Overall He = " + overallHe);
		System.out.println("--- --- ---");
		System.out.println("loopCount = " + loopCount);
		System.out.println("Milliseconds: " + endTime);
		System.out.println("--- --- ---");
		
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public int getCurrentState(){
		return state;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public float getPercentage(){
		return ((float)loopCount/(n*j*(numberOfMaxIndividuals-1)))*100.0F;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getOverallHe(){
		return this.overallHe;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getEndDate(){
		if(endDate!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			return sdf.format(endDate.getTime());
		}else{
			return "---";
		}
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Double> getTotalHeValues(){
		return totalHeValues;
	}

	
}
