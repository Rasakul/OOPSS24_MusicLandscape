// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES04
//
// ***************************************************
package MusicLandscape.tests;
import static org.testng.Assert.fail;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
public class MyEventComparatorTest {

  @Test
  public void compare() {
	  try {
			Method m=MyEventComparator.class.getMethod("compare", Event.class, Event.class);
			//System.out.println(m.getName()+" "+m.getReturnType()+ " "+m.getParameterTypes()[0] );
			if (!Modifier.isAbstract(m.getModifiers())) {
				fail("method compare(Event e1, Event e2) should be abstract");
			}
		
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			fail("method compare(Event e1, Event e2) not available");
		} 
  }
}
