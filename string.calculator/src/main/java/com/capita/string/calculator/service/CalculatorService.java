/**
 * 
 */
package com.capita.string.calculator.service;

import java.util.List;

/**
 * @author Vivek Agrawal
 *
 */
public interface CalculatorService {
	
	/**
	 * This method calculates a single mathematical expression and returns the value, if valid,
	 * otherwise returns "INVALID EXPRESSION"
	 * @param expression
	 * @return
	 */
	public String calculateExpression(String expression);
	
	/**
	 * This method takes the input as a {@link List} of mathematical expressions that needs to be evaluated
	 * and returns an output list with the values.
	 * @param expressionList
	 * @return {@link List} of {@link String} values
	 */
	public List<String> calculateExpressionList(List<String> expressionList);

}
