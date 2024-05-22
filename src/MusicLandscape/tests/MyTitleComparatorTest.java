// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES04
//
// ***************************************************
package MusicLandscape.tests;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MusicLandscape.entities.*;
import MusicLandscape.util.comparators.*;


/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES04
 *
 */
public class MyTitleComparatorTest {
	 
	
	  @Test(dataProvider="title")
	  public void compare(String t1, String t2, int res) {

		  MyTitleComparator com= new MyTitleComparator();
		  
		  int result= com.compare(new Track(t1), new Track(t2));
		  
		  if(res>0){
			  assertTrue(result>0, "first should be bigger than second");  
		  }else if(res==0){
			  assertTrue(result==0, "both should be equal");
		  }else if(res<0){
			  assertTrue(result<0, "first should be smaller than second");
		  }
	  }
	  
	  @DataProvider(name = "title")
		private static Object[][] title() {
			return new Object[][] {
					{ "same", "same", 0}, 
					{"same", "other", 4}, 
					{"other", "same", -4} 
					};
		}
}
