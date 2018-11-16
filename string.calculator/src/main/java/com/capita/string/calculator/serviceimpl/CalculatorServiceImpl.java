/**
 * 
 */
package com.capita.string.calculator.serviceimpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capita.string.calculator.beans.Unit;
import com.capita.string.calculator.service.CalculatorService;
import com.capita.string.calculator.utility.Constants;
import com.capita.string.calculator.validator.ValidationEngine;

/**
 * @author Vivek Agrawal
 *
 */
@Service
public class CalculatorServiceImpl implements CalculatorService, Constants {

	/* (non-Javadoc)
	 * @see com.capita.string.calculator.service.CalculatorService#calculateExpression(java.lang.String)
	 */
	@Override
	public String calculateExpression(String expression) {

		expression = expression.replace(" ", "");

		String value = "INVALID EXPRESSION";
		
		try {
			double finalAnswer = evaluateExpression(separateOperatorsAndOperands(expression), 0);
			value = new DecimalFormat("####0.00").format(finalAnswer);
		} catch(Exception e) {
			//DO NOTHING
		}
		
		return value;
	}

	/* (non-Javadoc)
	 * @see com.capita.string.calculator.service.CalculatorService#calculateExpressionList(java.util.List)
	 */
	@Override
	public List<String> calculateExpressionList(List<String> expressionList) {
		
		List<String> outputList = new LinkedList<String>();
		int index = 1;
		for(String expression : expressionList) {
			outputList.add("Case #" + index + ": ".concat(calculateExpression(expression)));
			index++;
		}
		
		return outputList;
	}
	
	/**
	 * @param expression
	 */
	private List<Unit> separateOperatorsAndOperands(String expression) {
		List<Unit> unitList = new ArrayList<Unit>();

		String[] characterArray = expression.split("");

		boolean operandFlag = false;
		Unit operandUnit = new Unit();

		for (String character : characterArray) {
			if (character.matches("[0-9]")) {
				operandFlag = true;
				operandUnit.setOperand(true);
				String value = String.valueOf((Integer.parseInt(operandUnit.getValue()) * 10) + Integer.parseInt(character));
				operandUnit.setOperand(true);
				operandUnit.setValue(value);
			} else {
				if (operandFlag == true) {
					unitList.add(operandUnit);
					operandUnit = new Unit();
					operandFlag = false;
				}
				Unit operatorUnit = new Unit();
				operatorUnit.setOperand(false);
				operatorUnit.setValue(character);
				unitList.add(operatorUnit);
			}
		}
		if (operandFlag == true) {
			unitList.add(operandUnit);
		}
		
		return unitList;
	}
	
	/**
	 * 
	 * @param unitList
	 * @param finalAnswer
	 * @return
	 * @throws Exception 
	 */
	private double evaluateExpression(List<Unit> unitList, double finalAnswer) throws Exception {

		long openBracesCount = unitList.stream().filter(a -> OPEN_BRACES.equals(a.getValue())).count();

		while (openBracesCount != 0) {

			boolean bracesPresent = false;
			int bracesCount = 0;
			int startIndex = 0;
			int endIndex = 0;
			for (int index = 0; index < unitList.size(); index++) {
				if (OPEN_BRACES.equals(unitList.get(index).getValue())) {
					bracesCount++;
					if (bracesPresent == false) {
						bracesPresent = true;
						startIndex = index;
					}
				} else if (CLOSED_BRACES.equals(unitList.get(index).getValue())) {
					bracesCount--;
				}
				if (bracesPresent && bracesCount == 0) {
					endIndex = index;
					break;
				}
			}
			
			ValidationEngine validator = new ValidationEngine();
			if (!validator.isValidExpression(unitList, bracesCount)) {
				throw new Exception();
			}

			finalAnswer = evaluateExpression(unitList.subList(startIndex + 1, endIndex), finalAnswer);
			unitList.remove(startIndex);
			unitList.remove(startIndex + 1);
			openBracesCount = unitList.stream().filter(a -> OPEN_BRACES.equals(a.getValue())).count();
		}
		
		long caratCount = unitList.stream().filter(a -> CARAT.equals(a.getValue())).count();
		long asterikCount = unitList.stream().filter(a -> ASTERIK.equals(a.getValue())).count();
		long divideCount = unitList.stream().filter(a -> DIVIDE.equals(a.getValue())).count();
		long plusCount = unitList.stream().filter(a -> PLUS.equals(a.getValue())).count();
		long minusCount = unitList.stream().filter(a -> MINUS.equals(a.getValue())).count();
		
		//Evaluating the expression on the basis of priority.
		finalAnswer = caratOperation(unitList, caratCount, finalAnswer);
		finalAnswer = asterikOperation(unitList, asterikCount, finalAnswer);
		finalAnswer = divideOperation(unitList, divideCount, finalAnswer);
		finalAnswer = additionOperation(unitList, plusCount, finalAnswer);
		finalAnswer = subtractionOperation(unitList, minusCount, finalAnswer);

		return finalAnswer;
	}
	
	private double caratOperation(List<Unit> unitList, long caratCount, double finalAnswer) {
		int i = 3;
		while (caratCount != 0) {
			for (int index = 0; index < unitList.size(); index++) {
				Unit u = unitList.get(index);
				if (CARAT.equals(u.getValue())) {
					double firstOperand = Double.parseDouble(unitList.get(index - 1).getValue());
					double secondOperand = Double.parseDouble(unitList.get(index + 1).getValue());

					finalAnswer = Math.pow(firstOperand, secondOperand);

					while (i != 0) {
						unitList.remove(index - 1);
						i--;
					}
					Unit tempUnit = new Unit();
					tempUnit.setOperand(true);
					tempUnit.setValue(String.valueOf(finalAnswer));
					unitList.add(index - 1, tempUnit);

					i = 3;
					break;
				}
			}
			caratCount--;
		}
		
		return finalAnswer;
	}
	
	private double asterikOperation(List<Unit> unitList, long asterikCount, double finalAnswer) {
		int i = 3;
		while (asterikCount != 0) {
			for (int index = 0; index < unitList.size(); index++) {
				Unit u = unitList.get(index);
				if (ASTERIK.equals(u.getValue())) {
					double firstOperand = Double.parseDouble(unitList.get(index - 1).getValue());
					double secondOperand = Double.parseDouble(unitList.get(index + 1).getValue());

					finalAnswer = firstOperand * secondOperand;

					while (i != 0) {
						unitList.remove(index - 1);
						i--;
					}
					Unit tempUnit = new Unit();
					tempUnit.setOperand(true);
					tempUnit.setValue(String.valueOf(finalAnswer));
					unitList.add(index - 1, tempUnit);

					i = 3;
					break;
				}
			}
			asterikCount--;
		}
		
		return finalAnswer;
	}

	private double divideOperation(List<Unit> unitList, long divideCount, double finalAnswer) {
		int i = 3;
		while (divideCount != 0) {
			for (int index = 0; index < unitList.size(); index++) {
				Unit u = unitList.get(index);
				if (DIVIDE.equals(u.getValue())) {
					double firstOperand = Double.parseDouble(unitList.get(index - 1).getValue());
					double secondOperand = Double.parseDouble(unitList.get(index + 1).getValue());

					finalAnswer = firstOperand / secondOperand;

					while (i != 0) {
						unitList.remove(index - 1);
						i--;
					}
					Unit tempUnit = new Unit();
					tempUnit.setOperand(true);
					tempUnit.setValue(String.valueOf(finalAnswer));
					unitList.add(index - 1, tempUnit);

					i = 3;
					break;
				}
			}
			divideCount--;
		}
		return finalAnswer;
	}
	
	private double additionOperation(List<Unit> unitList, long plusCount, double finalAnswer) {
		int i = 3;
		while (plusCount != 0) {
			for (int index = 0; index < unitList.size(); index++) {
				Unit u = unitList.get(index);
				if (PLUS.equals(u.getValue())) {
					double firstOperand = Double.parseDouble(unitList.get(index - 1).getValue());
					double secondOperand = Double.parseDouble(unitList.get(index + 1).getValue());

					finalAnswer = firstOperand + secondOperand;

					while (i != 0) {
						unitList.remove(index - 1);
						i--;
					}
					Unit tempUnit = new Unit();
					tempUnit.setOperand(true);
					tempUnit.setValue(String.valueOf(finalAnswer));
					unitList.add(index - 1, tempUnit);

					i = 3;
					break;
				}
			}
			plusCount--;
		}
		return finalAnswer;
	}
	
	private double subtractionOperation(List<Unit> unitList, long minusCount, double finalAnswer) {
		int i = 3;
		while (minusCount != 0) {
			for (int index = 0; index < unitList.size(); index++) {
				Unit u = unitList.get(index);
				if (MINUS.equals(u.getValue())) {
					double firstOperand = Double.parseDouble(unitList.get(index - 1).getValue());
					double secondOperand = Double.parseDouble(unitList.get(index + 1).getValue());

					finalAnswer = firstOperand - secondOperand;

					while (i != 0) {
						unitList.remove(index - 1);
						i--;
					}
					Unit tempUnit = new Unit();
					tempUnit.setOperand(true);
					tempUnit.setValue(String.valueOf(finalAnswer));
					unitList.add(index - 1, tempUnit);

					i = 3;
					break;
				}
			}
			minusCount--;
		}
		return finalAnswer;
	}
	
}
