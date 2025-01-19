package Javabackend.GovernmentVolunteer.Activity;

import Javabackend.GovernmentVolunteer.Disaster.Disaster;
import Javabackend.GovernmentVolunteer.Location.Location;
import Javabackend.GovernmentVolunteer.Resource.Resource;
import Javabackend.GovernmentVolunteer.Resource.ResourceStatus;
import Javabackend.GovernmentVolunteer.Volunteer.Volunteer;

import java.util.ArrayList;
import java.util.List;

public class Activity {

    private String name;
    private Location location;
    private String objectives;
    private List<Volunteer> volunteers;
    private Disaster disaster;

    private List<Resource> resources;

    private ActivityType activityType;

    private ActivityEmergency activityEmergency;

    private ActivityStatus activityStatus;


    public Activity(String name, Location location, String objectives, List<Volunteer> volunteers, List<Resource> resources, ActivityType activityType, ActivityEmergency activityEmergency) {
        this.name = name;
        this.location = location;
        this.objectives = objectives;
        this.volunteers = volunteers;
        this.resources = resources;
        this.activityType = activityType;
        this.activityEmergency = activityEmergency;
        this.activityStatus = ActivityStatus.Draft;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public ActivityEmergency getActivityEmergency() {
        return activityEmergency;
    }

    public void changeEmergency(ActivityEmergency activityEmergency) {
        this.activityEmergency = activityEmergency;
    }

    public ActivityStatus getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(ActivityStatus activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public int getNbVolunteers() {
        return volunteers.size();
    }

    public void generateReport() {
        // TODO
    }

    protected List<Resource> getResourceByStatus(ResourceStatus status) {
        ArrayList<Resource> validatedResources = new ArrayList<Resource>();
        for (Resource resource : resources) {
            if (resource.getStatus() == status) {
                validatedResources.add(resource);
            }
        }
        return validatedResources;
    }

    public List<Resource> getValidatedResources() {
        return getResourceByStatus(ResourceStatus.Stored);
    }

    public List<Resource> getRequestedResources() {
        return getResourceByStatus(ResourceStatus.InReview);
    }

    public void assignVolunteer(Volunteer volunteer) {
        this.volunteers.add(volunteer);
    }

    public void assignResource(Resource resource) {
        this.resources.add(resource);
    }

    public void assignResource(List<Resource> resources) {
        this.resources.addAll(resources);
    }

    //TODO: implement this method
    /*public void requestVolunteers(int nb) {
        int nbv = nb + this.getNbVolunteers();
        for (int i = this.getNbVolunteers() ; i < nbv; i++) {
            resources.add(new Resource("%s_%s_%d".formatted(this.getName(),"volunteer",i), 1, 0, ResourceStatus.InReview, this.location));
        }
    }

    public void requestResource(ResourceType type, int quantity) {
        resources.add(new Resource("%s_%s".formatted(this.getName(), type), quantity, 0, ResourceStatus.InReview, this.location));
    }

    public boolean hasAccess(User user) {
        return true; // TODO
    }*/

    public void getStatistics() {
        //todo
    }


}
