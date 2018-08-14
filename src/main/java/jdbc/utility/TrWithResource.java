package jdbc.utility;

public class TrWithResource implements AutoCloseable {
		public static void main(String[] args) throws Exception {
			TrWithResource t = null;
			try {
				t = new TrWithResource();
				t.open();
			} catch(Exception e) { 
				e.printStackTrace();
			} finally { 
				if (t != null) 
					t.close();
			}
		
	}
	public void open() {
		System.out.println("opening");
	}

	@Override  //narrower exception | broader
				// the same | unchecked exception | no exception at all.
	// no new or broader checked exception is allowed in overriding method in sub class. 
	public void close() throws Exception {		
		System.out.println("closing");
	}
	
	public TrWithResource() throws Exception { 
		System.out.println("creating");
	}

	

}
