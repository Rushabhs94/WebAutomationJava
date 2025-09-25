package com.web.automation.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.web.automation.constants.PathConstants;

public class FileUtility {

	public static void deleteDirectory(File file) {
		// store all the paths of files and folders present
		// inside directory
		for (File subfile : file.listFiles()) {
			// recursively call function to empty subfolder
			if (subfile.isDirectory()) {
				deleteDirectory(subfile);
			}
			// delete files and empty subfolders
			subfile.delete();
		}
	}

	public static List<String> getFileName() {
		File folder = new File(PathConstants.DOWNLOAD_FILE_PATH);
		File[] listOfFiles = folder.listFiles();
		List<String> fileName = new ArrayList<String>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File Name " + listOfFiles[i].getName());
				fileName.add(listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
		return fileName;
		
	}

	public static void main(String[] args) {
		File file = new File(PathConstants.DOWNLOAD_FILE_PATH);
		deleteDirectory(file);
		getFileName();
	}

}
