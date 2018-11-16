/**
 * 
 */
package com.capita.string.calculator.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Vivek Agrawal
 *
 */
public interface ReadMultipartFileService {
	
	/**
	 * This method return a {@link List} of String objects representing a line in the file.
	 * <b>Important:</b> The first line is ignored.
	 * @param file
	 * @return
	 */
	public List<String> getInputStringFromMultipartFile(MultipartFile file);

}
