// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES03
//
// ***************************************************
package MusicLandscape.tests;
import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MusicLandscape.entities.*;

/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES03
 *
 */
public class TrackTest {
	Track toTest;
	
	@BeforeMethod
	private void init(){
		toTest=new Track();
	}
	
  /**
   * tries to get a private field of the given object
   * @param myObject Object of which the field should be reached
   * @param fieldName String which defines the field to be retrieved
   * @return new Field object if found, fails if NoSuchFieldException
   */
	private <T> Field getPrivateField(T myObject, String fieldName){
		Field privateStringField;
		try {
			privateStringField = myObject.getClass().getDeclaredField(fieldName);
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
	
  
  @DataProvider(name = "getString")
  private static Object[][] string() {
      return new Object[][] { 
    		  //title, writer, name, performer, name, dur, ret
    		  {null, null, null, null, null, 0, "   unknown by    unknown performed by    unknown (00:00)"},
    		  {"song", mock(Artist.class), null, mock(Artist.class), null, 123, "      song by    unknown performed by    unknown (02:03)"},
    		  {"short song", mock(Artist.class), "writer", mock(Artist.class), "performer", 1230, "short song by     writer performed by  performer (20:30)"},
    		  {"very long songtitle", mock(Artist.class), "long writer", mock(Artist.class), "long performer", 1230, "very long  by long write performed by long perfo (20:30)"}
   		  };
  }
  
  
  
  
  
  
  
  
  /**************** ES 03 ***********************/
  @Test(dataProvider="getString")
  public void testtoString(String title, Artist w, String wName, Artist p, String pName, int d, String out) {
		List<Object[]> myList= new ArrayList<Object[]>();
		
		myList.add(new Object[]{"title",title});
		myList.add(new Object[]{"duration",d});
		myList.add(new Object[]{"writer",w});
		myList.add(new Object[]{"performer",p});
		
		if (w!=null)
			  when(w.getName()).thenReturn(wName);
		if (p!=null)
			  when(p.getName()).thenReturn(pName);
		
		for (Object[] li : myList){
			try {
				getPrivateField(toTest, li[0].toString()).set(toTest,li[1]);
								
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		assertEquals(toTest.toString(), out);  
	  
  }

  
  
    
  
  

 
}
