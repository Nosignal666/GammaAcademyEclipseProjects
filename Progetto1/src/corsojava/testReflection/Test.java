package corsojava.testReflection;

public class Test extends SuperTest{
	private String s;
	public Test(){
		s="Geek";
	}
	public void method1(){
		System.out.println("the string is "+s);
	}
	public void method2(int n){
		System.out.println("the number is "+n);
	}
	private void method3(){
		System.out.println("private method invokeed");
	}
}
