package com.zetcode;

import java.io.IOException;

import java.util.HashMap;

public class Data {
	
	HashMap<String, Integer> dataSet = new HashMap<String, Integer>();
	
	public Data() { }
	
	public void setMap(String a, int b) {
			
		dataSet.put(a, b);
	}
	

}
