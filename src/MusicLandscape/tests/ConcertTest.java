// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES03
//
// ***************************************************
package MusicLandscape.tests;

import MusicLandscape.Date;
import MusicLandscape.Venue;
import MusicLandscape.entities.Artist;
import MusicLandscape.entities.Concert;
import MusicLandscape.entities.Event;
import MusicLandscape.entities.Track;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * @author TeM
 * @version 234
 * @Stage ES03
 */
public class ConcertTest {
    Concert toTest;

    Track[] baseList;


    @BeforeMethod
    private void init() {
        toTest = new Concert();
        baseList = baseTestList();
    }

    private Track[] baseTestList() {
        Track[] basList = new Track[10];
        for (int i = 0; i < basList.length; i++) {
            basList[i] = new Track();
            basList[i].setDuration(i * 100);

        }
        return basList;
    }

    /**
     * tries to get a private field of the given object
     *
     * @param myObject  Object of which the field should be reached
     * @param fieldName String which defines the field to be retrieved
     * @return new Field object if found, fails if NoSuchFieldException
     */
    private <T> Field getPrivateField(T myObject, String fieldName, boolean isBase) {
        Field privateStringField;
        try {
            privateStringField = (isBase) ? myObject.getClass().getDeclaredField(fieldName) : myObject.getClass().getSuperclass().getDeclaredField(fieldName);
            privateStringField.setAccessible(true);
            return privateStringField;
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            fail("member \"" + fieldName + "\" not found");
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private boolean equals(Track a, Track b) {
        boolean isEqual = false;
        //check for self-comparison
        // if ( a == b ) return true;

        //check each field
        isEqual = (a.getTitle().equals(b.getTitle())) &&
                (a.getDuration() == b.getDuration()) &&
                (!a.getWriter().equals(b.getWriter())) && //writer is a deep copy
                (!a.getPerformer().equals(b.getPerformer())) && //performer is a deep copy
                (a.getYear() == b.getYear());
        return isEqual;
    }

    private void setbaseList() {
        try {
            Field f = getPrivateField(toTest, "setList", true);
            f.set(toTest, baseList);
            f = getPrivateField(toTest, "nextIdx", true);
            f.set(toTest, baseList.length);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void addTrack() {
        Track myT = new Track();
        assertEquals(toTest.addTrack(myT), true, "track should be added and method return true");
        toTest.addTrack(new Track());
        //check length is 2 (two tracks in list)
        assertEquals(toTest.getSetList().length, 2, "two tracks should have been added");

        assertEquals(toTest.addTrack(null), false, "null should not be added and method return false");

        //check length is 2 (two tracks in list, null not added)
        assertEquals(toTest.getSetList().length, 2, "two tracks should have been added");


        //check length is 2 (two tracks in list)
        assertEquals(toTest.getSetList().length, 2, "two tracks should have been added");

        //check Track is not copied - string is the same, hashcode too
        Track[] setl = new Track[1];
        try {
            setl = (Track[]) getPrivateField(toTest, "setList", true).get(toTest);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //check copy by reference
        assertEquals(setl[0], myT, "a Track should be added directly, not as copy!");


    }

    @Test
    public void duration() {
        assertEquals(toTest.duration(), 0, "initial duration should be 0");
        setbaseList();

        assertEquals(toTest.duration(), 4500, "duration for baselist should be 4500. Baselist contains 10 tracks with duration=idx*100");
    }

//  @Test
//  public void ensureCapacity() {
//    throw new RuntimeException("Test not implemented");
//  }

    @Test
    public void getSetList() {
        //add baselist hardcoded
        setbaseList();

        //check return is a new list
        try {
            Track[] origsetl = (Track[]) getPrivateField(toTest, "setList", true).get(toTest);
            Track[] gotset = toTest.getSetList();
            assertNotSame(gotset, origsetl);

            for (int i = 0; i < origsetl.length; i++) {
                //check reference is not the same
                assertNotSame(gotset[i], origsetl[i]);
                //check the if the values are the same
                assertTrue(equals(gotset[i], origsetl[i]));
            }
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void impact() {
        assertEquals(toTest.impact(), 0, "with initial values , there is no impact (0)");
        setbaseList();
        toTest.setAttendees(10);
        assertEquals(toTest.impact(), 30, "with baselist and 10 attendees, impact should be 30 -> duration is 4500, attendees 30. check calculation");
    }

    @Test
    public void nrTracks() {
        assertEquals(toTest.nrTracks(), 0);
        setbaseList();
        assertEquals(toTest.nrTracks(), 10);
    }

    @Test
    public void resetSetList() {
        toTest.resetSetList();

        try {
            Field f = getPrivateField(toTest, "setList", true);
            Track[] toTestset = (Track[]) f.get(toTest);
            for (Track t : toTestset) {
                assertEquals(t, null, "value should be resetted to null");
            }
            assertEquals(getPrivateField(toTest, "nextIdx", true).get(toTest), 0);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setbaseList();
        toTest.resetSetList();
        try {
            Field f = getPrivateField(toTest, "setList", true);
            Track[] toTestset = (Track[]) f.get(toTest);
            for (Track t : toTestset) {
                assertEquals(t, null, "values should be resetted to null");
            }
            assertEquals(getPrivateField(toTest, "nextIdx", true).get(toTest), 0);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void setSetListBasics() {

        toTest.setSetList(baseList);

        //reflect private field name
        Field f = getPrivateField(toTest, "setList", true);
        try {
            Track[] toTestset = (Track[]) f.get(toTest);
            //check that not same reference
            assertNotSame(toTestset, baseList, "the lists should not be the same");


            //check same order of baseList and checkList
            //remove null values from baselist
            ArrayList<Track> nnBase = new ArrayList<Track>();
            for (Track t : baseList) {
                if (t != null) {
                    nnBase.add(t);
                }
            }

            Track[] baseClean = new Track[nnBase.size()];
            baseClean = nnBase.toArray(baseClean);

            for (int i = 0; i < baseClean.length; i++) {
                //check reference is not the same
                assertNotSame(toTestset[i], baseClean[i]);
                //check the if the values are the same
                assertTrue(equals(toTestset[i], baseClean[i]));
            }


        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void setSetListWithNull() {
        baseList[3] = null;
        baseList[4] = null;
        toTest.setSetList(baseList);

        //reflect private field name
        Field f = getPrivateField(toTest, "setList", true);
        try {
            Track[] toTestset = (Track[]) f.get(toTest);
            //check that not same reference
            assertNotSame(toTestset, baseList, "the lists should not be the same");


            //check same order of baseList and checkList
            //remove null values from baselist
            ArrayList<Track> nnBase = new ArrayList<Track>();
            for (Track t : baseList) {
                if (t != null) {
                    nnBase.add(t);
                }
            }

            Track[] baseClean = new Track[nnBase.size()];
            baseClean = nnBase.toArray(baseClean);

            for (int i = 0; i < baseClean.length; i++) {
                //check reference is not the same
                assertNotSame(toTestset[i], baseClean[i]);
                //check the if the values are the same
                assertTrue(equals(toTestset[i], baseClean[i]));
            }


        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    @Test
    public void testtoString() {
        String out = "unknown @ unknown on null\n\n(0 attending (0))";
        assertEquals(new Event().toString(), out);
    }

    @Test
    public void testtoString2() {

        Venue myV = mock(Venue.class);
        when(myV.getName()).thenReturn("my Venue");

        toTest.setVenue(myV);
        toTest.setDescription("my fancy description");
        toTest.setAttendees(50);

        setbaseList();

        //kein mock weil von uns implementiert und nicht sichergestellt, welche methode von dort aufgerufen wird
        Date myDate = new Date();
        myDate.setJulian(2457091);
        toTest.setDate(myDate);

        Artist a = mock(Artist.class);
        when(a.toString()).thenReturn("cool artist");
        when(a.getName()).thenReturn("cool artist");

        toTest.setArtist(a);
        String out = "cool artist @ my Venue on 09.03.2015\nmy fancy description\n(50 attending (150))\n10 tracks played, total duration 01:15.";
        assertEquals(toTest.toString(), out);
    }


}


