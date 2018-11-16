/**
 * 
 */
package com.capita.string.calculator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capita.string.calculator.service.CalculatorService;
import com.capita.string.calculator.service.ReadMultipartFileService;

/**
 * {@link InputController} contains methods for taking the input in the form of file location or file(To be implemented in a later version)
 * 
 * @author Vivek Agrawal
 *
 */
@RestController("/")
public class InputController {

	@Autowired
	ReadMultipartFileService readMultipartFile;
	
	@Autowired
	CalculatorService calculatorService;
	
	@PostMapping("/solve")
	public List<String> solveMathematicalExpression(@RequestParam("file") MultipartFile file) {
		
		List<String> inputList = readMultipartFile.getInputStringFromMultipartFile(file);
		
		return calculatorService.calculateExpressionList(inputList);
	}
}
