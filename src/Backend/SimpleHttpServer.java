// import com.sun.net.httpserver.HttpExchange;
// import com.sun.net.httpserver.HttpHandler;
// import com.sun.net.httpserver.HttpServer;

// import java.io.*;
// import java.net.InetSocketAddress;
// import java.util.ArrayList;
// import java.util.List;

// public class SimpleHttpServer {
//     private static final int PORT = 3001;
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
//             File csvFile = new File(DATA_FILE_PATH);
//             List<String> lines = new ArrayList<>();

//             try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//                 String line;
//                 while ((line = br.readLine()) != null) {
//                     lines.add(line);
//                 }
//             }

//             // Assuming that the first line contains headers and the rest are data rows
//             String[] headers = lines.get(0).split(",");
//             List<String[]> data = new ArrayList<>();

//             for (int i = 1; i < lines.size(); i++) {
//                 data.add(lines.get(i).split(","));
//             }

//             // You can now process the headers and data as needed
//             // For simplicity, let's just print them
//             System.out.println("Headers: " + String.join(", ", headers));
//             System.out.println("Data: " + data);

//             // In a real application, you would convert the data to JSON and send it as the
//             // response
//             String jsonResponse = "{\"headers\": " + String.join(", ", headers) + ", \"data\": " + data + "}";
//             exchange.sendResponseHeaders(200, jsonResponse.length());
//             try (OutputStream os = exchange.getResponseBody()) {
//                 os.write(jsonResponse.getBytes());
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

// public class SimpleHttpServer {
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
//             try {
//                 File csvFile = new File(DATA_FILE_PATH);
//                 List<String> lines = new ArrayList<>();

//                 try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//                     String line;
//                     while ((line = br.readLine()) != null) {
//                         lines.add(line);
//                     }
//                 }

//                 // Assuming that the first line contains headers and the rest are data rows
//                 String[] headers = lines.get(0).split(",");
//                 List<String[]> data = new ArrayList<>();

//                 for (int i = 1; i < lines.size(); i++) {
//                     data.add(lines.get(i).split(","));
//                 }

//                 // You can now process the headers and data as needed
//                 // For simplicity, let's just print them
//                 System.out.println("Headers: " + String.join(", ", headers));
//                 System.out.println("Data: " + data);

//                 // In a real application, you would convert the data to JSON and send it as the
//                 // response
//                 String jsonResponse = "{\"headers\": " + String.join(", ", headers) + ", \"data\": " + data + "}";
//                 exchange.sendResponseHeaders(200, jsonResponse.length());
//                 try (OutputStream os = exchange.getResponseBody()) {
//                     os.write(jsonResponse.getBytes());
//                 }
//             } catch (Exception e) {
//                 e.printStackTrace();
//                 exchange.sendResponseHeaders(500, 0);
//             } finally {
//                 exchange.close();
//             }
//         }
//     }
// }
// import com.sun.net.httpserver.HttpServer;
// import com.sun.net.httpserver.HttpHandler;
// import com.sun.net.httpserver.HttpExchange;

// import java.io.IOException;
// import java.io.OutputStream;
// import java.net.InetSocketAddress;

// public class SimpleHttpServer {

//     public static void main(String[] args) throws IOException {
//         HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
//         server.createContext("/api/data", new DataHandler());
//         server.setExecutor(null); // creates a default executor
//         server.start();
//         System.out.println("Server started on port 8080");
//     }

//     static class DataHandler implements HttpHandler {
//         @Override
//         public void handle(HttpExchange exchange) throws IOException {
//             if ("GET".equals(exchange.getRequestMethod())) {
//                 // TODO: Retrieve data from your database and generate the response

//                 String response = "Hello, this is your API response!";
//                 exchange.sendResponseHeaders(200, response.length());
//                 try (OutputStream os = exchange.getResponseBody()) {
//                     os.write(response.getBytes());
//                 }
//             } else {
//                 exchange.sendResponseHeaders(405, 0); // Method Not Allowed
//             }
//             exchange.close();
//         }
//     }
// }
// import com.sun.net.httpserver.HttpServer;
// import com.sun.net.httpserver.HttpHandler;
// import com.sun.net.httpserver.HttpExchange;
// import com.corundumstudio.socketio.Configuration;
// import com.corundumstudio.socketio.SocketIOServer;

// import java.io.IOException;
// import java.io.OutputStream;
// import java.net.InetSocketAddress;

// public class SimpleHttpServer {

//     public static void main(String[] args) throws IOException {
//         // Start the HTTP server
//         HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
//         server.createContext("/api/data", new DataHandler());
//         server.setExecutor(null);
//         server.start();
//         System.out.println("HTTP Server started on port 8080");

//         // Start the WebSocket server
//         Configuration config = new Configuration();
//         config.setHostname("localhost");
//         config.setPort(9092);
//         SocketIOServer socketIoServer = new SocketIOServer(config);
//         socketIoServer.start();
//         System.out.println("WebSocket Server started on port 9092");

//         // Add a periodic task to send data updates to connected clients
//         socketIoServer.addConnectListener(client -> {
//             // Simulate data updates every 5 seconds
//             socketIoServer.getBroadcastOperations().sendEvent("data-update", "New data from server!");
//         });
//     }

//     static class DataHandler implements HttpHandler {
//         @Override
//         public void handle(HttpExchange exchange) throws IOException {
//             if ("GET".equals(exchange.getRequestMethod())) {
//                 // TODO: Retrieve data from your database and generate the response

//                 String response = "Hello, this is your API response!";
//                 exchange.sendResponseHeaders(200, response.length());
//                 try (OutputStream os = exchange.getResponseBody()) {
//                     os.write(response.getBytes());
//                 }
//             } else {
//                 exchange.sendResponseHeaders(405, 0); // Method Not Allowed
//             }
//             exchange.close();
//         }
//     }
// }
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        // Start the HTTP server
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/data", new DataHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("HTTP Server started on port 8080");

        // Start the WebSocket server
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);
        SocketIOServer socketIoServer = new SocketIOServer(config);
        socketIoServer.start();
        System.out.println("WebSocket Server started on port 9092");

        // Add a periodic task to send data updates to connected clients
        socketIoServer.addConnectListener(client -> {
            // Simulate data updates every 5 seconds
            socketIoServer.getBroadcastOperations().sendEvent("data-update", "New data from server!");
        });
    }

    static class DataHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                // TODO: Retrieve data from your database and generate the response

                String response = "Hello, this is your API response!";
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            } else {
                exchange.sendResponseHeaders(405, 0); // Method Not Allowed
            }
            exchange.close();
        }
    }
}
