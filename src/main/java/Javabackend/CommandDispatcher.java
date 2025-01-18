package Javabackend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandDispatcher {
    public static class CommandException extends Exception {
        public CommandException(String message) {
            super(message);
        }
    }

    private CommandDispatcher() {
    }

    public static void dispatch(String command) throws CommandException {
        JSONObject jsonCommand;
        try {
            jsonCommand = new JSONObject(command);
        }catch (JSONException e){
            throw new CommandException("Invalid JSON");
        }
        if(!jsonCommand.has("command")){
            throw new CommandException("No command found");
        }
        String commandName = jsonCommand.getString("command");
        List<String> args = new ArrayList<>();
        if(jsonCommand.has("args")){
            JSONArray jsonArgs;
            try {
                jsonArgs = jsonCommand.getJSONArray("args");
            }catch (JSONException e){
                throw new CommandException("Invalid args");
            }
            for (int i = 0; i < jsonArgs.length(); i++){
                args.add(jsonArgs.getString(i));
            }
        }

        java.lang.reflect.Method function;
        try {
            Class[] cArg = new Class[args.size()];
            for (int i = 0; i < args.size(); i++){
                cArg[i] = String.class;
            }
            function  = CommandDispatcher.class.getDeclaredMethod(commandName, cArg);
        } catch (NoSuchMethodException e) {
            java.lang.reflect.Method[] methods = CommandDispatcher.class.getDeclaredMethods();
            for (Method method : methods){
                if(Objects.equals(method.getName(), commandName)){
                    throw new CommandException("Wrong arguments");
                }
            }
            throw new CommandException("Command not found");
        }
        Object result;
        try {
            Object[] argsArray = new String[args.size()];
            argsArray = args.toArray(argsArray);
            result = function.invoke(null, argsArray);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new CommandException("Error executing command");
        }
    }
}
