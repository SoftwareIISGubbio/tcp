package it.edu.iisgubbio.tcp;

import java.net.InetAddress;

public class Stato {
	public static boolean talos = false;
	
	static {
    	String nome;
    	try {
    		nome = InetAddress.getLocalHost().getHostName();
    	}catch(Exception x) {
    		nome = "x";
    	}
    	System.out.println(">>>>>>>>>>>>>>>>>> "+nome);
    	talos = nome.indexOf("talos")>-1;
	}
}
