package utilitiesConfig;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

public class DataFacker {
	private Locale locale = new Locale("en");
	private Faker faker = new Faker(locale);

	public static DataFacker getData() {
		return new DataFacker();
	}

	public String getFirstName() {
		return faker.name().firstName();
	}
	
	public String getGender() {
		return faker.demographic().sex();
	}
	
	public String getLastName() {
		return faker.name().lastName();
	}
	
	public String getCompanyName() {
		return faker.company().name();
	}
	
	public String getCountry() {
		return faker.address().country();
	}
	
	public String getState() {
		return faker.address().state();
	}
	
	public String getAddress() {
		return faker.address().streetAddress();
	}
	
	public String getZipCode() {
		return faker.address().zipCode();
	}
	
	public String getFullName() {
		return faker.name().fullName().toUpperCase();
	}
	
	public String getEmail() {
		return faker.internet().emailAddress();
	}
	
	public String getPhone() {
		return faker.phoneNumber().cellPhone();
	}
	
	public String getCityName() {
		return faker.address().cityName();
	}
	
	public String getPassword() {
		return faker.internet().password();
	}
	
	public String getVisa() {
		return faker.finance().creditCard();
	}
}
