// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES03
//
// ***************************************************
package MusicLandscape;

import java.util.Scanner;

/**
 * 
 * @author TeM
 * @author JS
 * @version 234
 * @Stage ES03
 *
 */
public class Date implements  java.lang.Comparable<Date>


{

	private int y = 1970, m = 01, d = 01;

	/**
	 * Copy constructor
	 * 
	 * @param date the date to copy
	 */
	public Date(Date date) {
		if(date==null)return;
		y = date.y;
		m = date.m;
		d = date.d;
	}

	/**
	 * 
	 * 
	 */
	public Date() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return the year of this date
	 */
	public int getYear() {
		return y;
	}

	/**
	 * 
	 * @return the day of this date
	 */
	public int getDay() {
		return d;
	}

	/**
	 * 
	 * @return the month of this date
	 */
	public int getMonth() {
		return m;
	}

	/**
	 * 
	 * @return true if this date is in a leap year, false otherwise
	 */
	public boolean isLeap() {
		return ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0);
	}

	/**
	 * Calculates the day of the week this date.<br>
	 * 
	 * 0...SUN
	 * 6...SAT
	 * 
	 * @return the weekday of this date
	 */
	public int weekday() {
		int yy = y, cc, dd = d, mm = m;

		if (mm < 3) {
			yy--;
			mm += 10;
		} else {
			mm -= 2;
		}
		int mmm = mm;

		cc = yy / 100;
		yy = yy % 100;

		int A = dd + (int) (2.6 * mmm - 0.2) + yy + (yy / 4) + (cc / 4) - 2
				* cc;
		int W = A % 7;

		if (W < 0)
			W += 7;
		return W;

	}

	/**
	 * 
	 * @return a formatted String representing this date
	 */
	public String dateString() {
		return String.format("%s, %s %d%s %d", dayName(weekday()),
				monthName(m), d, numberEnding(), y);
	}

	/**
	 * 
	 * @return the number ending of a the day of this date
	 */
	private String numberEnding() {
		if (d % 10 == 1 && d != 11)
			return "st";
		if (d % 10 == 2 && d != 12)
			return "nd";
		if (d % 10 == 3 && d != 13)
			return "rd";
		return "th";
	}

	/**
	 * 
	 * @param month the month for which the English name shall be calculated
	 * @return the English name of a month 
	 */
	private String monthName(int month) {
		switch (month) {
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		default:
			return "invalid month (" + month + ")";
		}
	}

	/**
	 * 
	 * @param w the number of the day (as explained in weekday())
	 * @return the English name of a day of the week
	 */
	private String dayName(int w) {
		switch (w) {
		case 0:
			return "Sunday";
		case 1:
			return "Monday";
		case 2:
			return "Tuesday";
		case 3:
			return "Wednesday";
		case 4:
			return "Thursday";
		case 5:
			return "Friday";
		case 6:
			return "Saturday";
		default:
			return "invalid day (" + w + ")";
		}
	}

	/**
	 * 
	 * @param n the number of days to add to this date
	 */
	public void addDay(int n) {
		setJulian(julianDayNumber() + n);

	}

	/**
	 * 
	 * @param i the number of months to add to this date
	 */
	public void addMonth(int i) {
		setJulian(julianDayNumber(y, m + i, d));

	}

	/**
	 * 
	 * @param i the number of year to add to this date
	 */
	public void addYear(int i) {
		setJulian(julianDayNumber(y + i, m, d));

	}

	/**
	 * returns the Julian Day Number for specified Date given as year, month and
	 * day (in Gregorian calendar) (for formulas see
	 * http://en.wikipedia.org/wiki/Julian_day)
	 * 
	 * @param y
	 *            year
	 * @param m
	 *            month
	 * @param d
	 *            day
	 * @return the Julian Day Number
	 */
	public static int julianDayNumber(int y, int m, int d) {
		y += m / 12;
		m %= 12;
		int a = (14 - m) / 12;
		int yy = (y) + 4800 - a;
		int mm = m + 12 * a - 3;
		int jdn = d + (153 * mm + 2) / 5 + 365 * yy + yy / 4 - yy / 100 + yy
				/ 400 - 32045;
		return jdn;
	}

	/**
	 *  @return  the Julian Day Number for this Date (for formulas see
	 * http://en.wikipedia.org/wiki/Julian_day)
	 * 
	 */
	public int julianDayNumber() {
		return julianDayNumber(y, m, d);
	}

	/**
	 * converts Lilian Day Number to Julian Day Number
	 * 
	 * @param lil
	 *            Lilian day number
	 * @return the Julian Day Number of the day specified by its Lilian Day
	 *         Number
	 */
	public static int lil2jul(int lil) {
		return lil + 2299160;
	}

	/**
	 * converts Julian Day Number to Lilian Day Number
	 * 
	 * @param jul
	 *            Julian day number
	 * @return the Lilian Day Number of the day specified by its Julian Day
	 *         Number
	 */
	public static int jul2lil(int jul) {
		return jul - 2299160;
	}

	/**
	 * @return  returns the Lilian Date Number for this Date for details see
	 * http://en.wikipedia.org/wiki/Lilian_date
	 * 

	 */
	public int lilianDayNumber() {
		return jul2lil(julianDayNumber());
	}

	/**
	 * 
	 * returns the Lilian Date Number for the Date given as year, month, and day
	 * (in Grgorian calendar) (for details see
	 * http://en.wikipedia.org/wiki/Lilian_date)
	 * 
	 * @param y
	 *            year
	 * @param m
	 *            month
	 * @param d
	 *            day
	 * @return the Lilian Day Number of the specified date
	 */
	public static int lilianDayNumber(int y, int m, int d) {
		return jul2lil(julianDayNumber(y, m, d));
	}

	/**
	 * sets the current Date to the Date given as Julian Day Number
	 * 
	 * @param jul
	 *            Julian Day Number
	 */
	public void setJulian(int jul) {
		jul = Math.max(jul, julianDayNumber(1582, 10, 15));
		jul = Math.min(jul, julianDayNumber(2199, 12, 31));

		int J = (int) Math.floor(jul + 0.5);
		int j = J + 32044;
		int g = j / 146097;
		int dg = j % 146097;
		int c = ((dg / 36524 + 1) * 3) / 4;
		int dc = dg - c * 36524;
		int b = dc / 1461;
		int db = dc % 1461;
		int a = ((db / 365 + 1) * 3) / 4;
		int da = db - a * 365;
		int y = g * 400 + c * 100 + b * 4 + a;
		int m = (da * 5 + 308) / 153 - 2;
		int d = da - ((m + 4) * 153) / 5 + 122;
		int Y = y - 4800 + (m + 2) / 12;
		int M = (m + 2) % 12 + 1;
		int D = d + 1;
		this.d = D;
		this.m = M;
		this.y = Y;

		/*
		 * int y=4716, j=1401, m=2, n=12, r=4, p=1461, v=3, u=5, s=153, w=2,
		 * B=274277, C=-38; int J=jul; int f = J + j +(((4*J+B)/146097)*3)/4+C;
		 * int e = r*f+v; int g = (e%p)/r; int h=u*g+w; int D = (h%s)/u+1; int
		 * M=((h/s+m)%n)+1; int Y = e/p-y+(n+m-M)/n; this.d=D; this.m=M;
		 * this.y=Y;
		 */

	}

	/**
	 * 
	 * @return a formatted String representing this date
	 */
	public String numericString() {
		return String.format("%02d.%02d.%04d", d, m, y);
	}

	/**
	 * 
	 * Compares this date to another date.<br>
	 * 
	 * @param d the date to compare this date to
	 * @return a measure of the distance between this date and another date
	 */
	@Override
	public int compareTo(Date d) {
		return lilianDayNumber() - d.lilianDayNumber();
	}

	/**
	 * 
	 * @return true if this date was modified during the operation, false otherwise
	 */
	
	public boolean scan() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter date (y m d)");
		int y = Integer.parseInt(sc.nextLine()), m = Integer.parseInt(sc.nextLine()), d = Integer.parseInt(sc.nextLine());
		if (julianDayNumber(y, m, d) > julianDayNumber(2199, 12, 31))
			return false;
		if (julianDayNumber(y, m, d) < julianDayNumber(1582, 10, 15))
			return false;

		setJulian(julianDayNumber(y, m, d));
		sc.close();
		return true;
	}

	@Override
	public String toString() {
		return String.format("%02d.%02d.%04d", d, m, y);
	}

}
