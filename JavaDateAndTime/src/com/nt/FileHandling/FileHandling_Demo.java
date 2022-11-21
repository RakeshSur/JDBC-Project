package com.nt.FileHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileHandling_Demo {

	public static void main(String[] args) {
		copyFileMethod();
	}

	public static void copyFileMethod()
	{
		File file = new File("D:\\image\\file.txt");
		File cFile = new File("D:\\image\\fileCopy.txt");

		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {

			fis = new FileInputStream(file);
			fos = new FileOutputStream(cFile);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			System.out.println(fis.available());
		} catch (IOException e) {

			e.printStackTrace();
		}
		int i = 0;
		try {
			while ((i = fis.read()) != -1) {
				fos.write(i);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fos.close();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		}

	}

}
