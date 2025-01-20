package Javabackend.GovernmentVolunteer.GovernmentRepresentative;

import java.util.List;

public class GovernmentRepresentativeController {
    private static GovernmentRepresentativeController instance = null;

    private GovernmentRepresentativeController() {
    }

    public static GovernmentRepresentativeController getInstance() {
        if (instance == null) {
            instance = new GovernmentRepresentativeController();
        }
        return instance;
    }

    public List<GovernmentRepresentative> getGovernmentRepresentatives() {
        //TODO: implement this method
        return null;
    }

    public void addGovernmentRepresentative(GovernmentRepresentative governmentRepresentative) {
        //TODO: implement this method
    }

    public void updateGovernmentRepresentative(GovernmentRepresentative governmentRepresentative) {
        //TODO: implement this method
    }


}
