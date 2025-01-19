package RequestTest;
import app.koyeb.example.App;
import com.sun.tools.javac.Main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestSender {
    public static void main(String[] args) throws Exception {
        App.main(args);

        URL url = new URL("http://localhost:8888/test?userID=678c929856283f299c918cf&userRole=Volunteer&isAdmin=true");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        //con.connect();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        con.setDoInput(true);
        JSONObject jsonInput = new JSONObject().put("command", "updateVolunteer")
                .put("args", new JSONArray()
                        .put("Wach")
                        .put("Anouk")
                        .put(true)
                        .put("Rue de la Tour 1")
                        .put("1000")
                        .put("Lausanne")
                        .put("Switzerland")
                        .put("678c9729856283f299c918cf"));
        try(OutputStream os = con.getOutputStream()) {

            byte[] input = jsonInput.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        //read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        System.out.println(content);
        
    }
}
