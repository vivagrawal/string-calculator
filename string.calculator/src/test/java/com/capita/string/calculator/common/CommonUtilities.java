/**
 * 
 */
package com.capita.string.calculator.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.mock.web.MockMultipartFile;

/**
 * @author Vivek Agrawal
 *
 */
public class CommonUtilities {
	
	public static List<String> mockListString = Arrays.asList("a,b,c");
	
	public static List<String> getExpressionList() {
		List<String> mockExpressionList = new ArrayList<>();
		mockExpressionList.add("3*5");
		mockExpressionList.add("++2+5");
		mockExpressionList.add("(3+2)");
		mockExpressionList.add("(3(5+9)");
		mockExpressionList.add("(23*3+(2/)");
		mockExpressionList.add("7+(6*5^2+3-4/2)");
		mockExpressionList.add("23(2+3))");
		mockExpressionList.add("(23+34)81");
		mockExpressionList.add("#$%^");
		
		return mockExpressionList;
	}

	public static MockMultipartFile mockFile = new MockMultipartFile("mockFile", "mock".getBytes());
	
}
