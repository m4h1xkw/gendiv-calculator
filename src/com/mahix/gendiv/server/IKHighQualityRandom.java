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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * @author unknown
 *
 */
public class IKHighQualityRandom extends Random {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9011263426451876495L;
	
	private Lock l = new ReentrantLock();
	private long u;
	private long v = 4101842887655102017L;
	private long w = 1;
	
	
	
	/**
	 * 
	 */
	public IKHighQualityRandom() {
		this(System.nanoTime());
	}
	
	
	
	/**
	 * 
	 * @param seed
	 */
	public IKHighQualityRandom(long seed) {
		//l.lock();
		u = seed ^ v;
		nextLong();
		v = u;
		nextLong();
		w = v;
		nextLong();
		//l.unlock();
	}

	
	
	/**
	 * 
	 */
	public long nextLong() {
		//l.lock();
	    try {
	    	u = u * 2862933555777941757L + 7046029254386353087L;
	    	v ^= v >>> 17;
	    	v ^= v << 31;
	    	v ^= v >>> 8;
	    	w = 4294957665L * (w & 0xffffffff) + (w >>> 32);
	    	long x = u ^ (u << 21);
	    	x ^= x >>> 35;
	    	x ^= x << 4;
	    	long ret = (x + v) ^ w;
	    	return ret;
	    } finally {
	    	//l.unlock();
	    }
	}
	  
	
	
	/**
	 * 
	 */
	protected int next(int bits) {
		return (int) (nextLong() >>> (64-bits));
	}

}


