package Javabackend.GovernmentVolunteer.Volunteer;

import Javabackend.GovernmentVolunteer.Activity.Activity;
import Javabackend.GovernmentVolunteer.DatabaseManager.DatabaseManager;
import Javabackend.JavaBackendException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

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
                //System.out.println(cursor.next().toJson());
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
        Document volunteerDocument = new Document("_id", volunteer.getUserId())
                .append("lastName", volunteer.getLastName())
                .append("firstName", volunteer.getFirstName())
                .append("validated", volunteer.isValidated())
                .append("street", volunteer.getStreet())
                .append("postalCode", volunteer.getPostalCode())
                .append("city", volunteer.getCity())
                .append("country", volunteer.getCountry());
        try {
            dbManager.insertOne(volunteerDocument, VOLUNTEER_COLLECTION);
        } catch (JavaBackendException e) {
            throw new JavaBackendException("Error while adding volunteer: " + e.getMessage());
        }
    }

    public void updateVolunteer(Volunteer volunteer){
        DatabaseManager dbManager = DatabaseManager.getInstance();
        Document volunteerDocument = new Document("_id", volunteer.getUserId())
                .append("lastName", volunteer.getLastName())
                .append("firstName", volunteer.getFirstName())
                .append("validated", volunteer.isValidated())
                .append("street", volunteer.getStreet())
                .append("postalCode", volunteer.getPostalCode())
                .append("city", volunteer.getCity())
                .append("country", volunteer.getCountry());
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

    public static void main(String[] args) throws Exception {
        Volunteer volunteer = new Volunteer(
                "Wach", "Anouk",
                true, "Rue de la Tour 1",
                "1000", "Lausanne", "Switzerland",
                "678c9729856283f299c918cf"
                );

        VolunteerController vController = VolunteerController.getInstance();
        vController.updateVolunteer(volunteer);

        /*if (args.length > 0) {
            if (args[0].equals("test")) {
                // load test parameters
            } else if (args[0].equals("production")) {
                // load production parameters
            }
        }
        VolunteerController vController = new VolunteerController();
        List<Volunteer> volunteers = vController.getVolunteers();
        for (Volunteer volunteer : volunteers) {
            System.out.println(volunteer.getLastName());
        }*/


        /*URL yahoo = new URL("https://hacs.bdemir.net/auth/signup");
        URLConnection yc = yahoo.openConnection();
        HttpURLConnection con = (HttpURLConnection)yahoo.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jsonInputString = "{\"name\":\"Anoukh\" ,\"email\" : \"anoukhan.wachnicki@gmail.com\", \"password\": \"123\", \"role\":\"Volunteer\"}";
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
        System.out.println(con.getResponseMessage());*/
    }
}
