import java.util.Date;


public class Test {
	 public static void main(String[] args) throws  Throwable {
		 	System.out.println(System.getProperties());
	        
	          java.lang.reflect.Method m = java.lang.ClassLoader.class.getDeclaredMethod("loadLibrary", Class.class, String.class, Boolean.TYPE);
	          m.setAccessible(true);
	          m.invoke(null, java.lang.System.class, "jvmfaketime", false);

		 	
	        System.out.println(System.currentTimeMillis());
	        System.out.println(new Date());
	        System.registerFakeCurrentTimeMillis();
	        System.out.println(System.currentTimeMillis());
	        System.out.println(new Date());
	        System.deregisterFakeCurrentTimeMillis();
	        System.out.println(System.currentTimeMillis());
	        System.out.println(new Date());
	        
	        System.registerFakeCurrentTimeMillis();
	        
	        System.out.println("offset " + System.getTimeOffset() + " " + new Date());
	        System.setTimeOffset(2000);
	        System.out.println("offset " + System.getTimeOffset() + " " + new Date());

	 }
}
