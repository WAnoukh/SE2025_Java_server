package Javabackend.GovernmentVolunteer.Resource;

import Javabackend.GovernmentVolunteer.Location.Location;

public class Resource {

    private String name;
    private int quantity;
    private float unitCost;
    private ResourceStatus status;

    private Location location;

    private String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(float unitCost) {
        this.unitCost = unitCost;
    }

    public ResourceStatus getStatus() {
        return status;
    }

    public void setStatus(ResourceStatus status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public Resource(String name, int quantity, float unitCost, ResourceStatus status, Location location) {
        this.name = name;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.status = status;
        this.location = location;
    }


    public boolean equalsResource(Resource resource) {
        return this.getType() == resource.getType() && this.location == resource.getLocation() && this.status == resource.getStatus();
    }

    public boolean isAvailable() {
        return status == ResourceStatus.Stored;
    }
}
