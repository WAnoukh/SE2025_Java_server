package Javabackend.GovernmentVolunteer.Volunteer;

import Javabackend.GovernmentVolunteer.Activity.Activity;
import Javabackend.GovernmentVolunteer.DatabaseManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.net.*;
import java.io.*;

// add import aid organisation component
// add import server and database component

public class VolunteerController {

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
        MongoCollection<Document> volunteerCollection = dbManager.getCollection("volunteers");
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

    public void addVolunteer(Volunteer volunteer){
        //TODO: implement this method
    }

    public void updateVolunteer(Volunteer volunteer){
        //TODO: implement this method
    }

    public static void main(String[] args) throws Exception {
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


        URL yahoo = new URL("https://hacs.bdemir.net/auth/login");
        URLConnection yc = yahoo.openConnection();
        HttpURLConnection con = (HttpURLConnection)yahoo.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jsonInputString = "{ \"email\" : \"iletisim@bdemir.net\", \"password\": \"123\"}";
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
        System.out.println(con.getResponseMessage());
    }
}
