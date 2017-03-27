package com.demo.utils;

import java.util.Random;

public class VerifyCodeGenerator {

	
	public static String getVerifyCode() {
		StringBuilder sb = new StringBuilder();
        Random random1 = new Random();
        for(int i=0; i< 6 ;i++){
        	sb.append(random1.nextInt(10)+"");
        }
        return sb.toString();
	}
	
}
