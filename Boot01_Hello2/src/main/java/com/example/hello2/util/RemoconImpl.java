package com.example.hello2.util;

public class RemoconImpl implements Remocon {

	@Override
	public void up() {
		System.out.println("Up");
	}

	@Override
	public void down() {
		System.err.println("Down");
	}

}
