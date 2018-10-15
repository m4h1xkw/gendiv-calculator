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
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mahix.gendiv.shared.GenDivCalcLight;



/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	ArrayList<String> getCalculationResult(int id) throws IllegalArgumentException;
	
	
	
	/**
	 * 
	 * @param title
	 * @param sample
	 * @param repN
	 * @param repJ
	 * @param numFrags
	 * @return
	 * @throws IllegalArgumentException
	 */
	String createNewCalculation(String title, String sample, String repN, String repJ, String numFrags) throws IllegalArgumentException;
	
	
	
	/**
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	ArrayList<GenDivCalcLight> getCalculationsList() throws IllegalArgumentException;
	
}
