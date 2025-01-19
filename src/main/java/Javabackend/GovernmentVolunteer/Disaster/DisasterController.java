package Javabackend.GovernmentVolunteer.Disaster;

import Javabackend.GovernmentVolunteer.Activity.Activity;
import Javabackend.GovernmentVolunteer.Volunteer.Volunteer;

import java.util.List;

public class DisasterController {
    private static DisasterController instance = null;

    private DisasterController() {
    }

    public static DisasterController getInstance() {
        if (instance == null) {
            instance = new DisasterController();
        }
        return instance;
    }

    public List<Disaster> getDisasters() {
        //TODO: implement this method
        return null;
    }

    public void addDisaster(Disaster disaster) {
        //TODO: implement this method
    }

    public void updateDisaster(Disaster disaster) {
        //TODO: implement this method
    }

    public List<Activity> getActivities(Disaster disaster) {
        //TODO: implement this method
        return null;
    }

    public Disaster getActivityDisaster(Activity activity) {
        //TODO: implement this method
        return null;
    }

    public List<Volunteer> getAllVolunteersInvolved(Disaster disaster) {
        //TODO: implement this method
        return null;
    }

    public void generateReport(String path, Disaster disaster) {
        //TODO: implement this method
    }
}
