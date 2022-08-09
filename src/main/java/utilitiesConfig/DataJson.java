package utilitiesConfig;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataJson {
public static DataJson getFileName(String fileName) {
	try {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(new File(fileName), DataJson.class);
	} catch (Exception ex) {
		ex.printStackTrace();
		return null;
	}
}

@JsonProperty("firstName")
private String firstName;

@JsonProperty("lastName")
private String lastName;

@JsonProperty("register")
private Register register;

static class Register{
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("address")
	private String address;
}

public String getFirstName() {
	return firstName;
}

public String getLastName() {
	return lastName;
}

public String getAddress() {
	return register.address;
}

public String getCity() {
	return register.city;
}
}
