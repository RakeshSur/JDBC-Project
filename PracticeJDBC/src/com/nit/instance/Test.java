package com.nit.instance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
	IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
	Constructor<Student> sons = Student.class.getConstructor();
	Student s = sons.newInstance();
	System.out.println(s);
	
	}

}
