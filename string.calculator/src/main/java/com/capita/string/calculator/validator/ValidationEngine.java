/**
 * 
 */
package com.capita.string.calculator.validator;

import java.util.List;

import com.capita.string.calculator.beans.Unit;
import com.capita.string.calculator.utility.Constants;

/**
 * @author Vivek Agrawal
 *
 */
public class ValidationEngine implements Constants {
	
	public boolean isValidExpression(List<Unit> unitList, int bracesCount) {
		
		String expression = "";
		for(Unit u : unitList) {
			expression = expression.concat(u.getValue());
		}
		if(!expression.matches("[^\\^*-+\\/][0-9+\\-*\\/()^\\.]*") || bracesCount != 0) {
			return false;
		}
		long openBracesCount = unitList.stream().filter(a -> OPEN_BRACES.equals(a.getValue())).count();
		long closedBracesCount = unitList.stream().filter(a -> CLOSED_BRACES.equals(a.getValue())).count();
		
		String tmpExpression = expression;
		while(openBracesCount != 0) {
			int index = tmpExpression.indexOf("(");
			if(index != 0 && Character.isDigit(expression.charAt(index - 1))) {
				return false;
			} else {
				tmpExpression = tmpExpression.replaceFirst("\\(", "");
			}
			openBracesCount--;
		}
		
		tmpExpression = expression;
		while(closedBracesCount != 0) {
			int index = tmpExpression.indexOf(")");
			if(index < (tmpExpression.length() - 1) && Character.isDigit(expression.charAt(index + 1))) {
				return false;
			} else {
				tmpExpression = tmpExpression.replaceFirst("\\)", "");
			}
			closedBracesCount--;
		}
		return true;
	}

}
