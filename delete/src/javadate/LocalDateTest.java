package javadate;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

public class LocalDateTest {

	public static void main(String[] args) {
		LocalDate localdate1 = LocalDate.from(LocalDate.now());
		System.out.println(localdate1);
		
		LocalDate localdate2 = LocalDate.now();
		System.out.println(localdate2);
		
		LocalDate localdate3 = LocalDate.now(Clock.systemDefaultZone());
		System.out.println(localdate3);
		
		LocalDate localdate4 = LocalDate.now(ZoneId.of("America/Chicago"));
		System.out.println(localdate4);
		
		LocalDate localdate5 = LocalDate.of(2021, Month.APRIL, 10);
		System.out.println(localdate5);
		
		LocalDate localdate6 = LocalDate.ofEpochDay(0);
		System.out.println(localdate6);

	}

}
