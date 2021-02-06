package com.zetcode;

import java.io.IOException;
import java.util.HashMap;

public class Data {
	
	HashMap<String, String> dataSet = new HashMap<String, String>();
	
	public Data() { }
	
	public void setMap(String a, String b) {
			
		dataSet.put(a, b);
	}
}
