// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES03
//
// ***************************************************
package MusicLandscape.tests;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.fail;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MusicLandscape.Date;
import MusicLandscape.Venue;
import MusicLandscape.entities.*;


/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES03
 *
 */
public class TVShowTest {
	TVShow toTest;
	
	@BeforeMethod
	private void init(){
		toTest=new TVShow();
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
		
		
	
		/**
		 * checks all initial values 
		 * 	-> 0 for numbers, 
		 * 	null for String, 
		 * 	any non-null value for reference members
		 */
  @Test
  public void TVShow() {
		String message="";
		List<Object[]> myList= new ArrayList<Object[]>();
		
		myList.add(new Object[]{"s", "name",null});
		myList.add(new Object[]{"i", "viewers",0});
		
		
		for (Object[] li : myList){
			message=String.format("initial value for %s should be %s\n",li[1], li[2]);
			try {
				switch (li[0].toString()) {
				case "i":case "s":
					assertEquals(getPrivateField(toTest, li[1].toString(), true).get(toTest),li[2],message);
					break;
				case "nn":
					assertNotNull(getPrivateField(toTest, li[1].toString(), true).get(toTest),message);
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
		
		assertEquals(((Object)toTest instanceof Event), true, "Class not inherited as expected\n");
  }

  @Test
  public void TVShowTVShow() {
	 TVShow toCopy= new TVShow();

		toCopy.setAttendees(20);
		toCopy.setDescription("my description");
		toCopy.setName("The Tonight Show");
		toCopy.setViewers(600);
		
		TVShow toCheck=new TVShow(toCopy);
		

		//not the same object
		assertNotSame(toCheck,toCopy);
		//desc and attendees correct values
		assertEquals(toCheck.getDescription(), toCopy.getDescription(), "description has to be a copy\n");
		assertEquals(toCheck.getAttendees(), toCopy.getAttendees(), "Attendees has to be a copy\n");
		assertNotSame(toCheck.getArtist(), toCopy.getArtist(), "Artist has to be a deep copy of the given argument\n");
		assertNotSame(toCheck.getVenue(), toCopy.getVenue(), "Venue has to be a deep copy of the given argument\n");
		assertNotSame(toCheck.getDate(), toCopy.getDate(), "Date has to be a deep copy of the given argument\n");
		
		assertEquals(toCheck.getName(), toCopy.getName());
		assertEquals(toCheck.getViewers(), toCopy.getViewers());
	 
  }

  @Test
  public void TVShowEvent() {
	  	Event toCopy=new Event();
		toCopy.setAttendees(20);
		toCopy.setDescription("my description");
		
		TVShow toCheck=new TVShow(toCopy);
		

		//not the same object
		assertNotSame(toCheck,toCopy);
		//desc and attendees correct values
		assertEquals(toCheck.getDescription(), toCopy.getDescription(), "description has to be a copy\n");
		assertEquals(toCheck.getAttendees(), toCopy.getAttendees(), "Attendees has to be a copy\n");
		assertNotSame(toCheck.getArtist(), toCopy.getArtist(), "Artist has to be a deep copy of the given argument\n");
		assertNotSame(toCheck.getVenue(), toCopy.getVenue(), "Venue has to be a deep copy of the given argument\n");
		assertNotSame(toCheck.getDate(), toCopy.getDate(), "Date has to be a deep copy of the given argument\n");
		
		assertEquals(toCheck.getName(), null);
		assertEquals(toCheck.getViewers(), 0);
  }

  @Test(dataProvider="name")
  public void getName(String in, String out) {
	//reflect private field name
	  Field f= getPrivateField(toTest, "name",true);
	  try {
		f.set(toTest, in);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  assertEquals(toTest.getName(),out);
  }

  @Test(dataProvider="name")
  public void setName(String in, String out) {
	  Field f= getPrivateField(toTest, "name",true);
	  toTest.setName(in);
	  try {
	    assertEquals(f.get(toTest),in);
	  } catch (Exception e) {} 
  }
  @DataProvider(name = "name")
  private static Object[][] name() {
      return new Object[][] { { null, null}, {"Johnny Cash", "Johnny Cash"}};
  }
  @Test(dataProvider="viewers")
  public void getViewers(int in, int out) {
	//reflect private field name
	  Field f= getPrivateField(toTest, "viewers",true);
	  try {
		f.set(toTest, in);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  assertEquals(toTest.getViewers(),in,"value should be "+in);
  }
  @Test(dataProvider="viewers")
  public void setViewers(int in, int out) {
	  Field f= getPrivateField(toTest, "viewers",true);
	  toTest.setViewers(in);
	  try {
	    assertEquals(f.get(toTest),out, "value should be "+out);
	  } catch (Exception e) {} 
  }
  
  @DataProvider(name = "viewers")
  private static Object[][] viewers() {
      return new Object[][] { { 0, 0}, {-1, 0},{200, 200} };
  }

  @Test(dataProvider="impact")
  public void impact(int viewers, int attendees, int impact) {
	//reflect private field name
	  Field f= getPrivateField(toTest, "viewers", true);
	  try {
		f.set(toTest, viewers);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//reflect private field attendees from super class
	  	
	  f= getPrivateField(toTest, "attendees", false);
	  try {
		f.set(toTest, attendees);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(toTest.impact(), impact);
	  
  }
  @DataProvider(name = "impact")
  private static Object[][] impacts() {
      return new Object[][] { { 0,0, 0}, {0, 10, 20},{100,0, 200}, {10,10, 40} };
  }

  @Test
  public void testtoString() {
	  String out="unknown @ unknown on null\n\n(0 attending (0))";
		assertEquals(new Event().toString(), out);
  }
  @Test
  public void testtoString2() {
	  	TVShow toTest=new TVShow();
		
		Venue myV=mock(Venue.class);
		when(myV.getName()).thenReturn("my Venue");
		
		toTest.setVenue(myV);
		toTest.setDescription("my fancy description");
		toTest.setAttendees(50);
		toTest.setViewers(20);
		
		toTest.setName("Late night show");
		
		//kein mock weil von uns implementiert und nicht sichergestellt, welche methode von dort aufgerufen wird
		Date myDate=new Date();
		myDate.setJulian(2457091);
		toTest.setDate(myDate);
		
		Artist a= mock(Artist.class);
		when(a.toString()).thenReturn("cool artist");
		when(a.getName()).thenReturn("cool artist");
		
		toTest.setArtist(a);
		String out="cool artist @ Late night show on 09.03.2015\nmy fancy description\n(70 attending (140))";
		assertEquals(toTest.toString(), out);
  }
  
  @DataProvider(name = "toString")
  private static Object[][] string() {
	  //artistname, show name, date, description, viwer, attendees, impact
      return new Object[][] { { null,null, null, null, 0, 0, 0, "unknown @ unkown on null\n\n(0 attending (0))"},
    		  {0, 10, 20},
    		  {100,0, 200}, 
    		  {10,10, 40} };
  }
  
}
