// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES04
//
// ***************************************************
package MusicLandscape.tests;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MusicLandscape.entities.*;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.fail;


/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES04
 *
 */
public class ReleaseTest {
	
	private class TestRelease extends Release {
		public TestRelease(Release toCopy) {
			// TODO Auto-generated constructor stub
			super(toCopy);
		}

		public TestRelease() {
			// TODO Auto-generated constructor stub
		}

		public TestRelease(String title, Artist artist, int year) {
			// TODO Auto-generated constructor stub
			super(title, artist, year);
		}

		/**
		 * make sure to have totalTime marked as abstract!!
		 */
		@Override
		public int totalTime() {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
	TestRelease toTest;
	
	@BeforeMethod
	private void init(){
		toTest=new TestRelease();
	}
	

	/**
	   * tries to get a private field of the given object
	   * @param myObject Object of which the field should be reached
	   * @param fieldName String which defines the field to be retrieved
	   * @return new Field object if found, fails if NoSuchFieldException
	   */
		private <T> Field getPrivateField(T myObject, String fieldName, boolean isBase){
			Field privateStringField;
			try {
				privateStringField = (isBase)?myObject.getClass().getDeclaredField(fieldName):myObject.getClass().getSuperclass().getDeclaredField(fieldName);
				privateStringField.setAccessible(true);
				return privateStringField;
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				fail("member \""+fieldName+"\" not found");
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
  @Test
  public void Release() {
	  
	  
	// artist !=null, venue=null, date null, attendees 0, description ""
			String message = "";
			List<Object[]> myList = new ArrayList<Object[]>();

			myList.add(new Object[] { "nn", "artist", "not null" });
			myList.add(new Object[] { "s", "title", null });
			myList.add(new Object[] { "i", "year", 1900 });
			

			for (Object[] li : myList) {
				message = String.format("initial value for %s should be %s\n",
						li[1], li[2]);
				try {
					switch (li[0].toString()) {
					case "i":
					case "s":
						assertEquals(
								getPrivateField(toTest, li[1].toString(), false).get(
										toTest), li[2], message);
						break;
					case "nn":
						assertNotNull(getPrivateField(toTest, li[1].toString(), false)
								.get(toTest), message);
						break;
					}

				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    
  }

  @Test
  public void ReleaseRelease() {
	  Release toCopy= new TestRelease();

		toCopy.setArtist(new Artist("Metallica"));
		toCopy.setTitle("my release");
		toCopy.setYear(1985);
		
		
		Release toCheck=new TestRelease(toCopy);
		

		//not the same object
		assertNotSame(toCheck,toCopy, "should be a deep copy");
		//desc and attendees correct values
		assertNotSame(toCheck.getArtist(), toCopy.getArtist(), "Artist has to be a deep copy of the given argument\n");
		assertEquals(toCheck.getYear(), toCopy.getYear(), "year should be the same");
		assertEquals(toCheck.getTitle(), toCopy.getTitle(), "title should be the same");
  }

  @Test
  public void ReleaseStringArtistint() {
	  Artist a= new Artist("my Artist");
    toTest= new TestRelease("my title", a, 2014);
    
    System.out.println(toTest);
    
	//desc and attendees correct values
	assertEquals(toTest.getArtist(), a, "Artist should be copied by reference\n");
	assertEquals(toTest.getYear(), 2014, "year should be the same");
	assertEquals(toTest.getTitle(), "my title", "title should be the same");
    
  }

  @Test
  public void getArtist() {
	  Artist myPerf= mock(Artist.class);
	  //reflect field

	  Field f= getPrivateField(toTest, "artist", false);
	  
	  try {
		f.set(toTest, myPerf);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  assertEquals(toTest.getArtist(), myPerf, "this should be the original artist");
  }

  @Test
  public void getTitle() {
	//reflect private field name
	  Field f= getPrivateField(toTest, "title", false);
	  try {
		f.set(toTest, "my Title");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  assertEquals(toTest.getTitle(),"my Title");
  }

  @Test
  public void getYear() {
		//reflect private field name
	  Field f= getPrivateField(toTest, "year", false);
	  try {
		f.set(toTest, 2011);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  assertEquals(toTest.getYear(),2011);
  }

  @Test
  public void setArtistNull() {
    
	  toTest.setArtist(null);
	  
	  Field f= getPrivateField(toTest, "artist", false);
	  try {
	    assertEquals(f.get(toTest),null);
	  } catch (Exception e) {} 
  }
  @Test
  public void setArtistDefault() {
	  
	  Artist in= new Artist();
	  toTest.setArtist(in);
	  
	  Field f= getPrivateField(toTest, "artist", false);
	  
	  try {
	    assertEquals(f.get(toTest),in);
	  } catch (Exception e) {} 
  }
  @Test
  public void setTitleAny() {
	  String in= "my title";
	  toTest.setTitle(in);
	  
	  Field f= getPrivateField(toTest, "title", false);
	  
	  try {
	    assertEquals(f.get(toTest),in);
	  } catch (Exception e) {} 
  }
  @Test
  public void setTitleNull() {
	  toTest.setTitle(null);
	  
	  Field f= getPrivateField(toTest, "title", false);
	  try {
	    assertEquals(f.get(toTest),null);
	  } catch (Exception e) {} 
  }
  
  @Test(dataProvider="year")
  public void setYear(int in, int out) {
	  toTest.setYear(in);
	  
	  Field f= getPrivateField(toTest, "year", false);
	  try {
	    assertEquals(f.get(toTest),out);
	  } catch (Exception e) {} 
  }
  
  
  @DataProvider(name = "year")
	private static Object[][] year() {
		return new Object[][] { { 0, 0}, { 2014, 2014}, {-25, 1900} };
	}
  

  @Test
  /**
   * all values to be null or 0
   */
  public void testtoStringNull() {
	  Field f= getPrivateField(toTest, "year", false);
	  
		try {
			f.set(toTest, 0);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(toTest);
		
		assertEquals(toTest.toString(), "unknown-unknown-unknown-0");
    
  }
  @Test
  /**
   * Artist.name= "my artist"
   * Release tite= "my title"
   * default year
   */
  public void testtoStringDefault() {
	  Field f= getPrivateField(toTest, "artist", false);
	  		try {
			f.set(toTest, new Artist("my artist"));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  		
	  	f= getPrivateField(toTest, "title", false);
	  		try {
			f.set(toTest, "my title");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(toTest);
		
		assertEquals(toTest.toString(), "my title-my artist-1900-0");
    
  }

  @Test
  /**
   * check if totalTime exists and is abstract
   */
  public void totalTime() {
	  try {
			Method m=Release.class.getMethod("totalTime");
			//System.out.println(m.getName()+" "+m.getReturnType()+ " "+m.getParameterTypes()[0] );
			if (!Modifier.isAbstract(m.getModifiers())) {
				fail("method totalTime() should be abstract");
			}
		
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			fail("method compare(Event e1, Event e2) not available");
		} 
  }
}
