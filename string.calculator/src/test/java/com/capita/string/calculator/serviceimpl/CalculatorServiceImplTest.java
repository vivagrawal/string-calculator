/**
 * 
 */
package com.capita.string.calculator.serviceimpl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.capita.string.calculator.common.CommonUtilities;

/**
 * @author Vivek Agrawal
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceImplTest extends CommonUtilities {

	@InjectMocks
	CalculatorServiceImpl service;
	
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
	 * Test method for {@link com.capita.string.calculator.serviceimpl.CalculatorServiceImpl#calculateExpression(java.lang.String)}.
	 */
	@Test
	public void testCalculateExpressionValid() {
		assertEquals("158.00", service.calculateExpression("7+(6*5^2+3-4/2)"));
	}
	
	@Test
	public void testCalculateExpressionInValid() {
		assertEquals("INVALID EXPRESSION", service.calculateExpression("7+(67(56*2))"));
	}

	/**
	 * Test method for {@link com.capita.string.calculator.serviceimpl.CalculatorServiceImpl#calculateExpressionList(java.util.List)}.
	 */
	@Test
	public void testCalculateExpressionList() {
		
		service.calculateExpressionList(getExpressionList());
	}
	
}
