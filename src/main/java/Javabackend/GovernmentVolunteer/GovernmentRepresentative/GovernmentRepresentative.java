package Javabackend.GovernmentVolunteer.GovernmentRepresentative;

public class GovernmentRepresentative {
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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    private String lastName;
    private String firstName;
    private String function;

    public String getUserId() {
        return userId;
    }

    private String userId;
}
