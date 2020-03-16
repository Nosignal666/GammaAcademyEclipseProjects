package corsojava.EsercizioThread;

public class ThreadMain {
	public static void main(String[] args){
		try{
			ThreadTest t1=new ThreadTest("PRIMO",700);
			ThreadTest t2=new ThreadTest("SECONDO",300);
			t1.start();
			t2.start();
			
			t1.join();
			t2.join();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
