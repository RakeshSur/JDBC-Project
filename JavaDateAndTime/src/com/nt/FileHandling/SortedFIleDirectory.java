package com.nt.FileHandling;

import java.io.File;
import java.util.Arrays;

public class SortedFIleDirectory {

	public static void main(String[] args) {
		String dPath = "D:\\image";
		File file = new File(dPath);
		
		File ImageDir[] =  file.listFiles();
		Arrays.sort(ImageDir);
		
		for(File e : ImageDir) {
			if(e.isFile()) {
			System.out.println("File : "+e.getName()+" "+e.getAbsolutePath());
			}
			else if(e.isDirectory()) {
				System.out.println("Directory : "+e.getName()+" "+e.getAbsolutePath());
			}
			else {
				System.out.println("Not Known : "+e.getName()+" "+e.getAbsolutePath());
			}
		}
	}

}
