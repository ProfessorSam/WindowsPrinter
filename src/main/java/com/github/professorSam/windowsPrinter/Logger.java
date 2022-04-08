package com.github.professorSam.windowsPrinter;

import java.text.DateFormat;
import java.util.Calendar;

public class Logger {
	
	public static void error(String error) {
		System.out.println("[Error "+ DateFormat.getInstance().format(Calendar.getInstance().getTime()) + "] " + error);
	}
	
	public static void info(String info) {
		System.out.println("[Info "+ DateFormat.getInstance().format(Calendar.getInstance().getTime()) +"] " + info);
	}
	
	public static void msg(String msg) {
		System.out.println("[" + DateFormat.getInstance().format(Calendar.getInstance().getTime()) + "] " + msg);
	}
	
	public static void info(Printer printer) {
		System.out.println("[Info "+ DateFormat.getInstance().format(Calendar.getInstance().getTime()) +"] " + "Name: " + printer.getDeviceName() + " Standort: " + printer.getPosition() + " Modell: " + printer.getModel() + " Ip: " + printer.getIp());
	}
}
