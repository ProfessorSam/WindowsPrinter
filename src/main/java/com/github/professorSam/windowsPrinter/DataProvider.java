package com.github.professorSam.windowsPrinter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class DataProvider {
	
	private static DataProvider dataProvider;
	private List<Printer> printers = new ArrayList<>();
	
	public DataProvider() {
		if(dataProvider != null) {
			return;
		}
		DataProvider.dataProvider = this;
		Logger.info("Lade Druckerdaten");
		File file = new File("printer.csv");
		try {
			for(String line : Files.readAllLines(Paths.get(file.toURI()), StandardCharsets.ISO_8859_1)) {
				String[] args = line.split(";");
				if(args.length != 4) {
					Logger.error("Fehlerhafte csv datei bei: " + line);
					continue;
				}
				printers.add(new Printer(args[0], args[1], args[2], args[3]));
			}
			printers.forEach(Logger::info);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static DataProvider getInstance() {
		return dataProvider;
	}
}
