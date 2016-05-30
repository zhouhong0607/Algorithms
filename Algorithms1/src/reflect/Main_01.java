package reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public class Main_01
{
	public static void main(String[] args) throws Exception
	{
		Constructor[] constructors=Example_01.class.getConstructors();
		Annotation annotation;
		for(Constructor c:constructors)
		{
			System.out.println(c.getModifiers());
		}
	}
}
