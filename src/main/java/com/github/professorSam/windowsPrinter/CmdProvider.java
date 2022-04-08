package com.github.professorSam.windowsPrinter;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CmdProvider {
	
	private final Scanner scanner = new Scanner(System.in);
	
	public CmdProvider() {
		Printer printer = askForPrinter();
		Logger.info(printer);
		try {
			new PrinterAdder(printer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private Printer askForPrinter(){
		Logger.msg("Wähle einen Drucker: ");
		Logger.msg("Name | Standort | Modell | IP");
		List<Printer> printers = DataProvider.getInstance().getPrinter();
		for(int i = 0; i < printers.size(); i ++) {
			Logger.msg(i + 1 + ") " + printers.get(i).getDeviceName() + " | " + printers.get(i).getPosition() + " | " + printers.get(i).getModel() + " | " + printers.get(i).getIp());
		}
		String input = scanForInput(false, "Wähle einen Drucker aus: ");
		int inputInt = 0;
		try {
			inputInt = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			Logger.error("Bitte gib eine Zahl zwischen 1 und " + printers.size() + " an!");
			return askForPrinter();
		}
		inputInt --;
		if(inputInt >= printers.size() || inputInt < 0) {
			Logger.error("Bitte gib eine Zahl zwischen 1 und " + printers.size() + " an!");
			return askForPrinter();
		}
		return printers.get(inputInt);
		
	}
	
	private String scanForInput(boolean canBeEmpty, String question) {
		if(!question.isEmpty() || !question.isBlank()) {
			Logger.msg(question);
		}
		String input = scanner.nextLine();
		if((input.isBlank() || input.isEmpty()) && !canBeEmpty){
			return scanForInput(canBeEmpty, question);
		}
		return input;
	}
}
