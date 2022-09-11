package webapp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


//Question: Could you use servlets to handle response as well?
public class Client {

    private final HttpClient client = HttpClient.newHttpClient();
    Scanner input = new Scanner(System.in);
    boolean active = true;

    public Client() {
        try {
            driver();
        } catch (IOException | InterruptedException exception) {
            System.out.println(exception.getMessage() + exception.getCause());
        }

    }

    public void driver() throws IOException, InterruptedException {
        while (active) {
            System.out.println(""
                    + "1. Get user info: "
                    + "2. Login (Post request): "
                    + "3. Put (Create/Update): "
                    + "4. Delete: "
                    + "Input anything else to terminate");
            int selector = input.nextInt();
            switch (selector) {
                case 1 -> sendGet();
                case 2 -> sendPost();
                case 3 -> sendPut();
                case 4 -> sendDelete();
                default -> {
                    active = false;
                    System.out.println("Quitting");
                }
            }
        }


    }

    private URI urlGenerator(String parameters) {
        return URI.create("http://localhost:8080/JavaModuleTutoringProgram/Server?" + parameters);
    }

    public void sendGet() throws IOException, InterruptedException {
        System.out.println("Please provide ID as a parameter or 'all'");
        String parameter = "who=" + input.next();
        URI uri = urlGenerator(parameter);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        displayResponse(response);


    }

    public void sendPost() throws IOException, InterruptedException {

        System.out.println("Please provide first username then password for login'");
        String parameter = "username=" + input.next() + "&" + "password=" + input.next();
        URI uri = urlGenerator(parameter);
        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.noBody()).uri(uri).build();
        System.out.println(request.uri());
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        displayResponse(response);

    }

    public void sendPut() throws IOException, InterruptedException {

        System.out.println("Please provide ID,username,password and email");
        String parameter = "id=" + input.next() + "&" + "username=" + input.next() + "&" + "password=" + input.next() + "&email=" + input.next();
        URI uri = urlGenerator(parameter);
        HttpRequest request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.noBody()).uri(uri).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        displayResponse(response);
    }

    public void sendDelete() throws IOException, InterruptedException {
        System.out.println("Please provide ID as a parameter");
        String parameter = "id=" + input.next();
        URI uri = urlGenerator(parameter);
        HttpRequest request = HttpRequest.newBuilder().DELETE().uri(uri).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        displayResponse(response);
    }

    private void displayResponse(HttpResponse<String> response) {
        System.out.println(response.body());
    }
}
