package com.example.hello2.util;

public class TvRemocon implements Remocon {

	@Override
	public void up() {
		System.out.println("Chennel Up");
	}

	@Override
	public void down() {
		System.out.println("Chennel Down");
	}

}
