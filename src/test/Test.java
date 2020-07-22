package test;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Test {

	public static String getCurrentDate() {
		//static 붙이면 바로 사용가능 안붙이면 New객체화 해야함.
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);
		return time1;
	}

	}


