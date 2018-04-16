package com.sugon.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sugon.Shell;

public class NewTest {

	@Test(priority = 0)
	@Parameters({ "ip", "username", "password", "install", "installtar", "installtar-1", "installtar-2",
			"installtar-3" })
	public void install(String ip, String username, String password, String install, String it, String it1, String it2,
			String it3) {
		Shell shell = new Shell(ip, username, password);
		shell.execute("sh " + install + " " + it + " " + it1 + " " + it2 + " " + it3 + " " + password + " " + ip);
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
	@Parameters({ "ip", "username", "password", "insertdataset" })
	public void insertData(String ip, String username, String password, String insertdataset) {
		Shell shell = new Shell(ip, username, password);
		shell.execute("sh " + insertdataset+" "+ip);
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
