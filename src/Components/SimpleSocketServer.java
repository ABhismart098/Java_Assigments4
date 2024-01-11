// import com.sun.net.httpserver.HttpExchange;
// import com.sun.net.httpserver.HttpHandler;
// import com.sun.net.httpserver.HttpServer;

// import java.io.*;
// import java.net.InetSocketAddress;
// import java.util.ArrayList;
// import java.util.List;

// public class SimpleSocketServer {
//     private static final int PORT = 3002;
//     private static final String DATA_FILE_PATH = "Data.csv";

//     public static void main(String[] args) throws IOException {
//         HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
//         server.createContext("/data", new DataHandler());
//         server.setExecutor(null);
//         server.start();
//         System.out.println("Server started on port " + PORT);
//     }

//     static class DataHandler implements HttpHandler {
//         @Override
//         public void handle(HttpExchange exchange) throws IOException {
//             if ("GET".equals(exchange.getRequestMethod())) {
//                 try {
//                     File csvFile = new File(DATA_FILE_PATH);
//                     List<String> lines = new ArrayList<>();

//                     try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//                         String line;
//                         while ((line = br.readLine()) != null) {
//                             lines.add(line);
//                         }
//                     }

//                     // Assuming that the first line contains headers and the rest are data rows
//                     String[] headers = lines.get(0).split(",");
//                     List<String[]> data = new ArrayList<>();

//                     for (int i = 1; i < lines.size(); i++) {
//                         data.add(lines.get(i).split(","));
//                     }

//                     // You can now process the headers and data as needed
//                     // For simplicity, let's just print them
//                     System.out.println("Headers: " + String.join(", ", headers));
//                     System.out.println("Data: " + data);

//                     // In a real application, you would convert the data to JSON and send it as the
//                     // response
//                     String jsonResponse = "{\"headers\": " + String.join(", ", headers) + ", \"data\": " + data + "}";
//                     exchange.sendResponseHeaders(200, jsonResponse.length());
//                     try (OutputStream os = exchange.getResponseBody()) {
//                         os.write(jsonResponse.getBytes());
//                     }
//                 } catch (Exception e) {
//                     e.printStackTrace();
//                     exchange.sendResponseHeaders(500, 0);
//                 } finally {
//                     exchange.close();
//                 }
//             } else {
//                 exchange.sendResponseHeaders(405, 0); // Method Not Allowed
//                 exchange.close();
//             }
//         }
//     }
// }
// import com.sun.net.httpserver.HttpExchange;
// import com.sun.net.httpserver.HttpHandler;
// import com.sun.net.httpserver.HttpServer;

// import java.io.*;
// import java.net.InetSocketAddress;
// import java.util.ArrayList;
// import java.util.List;

// public class SimpleSocketServer {
//     private static final int PORT = 3002;
//     private static final String DATA_FILE_PATH = "Data.csv";

//     public static void main(String[] args) throws IOException {
//         HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
//         server.createContext("/data", new DataHandler());
//         server.setExecutor(null);
//         server.start();
//         System.out.println("Server started on port " + PORT);
//     }

//     static class DataHandler implements HttpHandler {
//         @Override
//         public void handle(HttpExchange exchange) throws IOException {
//             if ("GET".equals(exchange.getRequestMethod())) {
//                 try {
//                     File csvFile = new File(DATA_FILE_PATH);
//                     List<String> lines = new ArrayList<>();

//                     try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//                         String line;
//                         while ((line = br.readLine()) != null) {
//                             lines.add(line);
//                         }
//                     }

//                     // Assuming that the first line contains headers and the rest are data rows
//                     String[] headers = lines.get(0).split(",");
//                     List<String[]> data = new ArrayList<>();

//                     for (int i = 1; i < lines.size(); i++) {
//                         data.add(lines.get(i).split(","));
//                     }

//                     // You can now process the headers and data as needed
//                     // For simplicity, let's just print them
//                     System.out.println("Headers: " + String.join(", ", headers));
//                     System.out.println("Data: " + data);

//                     // In a real application, you would convert the data to JSON and send it as the
//                     // response
//                     String jsonResponse = "{\"headers\": " + String.join(", ", headers) + ", \"data\": " + data + "}";
//                     exchange.sendResponseHeaders(200, jsonResponse.length());
//                     try (OutputStream os = exchange.getResponseBody()) {
//                         os.write(jsonResponse.getBytes());
//                     }
//                 } catch (Exception e) {
//                     e.printStackTrace();
//                     exchange.sendResponseHeaders(500, 0);
//                 } finally {
//                     exchange.close();
//                 }
//             } else {
//                 exchange.sendResponseHeaders(405, 0); // Method Not Allowed
//                 exchange.close();
//             }
//         }
//     }
// }
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class SimpleSocketServer {
    private static final int PORT = 3003;
    private static final String DATA_FILE_PATH = "Data.csv";

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/data", new DataHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port " + PORT);
    }

    static class DataHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                try {
                    File csvFile = new File(DATA_FILE_PATH);
                    List<String> lines = new ArrayList<>();

                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            lines.add(line);
                        }
                    }

                    // Assuming that the first line contains headers and the rest are data rows
                    String[] headers = lines.get(0).split(",");
                    List<String[]> data = new ArrayList<>();

                    for (int i = 1; i < lines.size(); i++) {
                        data.add(lines.get(i).split(","));
                    }

                    // You can now process the headers and data as needed
                    // For simplicity, let's just print them
                    System.out.println("Headers: " + String.join(", ", headers));
                    System.out.println("Data: " + data);

                    // In a real application, you would convert the data to JSON and send it as the
                    // response
                    String jsonResponse = "{\"headers\": " + String.join(", ", headers) + ", \"data\": " + data + "}";

                    // Set the Content-Type header to indicate JSON data
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, jsonResponse.length());
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(jsonResponse.getBytes());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    exchange.sendResponseHeaders(500, 0);
                } finally {
                    exchange.close();
                }
            } else {
                exchange.sendResponseHeaders(405, 0); // Method Not Allowed
                exchange.close();
            }
        }
    }
}
