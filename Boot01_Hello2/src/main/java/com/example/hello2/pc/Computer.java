package com.example.hello2.pc;

public class Computer {
	private Cpu cpu;
	
	public Computer(Cpu cpu) {
		this.cpu = cpu;
	}
	
	public void action() {
		System.out.println("Run Computer");
	}
}
