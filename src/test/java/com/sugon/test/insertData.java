package com.sugon.test;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.sugon.Shell;
import com.sugon.Util;

public class insertData {
  @Test
  public void f3() {
	    Shell shell = new Shell(Util.ip, Util.username, Util.password);
	    shell.execute("sh "+Util.insertdataset);
	    //shell.execute("sh /root/insertdataset.sh");
	    ArrayList<String> stdout = shell.getStandardOutput();
	    for (String str : stdout) { 
	      System.out.println(str); 
	    }
  }
}
