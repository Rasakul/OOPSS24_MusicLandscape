// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES04
//
// ***************************************************
package MusicLandscape.tests;

import static org.testng.Assert.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
public class AlbumTest {
	Album toTest;
	Track t1=new Track();
	Track t2=new Track();
	
	@BeforeMethod
	private void init(){
		toTest= new Album();
		t1.setTitle("my track");
		t1.setDuration(50);
		t2.setDuration(160);
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
  public void Album() {

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
				
				//check tracklisthead available and null
				
				try {
					assertEquals(
							getPrivateField(toTest, "trackListHead", true).get(
									toTest), null, "tracklistHead should be null initially");
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  }

  @Test
  public void AlbumAlbum() {
	  //tracklist selbst nicht getestet
	  	Album toCopy= new Album();

		toCopy.setArtist(new Artist("Metallica"));
		toCopy.setTitle("my release");
		toCopy.setYear(1985);
		
		
		
		Release toCheck=new Album(toCopy);
		

		//not the same object
		assertNotSame(toCheck,toCopy, "should be a deep copy");
		//desc and attendees correct values
		assertNotSame(toCheck.getArtist(), toCopy.getArtist(), "Artist has to be a deep copy of the given argument\n");
		assertEquals(toCheck.getYear(), toCopy.getYear(), "year should be the same");
		assertEquals(toCheck.getTitle(), toCopy.getTitle(), "title should be the same");
		
		
  }

  @Test
  public void AlbumStringArtistint() {
	//tracklist selbst nicht getestet
	  	Artist a= new Artist("my Artist");
	    toTest= new Album("my title", a, 2014);
	    
	    System.out.println(toTest);
	    
		//desc and attendees correct values
		assertEquals(toTest.getArtist(), a, "Artist should be copied by reference\n");
		assertEquals(toTest.getYear(), 2014, "year should be the same");
		assertEquals(toTest.getTitle(), "my title", "title should be the same");
		
		//check tracklisthead available and null
		
		try {
			assertEquals(
					getPrivateField(toTest, "trackListHead", true).get(
							toTest), null, "tracklistHead should be null initially");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }

  @Test(description="checks return value of addTrack as well as the changed nr. of Tracks")
  /**
   * tests if two default tracks have been added and if nrTracks works as expected
   * implementation behind is NOT checked
   */
  public void addTrack() {

	  //check return of method
	  assertTrue(toTest.addTrack(t1), "add track should return true");
	  
	  //check number of Tracks
	  assertEquals(toTest.nrTracks(), 1, "track should have been added and counted");
	  
	  //add another track
	  //check return of method
	  assertTrue(toTest.addTrack(t2), "add track should return true for a default Track");
	  
	  
	  //check number of Tracks
	  assertEquals(toTest.nrTracks(), 2, "track should have been added and counted");
	  
  }
  
  @Test
  public void addTrackNull() {
	  Track toAdd=null;
	  //check return of method
	  assertFalse(toTest.addTrack(toAdd), "add null should return false, as the list should not allow null values ");
  }

  @Test(dependsOnMethods = { "addTrack" }, description="test the return value of getTracks, Prerequisite: addTrack is successfull")
  /**
   * prerequisite: addTrack is working
   */
  public void getTracks() {
	  //checks length of return 
	  assertEquals(toTest.getTracks().length, 0);
	  addTrack();
	  assertEquals(toTest.getTracks().length, 2);
	  
	  //checks the values of the return array
	  //check first element
	  assertEquals(toTest.getTracks()[0], t1, "track should be copied by reference");
	  //check second element
	  assertEquals(toTest.getTracks()[1], t2, "track should be copied by reference");
  }

  @Test
  public void nrTracks() {
    //not implemented as it's tested within addTrack
  }

  @Test(dependsOnMethods = { "addTrack" }, description="test the return value and length of list after call of removeTrack, Prerequisite: addTrack is successfull")
  public void removeTrack() {
	  //try remove track from empty list
	 assertEquals( toTest.removeTrack(4), null, "empty list should return null when trying to remove a track at a given index");
	 //add the 2 tracks
    addTrack();
    //try to remove track at unavailable position
    assertEquals( toTest.removeTrack(4), null, "list should return track at index 1 when trying to remove this track");
    //check number of Tracks
    assertEquals(toTest.nrTracks(), 2, "null should not have been removed and counted");
    
    //try remove track idx 1
    assertEquals( toTest.removeTrack(1), t2, "list should return track at index 1 when trying to remove this track");

	  //check number of Tracks
	  assertEquals(toTest.nrTracks(), 1, "track should have been removed and counted");
	  
	  //try remove track idx 0
	  assertEquals( toTest.removeTrack(0), t1, "list should return track at index 1 when trying to remove this track");
  

	  //check number of Tracks
	  assertEquals(toTest.nrTracks(), 0, "track should have been removed and counted");
  }

  @Test
  public void testtoStringDefault() {
    assertEquals(toTest.toString(), "unknown-unknown-1900-0\n[]");
  }
  
  @Test(dependsOnMethods = { "addTrack" })
  public void testtoStringTwoTracks() {
	  addTrack();
    assertEquals(toTest.toString(), "unknown-unknown-1900-210\n[[my track][unknown title]]");
  }
  
  @Test(dependsOnMethods = { "addTrack" }, description="test totalTime, Prerequisite: addTrack is successfull, track durations: 50, 160")
  public void totalTime() {
    addTrack();
    assertEquals(toTest.totalTime(),210); 
    }
}
