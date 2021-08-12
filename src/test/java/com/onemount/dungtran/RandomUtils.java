package com.onemount.dungtran;

import java.util.Random;

public class RandomUtils {
	
	
	static int number = new Random().nextInt();
	
	public static String randomEmail() {	
		String email = String.format("dungttt%d@gmail.com",Math.abs(number));
		return email;
	}
	public static String randomPassword() {
		String password = String.format("Dung%d",Math.abs(number));
		return password;
	}
}
