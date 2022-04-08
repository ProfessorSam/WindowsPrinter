package com.github.professorSam.windowsPrinter;

public class Main {
	
	private static boolean debug = false;
	
	public static void main(String... args) {
		for(String string : args) {
			if(string.equalsIgnoreCase("Debug") || string.equalsIgnoreCase("-debug")) {
				debug = true;
			}
		}
		new DataProvider();
		new CmdProvider();
		
		//TODO Use Jline3 oder so für tabcomplete und farben
	}

	public static boolean isDebug() {
		return debug;
	}
}
