package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int togetRandomNumber() {
		
		Random ran = new Random();
		int rancount = ran.nextInt(1000);
		return rancount;
		
	}
	
	public String togetCurrentDate()
	{
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = sim.format(date);
		return currentDate;
		
	}
	
	public String ToGetRequiredDate(int days) {
		
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String datereq = sim.format(cal.getTime());
		return datereq;
	}

}
