/**
 * 
 */
package com.capita.string.calculator.beans;

/**
 * {@link Unit} bean is used to store each component of the input string, and differentiates between the operator and
 * operand using the isOperand flag. 
 * 
 * @author Vivek Agrawal
 *
 */
public class Unit {
	
	
private boolean isOperand;
	
	private String value = "0";

	/**
	 * @return {@link Boolean} isOperand
	 */
	public boolean isOperand() {
		return isOperand;
	}

	/**
	 * @param isOperand to set
	 */
	public void setOperand(boolean isOperand) {
		this.isOperand = isOperand;
	}

	/**
	 * @return {@link String} value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
