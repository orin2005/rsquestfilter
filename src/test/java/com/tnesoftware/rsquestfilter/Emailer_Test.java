package com.tnesoftware.rsquestfilter;

import com.tnesoftware.rsquestfilter.tools.Emailer;

public class Emailer_Test {
	
	public static void main(String[] args)
	{
		String feedback = "Your app is the bee's knees";
		
		Emailer.emailFeedback(feedback);
	}

}
