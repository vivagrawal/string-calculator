/**
 * 
 */
package com.capita.string.calculator.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.capita.string.calculator.common.CommonUtilities;
import com.capita.string.calculator.service.CalculatorService;
import com.capita.string.calculator.service.ReadMultipartFileService;

/**
 * @author Vivek Agrawal
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class InputControllerTest extends CommonUtilities {

	@Mock
	ReadMultipartFileService readMultipartFile;
	
	@Mock
	CalculatorService calculatorService;
	
	@InjectMocks
	InputController controller;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.capita.string.calculator.controller.InputController#solveMathematicalExpression(org.springframework.web.multipart.MultipartFile)}.
	 */
	@Test
	public void testSolveMathematicalExpression() {
		doReturn(mockListString).when(readMultipartFile).getInputStringFromMultipartFile(mockFile);
		doReturn(mockListString).when(calculatorService).calculateExpressionList(mockListString);
		
		controller.solveMathematicalExpression(mockFile);
		
		verify(readMultipartFile, times(1)).getInputStringFromMultipartFile(mockFile);
		verify(calculatorService, times(1)).calculateExpressionList(mockListString);
		
//		fail("Not yet implemented");
	}

}
