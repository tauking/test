package com.sugon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class jenkins {
	@BeforeTest
	public void install(){
		Shell shell = new Shell("172.16.0.93", "root", "123456");
	    shell.execute("sh /root/untar.sh 123456 172.16.0.93");
	  
	    ArrayList<String> stdout = shell.getStandardOutput();
	    for (String str : stdout) { 
	      System.out.println(str); 
	    } 
	}
	
	@Test
	public void insert() throws Exception{
		String s;
		Process proc = Runtime.getRuntime().exec("python F:\\sugon\\20180330\\AutoTestforAUS\\AUS\\createdataset.py");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gbk"));
		while((s=bufferedReader.readLine()) != null)
			System.out.println(s);
		proc.waitFor();
		
		Shell shell = new Shell("172.16.0.93", "root", "123456");
	    shell.execute("sh /root/insertdataset.sh");
	  
	    ArrayList<String> stdout = shell.getStandardOutput();
	    for (String str : stdout) { 
	      System.out.println(str); 
	    }
	}
	
	@AfterTest
	public void testMQL() throws Exception{
		String s;
		Process proc = Runtime.getRuntime().exec("python F:\\sugon\\20180330\\AutoTestforAUS\\AUS\\testMQL.py");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gbk"));
		while((s=bufferedReader.readLine()) != null)
			System.out.println(s);
		proc.waitFor(); 
	}
}
