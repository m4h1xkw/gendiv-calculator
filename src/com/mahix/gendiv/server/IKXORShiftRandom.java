/*
 * MIT License
 * 
 * Copyright (c) unknown
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


import java.util.Random;


/**
 * Random number generator based on research by George Marsaglia (2003)
 * To make it thread-safe, a new seed is used for each thread
 * 
 * @author unknown
 *
 */
public class IKXORShiftRandom extends Random {
	
	private static final long serialVersionUID = -2743000411591027733L;
	
	private long seed = System.nanoTime();

	public IKXORShiftRandom() {
	}
	  
	
	
	/**
	 * Return next random number
	 */
	protected int next(int nbits) {
	    // Not thread-safe => use different seed for each thread
	    long x = this.seed;
	    x ^= (x << 21);
	    x ^= (x >>> 35);
	    x ^= (x << 4);
	    this.seed = x;
	    x &= ((1L << nbits) -1);
	    return (int) x;
	}
}
