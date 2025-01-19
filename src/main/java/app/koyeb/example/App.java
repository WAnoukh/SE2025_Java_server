package app.koyeb.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import Javabackend.CommandDispatcher;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONArray;
import org.json.JSONObject;

public class App {

  public static void main(String[] args) throws Exception {
    int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8888"));
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/", new MyHandler());
    server.setExecutor(Executors.newCachedThreadPool());
    System.out.println("Listening on port " + port + ".");
    server.start();

    URL url = new URL("http://localhost:8888/test?userID=someID&userRole=Volunteer");
    HttpURLConnection con = (HttpURLConnection)url.openConnection();
    con.setRequestMethod("GET");
    //con.connect();
    con.setRequestProperty("Content-Type", "application/json");
    con.setRequestProperty("Accept", "application/json");
    con.setDoOutput(true);
    con.setDoInput(true);
    JSONObject jsonInput = new JSONObject().put("command", "createVolunteerFromUser")
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

  static class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
      JSONObject response = null;

      if (t.getRequestURI().getQuery() == null) {
        response = new JSONObject().put("success", "false").put("message", "No query parameters found");
      }

      Map<String, String> args = splitQuery(t.getRequestURI().getQuery());

      if(!args.containsKey("userID") || !args.containsKey("userRole")) {
        response = new JSONObject().put("success", "false").put("message", "Missing required parameters");
      }

      if(response == null) {
        try {
          String inputLine;
          BufferedReader reader = new BufferedReader(new InputStreamReader(t.getRequestBody(), "utf-8"));
          StringBuilder builder = new StringBuilder();
          String line = null;
          while ((line = reader.readLine()) != null) {
            builder.append(line);
          }
          inputLine = builder.toString();
          CommandDispatcher.dispatch(inputLine);
          response = new JSONObject().put("success", "true").put("message", "Command dispatched successfully");
        } catch (Exception e) {
          response = new JSONObject().put("success", "false").put("message", e.getMessage());
        }
      }


      t.sendResponseHeaders(200, response.toString().length());
      OutputStream os = t.getResponseBody();
      os.write(response.toString().getBytes());
      os.close();
    }
  }

  public static Map<String, String> splitQuery(String query) {
    Map<String, String> query_pairs = new LinkedHashMap<String, String>();
    String[] pairs = query.split("&");
    for (String pair : pairs) {
      int idx = pair.indexOf("=");
      query_pairs.put(pair.substring(0, idx), pair.substring(idx + 1));
    }
    return query_pairs;
  }
}
