// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES04
//
// ***************************************************
package MusicLandscape.tests;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import MusicLandscape.Date;
import MusicLandscape.entities.*;
import MusicLandscape.util.comparators.*;


/**
 * 
 * 
 * @author TeM
 * @version 234
 * @Stage ES04
 *
 */
public class MyEventDateComparatorTest {

  @Test
  public void compareEventNull() {
	  MyEventDateComparator comp= new MyEventDateComparator();

	  Event left= null;
	  Event right= null;
	  
	    
	  int res=comp.compare(left, right);
	  
	  assertTrue(res == 0, "both should be equal");
  }
  
  @Test
  public void compareNullEventLeft() {
	  MyEventDateComparator comp= new MyEventDateComparator();

	  Event left= null;
	  Event right= mock(Event.class);
	    
	  int res=comp.compare(left, right);
	  assertTrue(res <0, "first should be smaller than second");
  }
  @Test
  public void compareNullEventRight() {
	  MyEventDateComparator comp= new MyEventDateComparator();

	  Event right= null;
	  Event left= mock(Event.class);
	    
	  int res=comp.compare(left, right);
	  assertTrue(res >0, "first should be bigger than second");
  }
  
  
  @Test
  public void compareDateNull() {
	  MyEventDateComparator comp= new MyEventDateComparator();

	  Event left= mock(Event.class);
	  Event right= mock(Event.class);
	  when(left.getDate()).thenReturn(null);
	  when(right.getDate()).thenReturn(null);
	    
	  int res=comp.compare(left, right);
	  
	  assertTrue(res == 0, "both are equal");
  }
  
  @Test
  public void compareNullDateLeft() {
	  MyEventDateComparator comp= new MyEventDateComparator();

	  Event left= mock(Event.class);
	  Event right= mock(Event.class);
	  when(left.getDate()).thenReturn(null);
	  when(right.getDate()).thenReturn(new Date());
	    
	  int res=comp.compare(left, right);
	  assertTrue(res <0, "first should be smaller than second");
  }
  @Test
  public void compareNullDateRight() {
	  MyEventDateComparator comp= new MyEventDateComparator();

	  Event left= mock(Event.class);
	  Event right= mock(Event.class);
	  when(left.getDate()).thenReturn(new Date());
	  when(right.getDate()).thenReturn(null);
	    
	    
	  int res=comp.compare(left, right);
	  assertTrue(res >0, "first should be bigger than second");
  }
  
  @Test
  public void compareDate() {
	  MyEventDateComparator comp= new MyEventDateComparator();

	  Event left= mock(Event.class);
	  Event right= mock(Event.class);
	  when(left.getDate()).thenReturn(new Date());
	  when(right.getDate()).thenReturn(new Date());
	    
	    
	  int res=comp.compare(left, right);
	  assertTrue(res == 0, "both should be equal");
  }
  
}
