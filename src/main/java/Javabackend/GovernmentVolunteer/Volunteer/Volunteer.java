package Javabackend.GovernmentVolunteer.Volunteer;

import org.bson.Document;
import org.json.JSONObject;

public class Volunteer {
    private String lastName;
    private String firstName;
    private boolean validated;
    private String street;
    private String postalCode;
    private String city;
    private String country;
    private String userId;

    public Volunteer(String lastName, String firstName, boolean validated, String street, String postalCode, String city, String country, String userId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.validated = validated;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.userId = userId;
    }

    public Volunteer(Document document) {
        this.lastName = null;
        this.firstName = null;
        this.validated = false;
        this.street = null;
        this.postalCode = null;
        this.city = null;
        this.country = null;
        this.userId = document.getString("userId");
        if (document.containsKey("lastName")) {
            this.lastName = document.getString("lastName");
        }
        if (document.containsKey("firstName")) {
            this.firstName = document.getString("firstName");
        }
        if (document.containsKey("validated")) {
            this.validated = document.getBoolean("validated");
        }
        if (document.containsKey("country")) {
            this.street = document.getString("country");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("lastName", lastName);
        json.put("firstName", firstName);
        json.put("validated", validated);
        json.put("street", street);
        json.put("postalCode", postalCode);
        json.put("city", city);
        json.put("country", country);
        json.put("userId", userId);
        json.put("_id", userId);
        return json;
    }
}
