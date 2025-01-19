package Javabackend;

import Javabackend.GovernmentVolunteer.Volunteer.VolunteerController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandDispatcher {
    public static class UserInfo {
        public String userID;
        public String userRole;
    }
    public static class CommandException extends Exception {
        public CommandException(String message) {
            super(message);
        }
    }

    private CommandDispatcher() {
    }

    public static JSONObject dispatch(String command, UserInfo userInfo) throws CommandException {
        JSONObject jsonCommand;
        try {
            jsonCommand = new JSONObject(command);
        }catch (JSONException e){
            throw new CommandException("Invalid JSON");
        }
        if(!jsonCommand.has("command")){
            throw new CommandException("No command found in JSON");
        }
        String commandName = jsonCommand.getString("command");
        List<Object> args = new ArrayList<>();
        args.add(userInfo);
        if(jsonCommand.has("args")){
            JSONArray jsonArgs;
            try {
                jsonArgs = jsonCommand.getJSONArray("args");
            }catch (JSONException e){
                throw new CommandException("Invalid args, args should be an array of strings");
            }
            for (int i = 0; i < jsonArgs.length(); i++){
                args.add(jsonArgs.get(i));
            }
        }

        java.lang.reflect.Method function;
        try {
            Class[] cArg = new Class[args.size()];
            for (int i = 0; i < args.size(); i++){
                cArg[i] = args.get(i).getClass();
            }
            function  = CommandDispatcher.class.getDeclaredMethod(commandName, cArg);
        } catch (NoSuchMethodException e) {
            java.lang.reflect.Method[] methods = CommandDispatcher.class.getDeclaredMethods();
            for (Method method : methods){
                if(Objects.equals(method.getName(), commandName)){
                    throw new CommandException("No matching method found for command with " + args.size() + " arguments with the name " + commandName);
                }
            }
            throw new CommandException("No matching method found for command with the name " + commandName);
        }
        Object result;
        try {
            Object[] argsArray = new Object[args.size()];
            argsArray = args.toArray(argsArray);
            result = function.invoke(null, argsArray);
            if(result instanceof JSONObject) {
                return (JSONObject) result;
            }else {
                throw new CommandException("Invalid return type for command : " + commandName);
            }
        } catch (IllegalAccessException e) {
            throw new CommandException("Error executing command " + commandName + " : " + e.getMessage());
        } catch (InvocationTargetException e) {
            throw new CommandException("Error executing command " + commandName + " : " + e.getTargetException().getMessage());
        }
    }

    public static JSONObject createVolunteerFromUser(UserInfo userInfo,String lastName, String firstName, Boolean validated, String street, String postalCode, String city, String country, String userId) {
        VolunteerController volunteerController = VolunteerController.getInstance();
        try {
            volunteerController.createVolunteerFromUser(lastName, firstName, validated, street, postalCode, city, country, userId);
            return new JSONObject().put("status", "success").put("message", "Volunteer created");
        } catch (JavaBackendException e) {
            return new JSONObject().put("status", "error").put("message", e.getMessage());
        }
    }

    public static JSONObject updateVolunteer(UserInfo userInfo, String lastName, String firstName, Boolean validated, String street, String postalCode, String city, String country, String userId) {
        VolunteerController volunteerController = VolunteerController.getInstance();
        try {
            volunteerController.updateVolunteer(lastName, firstName, validated, street, postalCode, city, country, userId);
            return new JSONObject().put("status", "success").put("message", "Volunteer updated");
        } catch (JavaBackendException e) {
            return new JSONObject().put("status", "error").put("message", e.getMessage());
        }
    }
}
