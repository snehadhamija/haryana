package com.stanzaliving.api.Gateway;

import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		String toList = "hey,there";
		
		List<String> recipients = Arrays.asList(toList.split(","));
		System.out.println(recipients);
	}

}
