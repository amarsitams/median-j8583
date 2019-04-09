package com.rumango.median.soap.utils;

/**
 * @author lei2o
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

public class TextFileModificationProgram
{
	private static final Logger LOGGER = Logger.getLogger(TextFileModificationProgram.class);

	public void modifyFile(String filePath, String oldString, String newString)
	{
		LOGGER.info(
				"=========> TextFileModificationProgram.modifyFile(String filePath, String oldString, String newString) <==========");
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		BufferedReader reader = null;
		FileWriter writer = null;
		try
		{
			reader = new BufferedReader(new FileReader(fileToBeModified));
			// Reading all the lines of input text file into oldContent
			String line = reader.readLine();
			while (line != null)
			{
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			// Replacing oldString with newString in the oldContent
			String newContent = oldContent.replaceAll(oldString, newString);
			// Rewriting the input text file with newContent
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e)
		{
			e.printStackTrace();
			LOGGER.error("TextFileModificationProgram.modifyFile() error");
			
		} finally
		{
			try
			{
				if (reader != null)
					reader.close();
				if (writer != null)
					writer.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
