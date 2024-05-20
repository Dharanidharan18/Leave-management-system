package com.chainsys.test;


public class PasswordValidator {

	public static boolean isValidPassword(String password) {
        return password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+.])(?=.*.\\d).{6,}");
    }

	
}
