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


import java.util.ArrayList;
import java.util.Iterator;
import com.mahix.gendiv.client.GreetingService;
import com.mahix.gendiv.shared.GenDivCalcLight;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;



/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	
	ArrayList<GenDivCalculation> calcs = new ArrayList<GenDivCalculation>();
	
	
	
	/**
	 * 
	 */
	public String createNewCalculation(String title, String sample, String repN, String repJ, String numFrags) throws IllegalArgumentException{
		
		GenDivCalculation tempCalc = new GenDivCalculation(title, sample, repN, repJ, numFrags);
		calcs.add(tempCalc);
		return "";
		
	}
	
	
	
	/**
	 * 
	 */
	public ArrayList<GenDivCalcLight> getCalculationsList() throws IllegalArgumentException{
		
		ArrayList<GenDivCalcLight> calcsTemp = new ArrayList<GenDivCalcLight>();
		if(calcs!=null && calcs.iterator()!=null){
			Iterator<GenDivCalculation> i = calcs.iterator();
			while(i.hasNext()){
				calcsTemp.add(i.next().getGenDivCalcLight());
			}
		}
		return calcsTemp;
		
	}
	
	
	
	/**
	 * 
	 */
	public ArrayList<String> getCalculationResult(int id) throws IllegalArgumentException {
		
		ArrayList<String> tempStrArray = new ArrayList<String>();
		if(id>=0 && calcs!=null && calcs.size()>id){
			GenDivCalculation tempCalc = (GenDivCalculation)calcs.get(id);
			tempStrArray = tempCalc.getTotalHeValuesAsStringArray();
		}

		return tempStrArray;
		
	}
	
	
	
	
}
