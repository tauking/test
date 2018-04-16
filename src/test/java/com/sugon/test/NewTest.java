package com.sugon.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.sugon.Shell;

public class NewTest {
	@Test(priority = 0)
	public void install() {
		Shell shell = new Shell(Util.ip, Util.username, Util.password);
		shell.execute("sh " + Util.install + " " + Util.password + " " + Util.ip);
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
	public void insertData() {
		Shell shell = new Shell(Util.ip, Util.username, Util.password);
		shell.execute("sh " + Util.insertdataset);
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
