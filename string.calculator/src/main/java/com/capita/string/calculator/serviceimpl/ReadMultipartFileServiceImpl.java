/**
 * 
 */
package com.capita.string.calculator.serviceimpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capita.string.calculator.service.ReadMultipartFileService;

/**
 * @author Vivek Agrawal
 *
 */
@Service
public class ReadMultipartFileServiceImpl implements ReadMultipartFileService {

	/* (non-Javadoc)
	 * @see com.capita.string.calculator.service.ReadMultipartFile#getInputStringFromMultipartFile(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public List<String> getInputStringFromMultipartFile(MultipartFile file) {
		
		List<String> inputList = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line = "";
			line = br.readLine();
			while((line = br.readLine()) != null) {
				inputList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return inputList;
	}

}
