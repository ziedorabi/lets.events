package com.aleria.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

	public static boolean validatePassword(String password) {
		boolean status = false;
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		status = matcher.matches();
		return status;
	}

	public static boolean validateNumber(String number) {
		boolean status = false;
		String regex = "[0-9].{7}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(number);
		status = matcher.matches();
		return status;
	}

	public static boolean validateMail(String mail) {
		boolean status = false;
		String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		status = matcher.matches();
		return status;
	}

	public static boolean validateUserName(String userName) {
		boolean status = false;
		String regex = "^[A-Za-z][A-Za-z0-9_]{7,29}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userName);
		status = matcher.matches();
		return status;
	}

}
