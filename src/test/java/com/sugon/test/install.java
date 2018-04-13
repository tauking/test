package com.sugon.test;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.sugon.Shell;
import com.sugon.Util;

public class install {
  @Test
  public void f() {
	  	Shell shell = new Shell(Util.ip, Util.username, Util.password);
	    shell.execute("sh "+Util.install+" "+Util.password+" "+Util.ip);
	    //shell.execute("sh /root/untar.sh 123456 172.16.0.93");
	    ArrayList<String> stdout = shell.getStandardOutput();
	    for (String str : stdout) { 
	      System.out.println(str); 
	    } 
  }
}
