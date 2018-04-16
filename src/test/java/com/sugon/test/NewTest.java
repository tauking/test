package com.sugon.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sugon.Shell;

public class NewTest {
	
	@Test(priority = 0)
	@Parameters({"ip","username","password","install"})
	public void install(String ip,String username,String password,String install) {
		Shell shell = new Shell(ip, username, password);
		shell.execute("sh " + install + " " + password + " " + ip);
		// shell.execute("sh /root/untar.sh 123456 172.16.0.93");
		ArrayList<String> stdout = shell.getStandardOutput();
		for (String str : stdout) {
			System.out.println(str);
		}
	}

	@Test(priority = 1)
	public void createDataSet() throws Exception {
		String s;
		Process proc = Runtime.getRuntime().exec("python AUS/createdataset.py");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gbk"));
		while ((s = bufferedReader.readLine()) != null)
			System.out.println(s);
		proc.waitFor();
	}

	@Test(priority = 2)
	@Parameters({"ip","username","password","insertdataset"})
	public void insertData(String ip,String username,String password,String insertdataset) {
		Shell shell = new Shell(ip, username, password);
		shell.execute("sh " + insertdataset);
		// shell.execute("sh /root/insertdataset.sh");
		ArrayList<String> stdout = shell.getStandardOutput();
		for (String str : stdout) {
			System.out.println(str);
		}
	}
	@Test(priority = 3)
	public void testMQL() throws Exception {
		String s;
		Process proc = Runtime.getRuntime().exec("python AUS/testMQL.py");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gbk"));
		while ((s = bufferedReader.readLine()) != null)
			System.out.println(s);
		proc.waitFor();
	}
}
