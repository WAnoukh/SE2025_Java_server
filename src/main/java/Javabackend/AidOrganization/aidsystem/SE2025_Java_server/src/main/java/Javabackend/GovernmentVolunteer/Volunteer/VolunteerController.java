package Javabackend.GovernmentVolunteer.Volunteer;

import Javabackend.GovernmentVolunteer.Activity.Activity;
import Javabackend.GovernmentVolunteer.DatabaseManager.DatabaseManager;
import Javabackend.JavaBackendException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// add import aid organisation component
// add import server and database component

public class VolunteerController {

    private static final String VOLUNTEER_COLLECTION = "volunteers";
    public static VolunteerController instance = null;

    private VolunteerController() {
    }

    public static VolunteerController getInstance() {
        if (instance == null) {
            instance = new VolunteerController();
        }
        return instance;
    }

    public List<Volunteer> getVolunteers() {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        MongoCollection<Document> volunteerCollection = dbManager.getCollection(VOLUNTEER_COLLECTION);
        List<Volunteer> volunteers = new ArrayList<>();
        try (MongoCursor<Document> cursor = volunteerCollection.find().iterator()) {
            while (cursor.hasNext()) {
                try {
                    volunteers.add(new Volunteer(cursor.next()));
                } catch (Exception e) {
                    System.out.println("Error while getting volunteers: " + e.getMessage());
                }

            }
        }
        return volunteers;
    }

    public List<Activity> getVolunteersActivities(Volunteer volunteer){
        //TODO: implement this method
        return null;
    }

    public List<Volunteer> getVolunteersByActivity(Activity activity){
        //TODO: implement this method
        return null;
    }

    public void assignVolunteerToActivity(Volunteer volunteer, Activity activity){
        //TODO: implement this method
    }

    public void addVolunteer(Volunteer volunteer) throws JavaBackendException {
        DatabaseManager dbManager = DatabaseManager.getInstance();;
        Document volunteerDocument = Document.parse(volunteer.toJson().toString());
        try {
            dbManager.insertOne(volunteerDocument, VOLUNTEER_COLLECTION);
        } catch (JavaBackendException e) {
            throw new JavaBackendException("Error while adding volunteer: " + e.getMessage());
        }
    }

    public void updateVolunteer(Volunteer volunteer){
        DatabaseManager dbManager = DatabaseManager.getInstance();
        Document volunteerDocument = Document.parse(volunteer.toJson().toString());
        try {
            dbManager.updateOne(volunteerDocument, VOLUNTEER_COLLECTION);
        } catch (Exception e) {
            System.out.println("Error while updating volunteer: " + e.getMessage());
        }
    }

    public void createVolunteerFromUser(String lastName, String firstName, boolean validated, String street, String postalCode, String city, String country, String userId) throws JavaBackendException {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        try {
            if(dbManager.getDocumentById(userId, "users") == null){
                throw new JavaBackendException("No user found with this id, cannot create volunteer");
            }
        } catch (JavaBackendException e) {
            throw new RuntimeException(e);
        }
        Volunteer volunteer = new Volunteer(lastName, firstName, validated, street, postalCode, city, country, userId);
        addVolunteer(volunteer);
    }

    public void updateVolunteer(String lastName, String firstName, boolean validated, String street, String postalCode, String city, String country, String userId) throws JavaBackendException {
        Volunteer volunteer = new Volunteer(lastName, firstName, validated, street, postalCode, city, country, userId);
        updateVolunteer(volunteer);
    }

    public JSONArray getVolunteersAsJsonArray() {
        List<Volunteer> volunteers = getVolunteers();
        JSONArray jsonArray = new JSONArray();
        for (Volunteer volunteer : volunteers) {
            jsonArray.put(volunteer.toJson());
        }
        return jsonArray;
    }
}
