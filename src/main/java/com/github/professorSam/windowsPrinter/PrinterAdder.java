package com.github.professorSam.windowsPrinter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PrinterAdder {

	Scanner scanner = new Scanner(System.in);

	public PrinterAdder(Printer printer) throws IOException {
		Process process = new ProcessBuilder("powershell.exe",
				"Get-PrinterDriver -name \"" + printer.getModel() + "*\"").start();
		Logger.info("Folgende Treiber werden entfert: ");
		printProcessOutput(process);
		Logger.info("Drücke [Enter] zum fortfahren");
		scanner.nextLine();
		Process process2 = new ProcessBuilder("powershell.exe",
				"Get-Printer | where name -like \"" + printer.getModel() + "*\" | Remove-Printer").start();
		printProcessOutput(process2);
		Process process3 = new ProcessBuilder("powershell.exe",
				"Get-PrinterDriver | where name -like \"" + printer.getModel() + "*\" | Remove-PrinterDriver").start();
		printProcessOutput(process3);
		Logger.info("Installiere Treiber: " + printer.getModel());
		Logger.info("Drücke [Enter] um fortzufahren");
		String path = new File("./driver/" + printer.getDeviceName()).getPath();
		path = path + "\\*.inf";
		Process process4 = new ProcessBuilder("powershell.exe", "pnputil.exe -i -a \"" + path + "\"").start();
		printProcessOutput(process4);
		Logger.info("Erstelle Printerport:");
		Process process5 = new ProcessBuilder("powershell.exe", "Add-printerport -Name \""
				+ printer.getDeviceName() + "\" -PrinterHostAddress \"" + printer.getIp() + "\"").start();
		printProcessOutput(process5);
		
		Logger.info("done");
	}

	private void printProcessOutput(Process process) {
		InputStream stdIn = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(stdIn);
		BufferedReader br = new BufferedReader(isr);
		String line = null;

		try {
			while ((line = br.readLine()) != null) {
				Logger.msg(line);
			}
			int exitVal = process.waitFor();
			Logger.info("Process exit code: " + exitVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
