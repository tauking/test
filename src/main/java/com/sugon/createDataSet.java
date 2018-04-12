package com.sugon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class createDataSet {
	public static void main(String[] args) throws IOException, InterruptedException {
		String s;
		Process proc = Runtime.getRuntime().exec("python F:\\sugon\\20180330\\AutoTestforAUS\\AUS\\createdataset.py");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gbk"));
		while((s=bufferedReader.readLine()) != null)
			System.out.println(s);
		proc.waitFor(); 
	}
}
