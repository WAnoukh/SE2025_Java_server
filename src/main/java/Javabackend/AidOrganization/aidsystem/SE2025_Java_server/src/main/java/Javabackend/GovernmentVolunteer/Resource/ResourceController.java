package Javabackend.GovernmentVolunteer.Resource;

import Javabackend.GovernmentVolunteer.Activity.Activity;
import Javabackend.GovernmentVolunteer.Location.Location;

import java.util.List;

public class ResourceController {
    private static ResourceController instance = null;

    private ResourceController() {
    }

    public static ResourceController getInstance() {
        if (instance == null) {
            instance = new ResourceController();
        }
        return instance;
    }

    public List<Resource> getResources() {
        //TODO: implement this method
        return null;
    }

    public List<Resource> getResourcesByType(ResourceType resourceType) {
        //TODO: implement this method
        return null;
    }

    public List<Resource> getResourcesByStatus(ResourceStatus resourceStatus) {
        //TODO: implement this method
        return null;
    }

    public List<Resource> getResourcesByActivity(Activity activity) {
        //TODO: implement this method
        return null;
    }

    public Activity getAssignedActivity(Resource resource) {
        //TODO: implement this method
        return null;
    }

    public void assignResourceToActivity(Resource resource, Activity activity) {
        //TODO: implement this method
    }

    public void assignResourceToActivity(Resource resource, Activity activity, int quantity) {
        //TODO: implement this method
    }

    public void addResource(Resource resource) {
        //TODO: implement this method
    }

    public void updateResource(Resource resource) {
        //TODO: implement this method
    }

    public void removeResource(Resource resource) {
        //TODO: implement this method
    }

    public void useResource(Resource resource, int quantity) {
        //TODO: implement this method
        /*this.status = ResourceStatus.InUse;
        this.setQuantity(this.getQuantity() - quantity);*/
    }

    public void useResource(Resource resource) {
        //TODO: implement this method
        /*this.status = ResourceStatus.InUse;
        this.setQuantity(this.getQuantity() - 1);*/
    }

    public void changeResourceQuantity(Resource resource, int quantity) {
        //TODO: implement this method
    }

    public void mergeResource(Resource resource, Resource resourceToMerge) {
        //TODO: implement this method
        /*if (equalsResource(resource)) {
            this.addQuantity(resource.quantity);
        }
        resource.deleteResource();*/
    }

    public Resource splitResource(Resource resource, int quantity) {
        //TODO: implement this method
        /*this.removeQuantity(quantity);
        return new Resource(this.name, quantity, this.unitCost, ResourceStatus.Draft, this.location);*/
        return null;
    }

    public Resource sendResource(Resource resource, int quantity, Location location) {
        //TODO: implement this method
        /*this.removeQuantity(quantity);
        return new Resource(this.name, quantity, this.unitCost, ResourceStatus.InTransit, location);*/
        return null;
    }

    public void validateResourceReview(Resource resource) {
        //TODO: implement this method
        //this.status = ResourceStatus.Stored;
    }

    public void denyResourceReview(Resource resource) {
        //TODO: implement this method
        //this.status = ResourceStatus.Draft;
    }

    public void freeResource(Resource resource) {
        //TODO: implement this method
        //this.status = ResourceStatus.Stored;
    }

    public void publish(Resource resource) {
        //TODO: implement this method
        //status = ResourceStatus.Stored;
    }

    public void generateReport(String path, List<Resource> resources) {
        //TODO: implement this method
    }
}
