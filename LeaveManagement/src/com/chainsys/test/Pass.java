package com.chainsys.test;

import java.util.Scanner;

public class Pass {

//	public static void main(String[] args) {
//		login();
//	} 
	public static void login() {

		String password;
		Scanner sc = new Scanner(System.in);
		String passwordregex = "^[A-Za-z0-9]+$";
		System.out.println("Enter the password");
		password = sc.next();
		if (password.matches(passwordregex)) {
			System.out.println("success");
		} else {
			System.out.println("Invalid password");
		}
		
	}

}
