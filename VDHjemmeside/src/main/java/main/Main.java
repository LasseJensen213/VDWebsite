package main;

import java.util.logging.Level;
import java.util.logging.Logger;


import controller.Controller;

public class Main {
	private static Controller ctrl = null;
	
	
	
	public static void main(String[] args) {
		ctrl = Controller.getInstance();
		Logger.getLogger(Main.class.getName()).log(Level.FINE, "work");
		System.out.println("It works!");
		
		
		
	
	}
}
