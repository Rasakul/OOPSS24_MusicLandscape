// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES04
//
// ***************************************************
package MusicLandscape.tests;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.lang.reflect.Field;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import MusicLandscape.entities.*;


/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES04
 *
 */
public class MusicVideoTest {
	MusicVideo toTest;
	
	@BeforeMethod
	private void init(){
		toTest= new MusicVideo();
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
	public void checkInit(){
		assertEquals(((Object)toTest instanceof Release), true, "Class not inherited as expected\n");
		
		//reflect private field name
		  Field f= getPrivateField(toTest, "track",true);
		  try {
		    assertEquals(f.get(toTest),null, "initial value of track should be null");
		  } catch (Exception e) {} 
		  
	}

  @Test
  public void getTrack() {
	  Track myTrack= mock(Track.class);
	  //reflect field

	  Field f= getPrivateField(toTest, "track", true);
	  
	  try {
		f.set(toTest, myTrack);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  assertEquals(toTest.getTrack(), myTrack);
  }

  @Test
  public void setTrack() {
	  Track in= new Track();
	  Field f= getPrivateField(toTest, "track", true);
	  toTest.setTrack(in);
	  try {
	    assertEquals(f.get(toTest),in, "Track should be copied by reference");
	  } catch (Exception e) {} 
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
		
		assertEquals(toTest.toString(), "unknown-unknown-unknown-0-(Video)");
    
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
	  		
	  	f= getPrivateField(toTest, "track", true);
	  	try {
			f.set(toTest, new Track());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(toTest.toString(), "my title-my artist-1900-0-(Video)");
    
  }

  @Test
  /**
   * tested with track duration 236
   */
  public void totalTime() {
    Track myTrack= new Track();
    myTrack.setDuration(236);
    
    Field f= getPrivateField(toTest, "track", true);
  	try {
		f.set(toTest, myTrack);
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    assertEquals(toTest.totalTime(), 236, "total time should be duration of track");
  }
}
