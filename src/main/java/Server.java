import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@WebServlet("/UserServer")
@MultipartConfig
public class Server extends HttpServlet {

    private HashMap<Integer, User> users = new HashMap<Integer, User>();

    public Server() {
        addUsersToMap();
    }

    private void addUsersToMap() {
        for (int i = 0; i < 10; i++) {
            User user = new User(String.format("Test%d", i), "PSsrw12@", "Test@test.com");
            users.put(i, user);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        int id = Integer.parseInt(request.getParameter("id"));

        if (users.containsKey(id)) {
            User user = users.get(id);
            outputStream.println(""
                    + "<html><head><title>Student</title></head>" + "<body>"
                    + "Username: " + user.getUsername() + "<br>"
                    + "Password: " + user.getPassword() + "<br>"
                    + "Email: " + user.getEmail() + "<br>"
                    + "</body>"
                    + "</html>");

        } else {
            outputStream.println("Test user doesnt exist here");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        int id = Integer.parseInt(request.getParameter("id"));

        if (users.containsKey(id)) {
            outputStream.println("Test user already exist here");


        } else {
            User user = new User(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
            users.put(id, user);
            outputStream.println(""
                    + "<html><head><title>Student</title></head>" + "<body>"
                    + "User with the following paramaters has been added <br>"
                    + "Username: " + user.getUsername() + "<br>"
                    + "Password: " + user.getPassword() + "<br>"
                    + "Email: " + user.getEmail() + "<br>"
                    + "</body>"
                    + "</html>");

        }

    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        int id = Integer.parseInt(request.getParameter("id"));

        if (!users.containsKey(id)) {
            outputStream.println("User already exists");
        } else {

            User user = new User(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
            users.put(id, user);
            outputStream.println("User updated");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        int id = Integer.parseInt(request.getParameter("id"));

        if (users.containsKey(id)) {
            users.remove(id);
            outputStream.println("User deleted");

        } else {
            outputStream.println("User doesnt exist");
        }

    }
}
