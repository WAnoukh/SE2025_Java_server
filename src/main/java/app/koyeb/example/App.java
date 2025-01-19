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

public class App {

  public static void main(String[] args) throws Exception {
    int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8888"));
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/", new MyHandler());
    server.setExecutor(Executors.newCachedThreadPool());
    System.out.println("Listening on port " + port + ".");
    server.start();
  }

  static class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
      String response = null;

      if (t.getRequestURI().getQuery() == null) {
        response = "{\"success\": \"false\", \"message\":\"No query parameters found\"}";
      }

      Map<String, String> args = splitQuery(t.getRequestURI().getQuery());

      if(!args.containsKey("userID") || !args.containsKey("userRole")) {
        response = "{\"success\": \"false\", \"message\":\"Missing required parameters\"}";
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
          response = "{\"success\": \"true\", \"data\":" + inputLine + "}";
        } catch (Exception e) {
          response = "{\"success\": \"false\", \"message\":\"" + e.getMessage() + "\"}";
        }
      }


      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
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
