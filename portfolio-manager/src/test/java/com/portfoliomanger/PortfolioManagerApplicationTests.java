package com.portfoliomanger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PortfolioManagerApplicationTests {

	@Test
	void contextLoads() throws ParseException {
		//TimeZone tz = TimeZone.getTimeZone("GMT+5:30");
		//TimeZone tz = TimeZone.getTimeZone("IST");
		TimeZone tz = TimeZone.getTimeZone("Asia/Kolkata");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());

		System.out.println(nowAsISO);

		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
		//nowAsISO = "2013-05-31T00:00:00Z";
		Date finalResult = df1.parse(nowAsISO);
		System.out.println(finalResult);
	}

}
