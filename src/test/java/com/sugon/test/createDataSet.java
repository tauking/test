package com.sugon.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.testng.annotations.Test;

public class createDataSet {
	@Test
	public void f() throws Exception{
		String s;
		Process proc = Runtime.getRuntime().exec("python AUS/createdataset.py");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gbk"));
		while((s=bufferedReader.readLine()) != null)
			System.out.println(s);
		proc.waitFor(); 
	}
}
