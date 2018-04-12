package com.sugon;

import java.util.ArrayList;

public class insertData {
	public static void main(String[] args) {
		Shell shell = new Shell("172.16.0.93", "root", "123456");
	    shell.execute("sh /root/insertdataset.sh");
	  
	    ArrayList<String> stdout = shell.getStandardOutput();
	    for (String str : stdout) { 
	      System.out.println(str); 
	    }
	}
}
