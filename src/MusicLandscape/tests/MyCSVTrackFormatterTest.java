// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES04
//
// ***************************************************
package MusicLandscape.tests;
import org.testng.annotations.Test;

import MusicLandscape.entities.*;
import MusicLandscape.util.formatters.*;

import static org.testng.Assert.*;


/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES04
 *
 */
public class MyCSVTrackFormatterTest {

  @Test
  public void formatDefault() {
	  MyCSVTrackFormatter form= new MyCSVTrackFormatter();
	  String result= form.format(new Track());
	  System.out.println(result);
	  assertEquals(result, "unknown title,unknown,unknown,1900,0;", "Strings should be equal");
  }
  
  @Test
  public void formatAny() {
	  //"title","performer","writer","year","duration"
	  Track myTrack= new Track("Ain't No Sunshine");
	  myTrack.setPerformer(new Artist("me first and the gimme gimmes"));
	  myTrack.setWriter(new Artist("ghostwriter"));
	  myTrack.setYear(2015);
	  myTrack.setDuration(360);
	  
	  MyCSVTrackFormatter form= new MyCSVTrackFormatter();
	  String result= form.format(myTrack);
	  System.out.println(result);
	  assertEquals(result, "Ain't No Sunshine,me first and the gimme gimmes,ghostwriter,2015,360;", "Strings should be equal");

  }
  
  
}
