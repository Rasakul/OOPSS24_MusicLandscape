// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES03
//
// ***************************************************
package MusicLandscape.tests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.fail;


import static org.mockito.Mockito.*;


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
public class EventTest {
	Event toTest;

	

	
	/**
	 * test toString method
	 */
	@Test
	public void testtoString(){
		String out="unknown @ unknown on null\n\n(0 attending (0))";
		assertEquals(new Event().toString(), out);

	}
	/**
	 * test toString method
	 */
	@Test
	public void testtoString2(){

		Event toTest=new Event();
		
		Venue myV=mock(Venue.class);
		when(myV.getName()).thenReturn("my Venue");
		
		toTest.setVenue(myV);
		toTest.setDescription("my fancy description");
		toTest.setAttendees(250);
		
		//kein mock weil von uns implementiert und nicht sichergestellt, welche methode von dort aufgerufen wird
		Date myDate=new Date();
		myDate.setJulian(2457091);
		toTest.setDate(myDate);
		
		Artist a= mock(Artist.class);
		when(a.toString()).thenReturn("cool artist");
		when(a.getName()).thenReturn("cool artist");
		
		toTest.setArtist(a);
		String out="cool artist @ my Venue on 09.03.2015\nmy fancy description\n(250 attending (500))";
		assertEquals(toTest.toString(), out);
		
	}
	@Test
	public void EventEvent(){
		Event toCopy=new Event();
		toCopy.setAttendees(20);
		toCopy.setDescription("my description");
		
		Event toCheck=new Event(toCopy);
		

		//not the same object
		assertNotSame(toCheck,toCopy);
		//desc and attendees correct values
		assertEquals(toCheck.getDescription(), toCopy.getDescription(), "description has to be a copy\n");
		assertEquals(toCheck.getAttendees(), toCopy.getAttendees(), "Attendees has to be a copy\n");
		assertNotSame(toCheck.getArtist(), toCopy.getArtist(), "Artist has to be a deep copy of the given argument\n");
		assertNotSame(toCheck.getVenue(), toCopy.getVenue(), "Venue has to be a deep copy of the given argument\n");
		assertNotSame(toCheck.getDate(), toCopy.getDate(), "Date has to be a deep copy of the given argument\n");
		

	}
	
	

	@Test
	public void impact() {
		Event toTest=new Event();
		assertEquals(toTest.impact(), 0);
		toTest.setAttendees(20);
		assertEquals(toTest.impact(), 40);
		
	}
	
}
