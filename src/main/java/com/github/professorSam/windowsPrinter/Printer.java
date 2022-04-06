package com.github.professorSam.windowsPrinter;

public class Printer {

	private String deviceName;
	private String position;
	private String model;
	private String ip;
	
	public Printer() {
	}

	public Printer(String deviceName, String position, String model, String ip) {
		super();
		this.deviceName = deviceName;
		this.position = position;
		this.model = model;
		this.ip = ip;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public String getPosition() {
		return position;
	}

	public String getModel() {
		return model;
	}

	public String getIp() {
		return ip;
	}
}
