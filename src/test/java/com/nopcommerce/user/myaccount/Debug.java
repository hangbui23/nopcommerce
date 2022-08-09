package com.nopcommerce.user.myaccount;

import utilitiesConfig.DataFacker;

public class Debug {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataFacker faker = DataFacker.getData();
//		LocalDate beginDate = LocalDate.of(2000,1,11);
//		LocalDate endDate = LocalDate.of(2021,2,22);
//		DateTimeSource.getInstance().randomDate(beginDate,endDate, "d");
		
		System.out.println(faker.getVisa());
		System.out.println(faker.getFullName());
		
//		SimpleDateFormat originalFormat = new SimpleDateFormat("M/d/yyyy");
//		TimeZone tzone = TimeZone.getTimeZone("America/New_York");
//		originalFormat.setTimeZone(tzone);
//		String dateString = originalFormat.format(new Date());
//		System.out.println("Date" + dateString);
		
//		System.out.println(DateTimeSource.getInstance().randomDate(beginDate,endDate, "d"));
//		System.out.println(DateTimeSource.getInstance().randomDate(beginDate,endDate, "yyyy"));
//		String text="$1,590.00";
//		
//		String value = text.replace("$", "").replace(",", "");
//		float price = Float.parseFloat(value) *Float.parseFloat("2");
//		System.out.println(price);
		
//		Float.parseFloat(text.replace("$", "").replace(".", "").replace(",", "").trim());
//		
//		System.out.println(Float.parseFloat(text.replace("$", "").replace(".", "").replace(",", "").trim()));
	}

}
