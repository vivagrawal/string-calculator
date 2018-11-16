/**
 * 
 */
package com.capita.string.calculator.serviceimpl;

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
public class ReadMultipartFileServiceImplTest extends CommonUtilities {

	@InjectMocks
	ReadMultipartFileServiceImpl service;
	
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
	 * Test method for {@link com.capita.string.calculator.serviceimpl.ReadMultipartFileServiceImpl#getInputStringFromMultipartFile(org.springframework.web.multipart.MultipartFile)}.
	 */
	@Test
	public void testGetInputStringFromMultipartFile() {
		service.getInputStringFromMultipartFile(mockFile);
	}
}
