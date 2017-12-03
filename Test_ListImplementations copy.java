import java.lang.reflect.Constructor;

/**
 * Example of a framework for testing multiple implementations of the
 * ListADT for TEAM_TestListImplementations assignment.
 * 
 * @version 0.0
 * @author deppeler
 *
 */
public class Test_ListImplementations {

	/**
	 * Run all tests for each list submission available.
	 * 
	 * @param args UNUSED
	 */
	public static void main(String[] args) {
		String [] ta_list = { "Deb", "Mingi", "Yash", "Sonu", 
						"Sapan", "Tianrun", "Roshan" };
		for ( String ta_name : ta_list ) { 
			String listClassName = "List_"+ta_name;
			System.out.println("\n==================== TESTING "+listClassName );
			runTestsFor(listClassName);
			System.out.println("==================== done");
		}
		
		
	}

	/**
	 * Constructs a list of the correct type based on the name provided.
	 * This method assumes there is a class with the name List_taName
	 * that implements the ListADT<T> type given.
	 * 
	 * For example: if taName is Deb, then it constructs an instance of
	 * a generic class with the following structure.
	 * 
	 *  <pre>public class List_Deb<T> implements ListADT<T> { ... }</pre>
	 * 
	 * @param listClassName The name "List_Deb" or other list type name.
	 */
	private static void runTestsFor(String listClassName) {
		try {
			ListADT<String> list = constructListOfString(listClassName);
			runTestsOn(listClassName,list);
		} catch (IllegalArgumentException e) {
			System.out.println("Unable to construct "+listClassName+" NO TESTS RUN");
		}
	}

	/**
	 * Constructs an instance of the list type that will contains String data.
	 * The class name is given and it is assumed to be a generic type with
	 * a no-arg constructor. If the List of Strings can not be constructed
	 * an IllegalArgumentException is thrown.
	 * 
	 * @param listname List_Deb or other that matches existing list type
	 * @return a ListADT<String> constructed with specified class name.
	 * 
	 * @throws IllegalArgumentException if any of several types of exceptions 
	 * occur when attempting to construct a ListADT<String> object from 
	 * the given class name. 
	 */
	private static ListADT<String> constructListOfString(String listname) {
		try {
			Class<?> listClassName = Class.forName(listname);
			Constructor<?> ctr = listClassName.getDeclaredConstructor();
			Object [] initargs = new Object[] { };

			// CAUTION: Type cast warnings are suppressed here.
			// to constructing Lists that will contain String data items.
			@SuppressWarnings("unchecked")  
			ListADT<String> list = ((ListADT<String>) ctr.newInstance(initargs));
			return list;
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to construct instance of "+listname+"<String>()");
		}
	}

	/**
	 * Runs all tests on this list.  Note, may have to create new lists 
	 * 
	 * to be sure that we start with a new empty list instance.  
	 * Otherwise, errors from one test may affect the next test.
	 * 
	 * @param className List_Deb or other list class name
	 * @param list 
	 */
	private static void runTestsOn(String className,ListADT<String> list) {

		if ( ! list.isEmpty() ) { 
			System.out.println("test00 failed: "+className+" should be empty at start. ");
		}

		if ( list.size() != 0 ) { 
			System.out.println("test00 failed: "+className+" size() should be 0 at start, but size()="+ list.size());
		}

		// TODO: name and write additional tests to run on each list.
		test01_add_Get_One_Item(className,list);
		test02_add_At_Negative_Pos(className, list);
		test03_add_A_Null_Item_At_End(className, list);
		test04_add_A_Null_Item_At_A_Pos(className, list);
		test05_add_Single_Item_To_Pos_Zero_Of_Empty_List(className, list);
		test06_add_Items_At_Front_Rear_And_In_Between(className, list);
		test07_add_Multiple_Items(className, list);
		
		test11_List_Contains(className,list);
		test09_List_remove(className, list);
		test10_add_At_Positive_Index_Out_Of_Bound(className, list);
		
				
	}
	

	 private static void failMsg(String msg, String expected, String actual) {
	        System.out.println("FAILED " + msg);
	        System.out.println("    expected: " + expected);
	        System.out.println("      actual: " + actual);
	    }

	/** 
	 * A sample test method.
	 * @param list
	 */
	private static void test01_add_Get_One_Item(String className, ListADT<String> list) {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName(); 
		list = constructListOfString(className);
		boolean failure = false;
		
		try{
			list.add("1");
			
			String getItem = list.get(0);
			String expItem = "1";
			
			if(getItem != expItem){
				failMsg(name + " for " + ta_name, "1", "" + getItem);
				failure = true;
			}
			
			if (!failure){
				System.out.println(name + " for " + ta_name + " passed");	
			}
			
			
		}catch(Exception e){
			failMsg(name+ " add(\"1\") for "+ ta_name, "No exception", ""+e);
			
		}
	
	}
	 
	 private static void test02_add_At_Negative_Pos(String className, ListADT<String> list) {
			list = constructListOfString(className);
			String name = new Object(){}.getClass().getEnclosingMethod().getName();
			String ta_name = list.getClass().getName();
			
			
			try {
				list.add(-1,"a");
				failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
			}
			
			catch (IndexOutOfBoundsException e) {
				System.out.println(name + " for " + ta_name + " passed");
			}
			
			catch (Exception e) {
				failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
			}
		}
	
	 private static void test03_add_A_Null_Item_At_End(String className, ListADT<String> list) {
 		list = constructListOfString(className);
 		String name = new Object(){}.getClass().getEnclosingMethod().getName();
 		String ta_name = list.getClass().getName();
 		
 		
 		// should we include list.add(null) general check?
 		try {
 			String item = null;
 			list.add(item);
 			failMsg(name+" for "+ta_name,"IllegalArgumentException","Not throwing an exception");
 		}
 		
 		catch (IllegalArgumentException e) {
 			System.out.println(name + " for " + ta_name + " passed");
 		}
 		
 		catch (Exception e) {
 			failMsg(name+" for "+ta_name,"IllegalArgumentException", ""+e);
 		}
 	
 	}
	
	private static void test04_add_A_Null_Item_At_A_Pos(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		
		// should we include list.add(null) general check?
		try {
			String item = null;
			list.add(0,item);
			failMsg(name+" for "+ta_name,"IllegalArgumentException","Not throwing an exception");
		}
		
		catch (IllegalArgumentException e) {
			System.out.println(name + " for " + ta_name + " passed");
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,"IllegalArgumentException", ""+e);
		}
	
	}
	
	private static void test05_add_Single_Item_To_Pos_Zero_Of_Empty_List(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		String expItem = "1";
		try {
			list.add(0,"1");
			String getItem = list.get(0);
			
			if (!getItem.equals(expItem)) {
				failMsg(name+" for "+ta_name,expItem, ""+getItem);
			}
			
			else {
				System.out.println(name + " for " + ta_name + " passed");
			}
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,expItem, ""+e);
		}
	}
	
	private static void test06_add_Items_At_Front_Rear_And_In_Between(String className, ListADT<String> list) {
		// It seems that this method will cause an infinite loop for Sapan.
		// So we will manually break out of that.
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName(); 
		
		String expItem = "2314";
		try {
			list.add(0,"1");
			list.add(0,"2"); // add to front
			list.add(1,"3"); // add in between
			list.add(3,"4"); // add to rear
			String getItem = "" + list.get(0) + list.get(1) + list.get(2) + list.get(3);
			
			if (!getItem.equals(expItem)) {
				failMsg(name+" for "+ta_name,expItem, ""+getItem);
			}
			
			else {
				System.out.println(name + " for " + ta_name + " passed");
			}
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,expItem, ""+e);
		}
	}
	
	 private static void test07_add_Multiple_Items(String className, ListADT<String> list){
		 String name = new Object(){}.getClass().getEnclosingMethod().getName();
		 String ta_name = list.getClass().getName();
		 
		 boolean failure = false;
		 
		 list = constructListOfString(className);
		 int numItems = 101; //
		 int i=0;
		 try{
			 
			 for (i=0; i<=numItems;i++){
				 list.add(""+i); 
				 
				 if (!list.get(i).equals(""+i)){
					 failMsg(name + "for list.get(" + i + ").equals(\"\"+" + i  + ") for " + ta_name, "true", "false");
					 failure = true;
				 }
		 
			 } 
			 
			 if (!failure){
					System.out.println(name + " for " + ta_name + " passed");	
			 }
			 
		 } catch (Exception e){
			failMsg(name + "_add("+ i + ") for " + ta_name, "No exception", ""+e);
		 }
		 
		 
	 }
	 

	 
        
    
	    /**
	       * test add("item") and then remove() on a non-empty list
	         */
	        public static void test09_List_remove(String className, ListADT<String> list) {
	        	String name = new Object(){}.getClass().getEnclosingMethod().getName();
	    		String ta_name = list.getClass().getName();
	            list = constructListOfString(className);
	            
	            boolean failure = false;
	            int initialSize = list.size();
	            try{
		            // add "item", then "remove"
		            String expected = "item";
		            list.add(expected);
		            String actual = list.remove(0);
		            if (!expected.equals(actual)) {
		            	System.out.println("Testing remove method failed1");
		                failMsg(name,""+expected,""+actual);
		                failure = true;
		            }
		            // check that size is back to 0
		            expected = ""+initialSize;
		            actual = list.size()+"";
		            if (!expected.equals(actual)) {
		            	System.out.println("Testing remove method failed2");
		                failMsg(name+" size()",""+expected,""+actual);
		                failure = true;
		    
		            }
		            
		            if (!failure){
						System.out.println(name + " for " + ta_name + " passed");	
		            }
		            
	            }catch(Exception e)
	            {
	            	failMsg(name + " for " + ta_name, "No exception", ""+e);
	           }
	        }
	
	       
	        
	     // Note from Hongyi: for test 03-06, I did not check the size of the list because I think the size may better to be tested
	    	// separately. 
	    	// Also test06 will cause an infinite loop for Saipan.
	    	private static void test10_add_At_Positive_Index_Out_Of_Bound(String className, ListADT<String> list) {
	    		list = constructListOfString(className);
	    		String name = new Object(){}.getClass().getEnclosingMethod().getName();
	    		String ta_name = list.getClass().getName();
	    		
	    		try {
	    			list.add(1,"a");
	    			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
	    		}
	    		
	    		catch (IndexOutOfBoundsException e) {
	    			System.out.println(name + " for " + ta_name + " passed");
	    		}
	    		
	    		catch (Exception e) {
	    			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
	    		}
	    	}
	    	
	    	 /**
	         * creates empty List and adds multiple items
	         * and checks that size is one
	         */
	        public static void test11_List_Contains(String className, ListADT<String> list) {
	        	String name = new Object(){}.getClass().getEnclosingMethod().getName();
	    		String ta_name = list.getClass().getName();
	    		list = constructListOfString(className);
	            int numItems = 101; // number of items to add and check size
	            
	            boolean failure = false;
	            
	            try{
	            	
	            // should be big enough to force expand
	            	
	            	
	            for ( int i=0; i < numItems; i++ ) {
	                String s = "item_"+i;
	                list.add(s);
	                boolean expected = true;
	                boolean actual = list.contains(s);
	                if ( expected != actual ) {
	                    failMsg(name+ "for ContainsItem_"+s,""+expected,""+actual);
	                    failure = true;
	                }
	               
	            } 
	            
	            if (!failure){
	    			System.out.println(name + " for " + ta_name + " passed");	
	            }
	            
	            } catch(Exception e)
	            {
	            	failMsg(name+ "for " + ta_name, "No exception", ""+e);
	            }
	            
	        }
	
		private static void test07_get_At_Negative_Pos(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		try {
			list.get(-1);
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
		}
		
		catch (IndexOutOfBoundsException e) {
			System.out.println("test passed");
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
		}
	}
	
	private static void test08_get_At_Pos_Zero_Of_Empty_List(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		try {
			list.get(0);
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
		}
		
		catch (IndexOutOfBoundsException e) {
			System.out.println("test passed");
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
		}
	}
	
	private static void test09_remove_At_Negative_Pos(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		try {
			list.remove(-1);
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
		}
		
		catch (IndexOutOfBoundsException e) {
			System.out.println("test passed");
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
		}
	}
	
	private static void test10_remove_At_Pos_Zero_Of_Empty_List(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		try {
			list.remove(0);
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
		}
		
		catch (IndexOutOfBoundsException e) {
			System.out.println("test passed");
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
		}
	}

	private static void test12_get_At_Negative_Pos(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		try {
			list.get(-1);
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
		}
		
		catch (IndexOutOfBoundsException e) {
			System.out.println(name + " for " + ta_name + " passed");
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
		}
	}
	
	private static void test13_get_At_Pos_Zero_Of_Empty_List(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		try {
			list.get(0);
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
		}
		
		catch (IndexOutOfBoundsException e) {
			System.out.println(name + " for " + ta_name + " passed");
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
		}
	}
	
	private static void test14_remove_At_Negative_Pos(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		try {
			list.remove(-1);
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
		}
		
		catch (IndexOutOfBoundsException e) {
			System.out.println(name + " for " + ta_name + " passed");
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
		}
	}
	
	private static void test15_remove_At_Pos_Zero_Of_Empty_List(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		try {
			list.remove(0);
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
		}
		
		catch (IndexOutOfBoundsException e) {
			System.out.println(name + " for " + ta_name + " passed");
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
		}
	}
	
	private static void test16_remove_At_Size(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		try {
			list.add("a");
			list.remove(list.size());
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
		}
		
		catch (IndexOutOfBoundsException e) {
			System.out.println(name + " for " + ta_name + " passed");
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
		}
	}
	
}
