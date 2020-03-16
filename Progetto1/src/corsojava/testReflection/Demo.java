package corsojava.testReflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import corsojava.anagrafica.DatiStudente;

public class Demo {
	public static void main(String[] args){
		try{
			Test obj=new Test();
			
			Class<?> cls=obj.getClass();
			System.out.println("The name of the class is "+cls.getName());
			
			Constructor<?> constructor;
			constructor = cls.getConstructor();
			System.out.println("the name of the constructor is "+constructor.getName());
		
			
			Method[] method=cls.getMethods();
			for(Method me: method){
				System.out.println(me.getName());
			}
			
			Method methodcall1=cls.getDeclaredMethod("method2",int.class);
			methodcall1.invoke(obj,1);
			
			Field field=cls.getDeclaredField("s");
			field.setAccessible(true);
			field.set(obj,"java");
			Method methodcall2=cls.getDeclaredMethod("method1");
			methodcall2.invoke(obj);
			
			
			Method methodcall3=cls.getDeclaredMethod("method3");
			methodcall3.setAccessible(true);
			methodcall3.invoke(obj);
			
			
			
			Class<?> clazz=obj.getClass();
			Field[] fields=clazz.getDeclaredFields();
			while(!clazz.getName().contentEquals("java.lang.Object")){
				System.out.println("fields of class" + clazz.getName());
				for(Field f:fields){
					System.out.println(f.getName());
				}
				clazz=clazz.getSuperclass();
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
