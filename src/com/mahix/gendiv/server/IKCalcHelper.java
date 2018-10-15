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


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;


/**
 * 
 * @author m4h1xkw
 *
 */
public class IKCalcHelper {

	

	/**
	 * 
	 * @param n
	 * @param min
	 * @param max
	 * @return
	 */
	public static ArrayList<Integer> getRandomIntegerList(int n, int min, int max){
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		
		int randInt;
		while(tempList.size()<n){
			randInt = (min + (int)(Math.random() * ((max - min) + 1)));
			if(!tempList.contains(randInt))
				tempList.add(randInt);
		}
		
		return tempList;
	}
	
	
	
	/**
	 * 
	 * @param randomGenerator
	 * @param n
	 * @param min
	 * @param max
	 * @return
	 */
	public static ArrayList<Integer> getRandomIntegerList(Random randomGenerator, int n, int min, int max){
		Vector<Integer> tempList = new Vector<Integer>();
		
		for(int i=min; i<=max; i++){
			tempList.add(i);
		}
		
		Collections.shuffle(tempList, randomGenerator);
		tempList.setSize(n);
		return new ArrayList<Integer>(tempList);
	}
	
	
	
	/**
	 * 
	 * @param data
	 * @param pickedFragIds
	 * @param pickedIndividualIds
	 * @return
	 */
	public static double calcHe(ArrayList<ArrayList<Integer>> data, ArrayList<Integer> pickedFragIds, ArrayList<Integer> pickedIndividualIds){
		double he = 0;
		
		for(int i=0; i<pickedFragIds.size(); i++){
			ArrayList<Integer> frag = data.get(pickedFragIds.get(i));
			double band_freq = 0; 
			for(int k=0; k<pickedIndividualIds.size(); k++){
				band_freq += frag.get(pickedIndividualIds.get(k));
			}
			band_freq = band_freq / pickedIndividualIds.size();
			double q = Math.pow((1-band_freq),0.5);
			double p = 1-q;
			he+=2*p*q;
			
		}
		
		he = he / pickedFragIds.size();
		
		return he;
	}
	
	
	
	/**
	 * Converts a sample string to a 2-dimensional array
	 * 
	 * @param sample
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> getCSVData(String sample){
		
		ArrayList<ArrayList<Integer>> tempIntArr = new ArrayList<ArrayList<Integer>>();
		
		
		String line = "";
		BufferedReader csvReader = null;
		
		try{
		csvReader = new BufferedReader(new StringReader(sample));
			while((line = csvReader.readLine()) != null) {
				
				String[] strList = (line.split(";"));
				ArrayList<Integer> tempIntList = new ArrayList<Integer>();
				
				for(int i=0; i<strList.length; i++){
					tempIntList.add(Integer.parseInt(strList[i]));
				}
				tempIntArr.add(tempIntList);
				
				
			}
		}catch(FileNotFoundException fnfe){
			System.out.println("file not found");
		}catch(IOException ioe){
			
		}finally{
			try{
				csvReader.close();
			}catch(Exception e){}
		}
		
		return tempIntArr;
	}
	
	
}
