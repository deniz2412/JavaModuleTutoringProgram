package webapp;

import exceptions.InvalidUserException;
import exceptions.NoUserException;
import user.User;
import user.UserDaoImplInMemory;
import user.UserService;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Server")
@MultipartConfig
public class Server extends HttpServlet {

    private final UserDaoImplInMemory userDaoImplInMemory = new UserDaoImplInMemory();
    private final UserService userService = new UserService(userDaoImplInMemory);

    private boolean loggedIn = false;

    public Server() {
        createAdmin();
    }

    private void createAdmin() {
        User adminUser = new User(0, "Admin", "PSSrwd13@", "Admin@Admin.com");
        userDaoImplInMemory.addOrUpdate(adminUser);
    }

    private User userCreator(Integer id, String username, String password, String email) throws InvalidUserException {
        User user = new User(id, username, password, email);
        if (userService.isValidUser(user)) {
            return user;
        } else {
            throw new InvalidUserException("Cant create a user like that");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String parameters = request.getParameter("who");
        if (loggedIn) {
            if (parameters.equals("all")) {
                outputStream.println(""
                        + "<html><head><title>User</title></head>" + "<body>");
                for (User user : userDaoImplInMemory.getAll()) {
                    outputStream.println(""
                            + "ID: " + user.getId() + "<br>"
                            + "Username: " + user.getUsername() + "<br>"
                            + "Password: " + user.getPassword() + "<br>"
                            + "Email: " + user.getEmail() + "<br>");
                }
                outputStream.println("" + "</body>" + "</html>");
            } else if (parameters.matches("^[0-9]+$")) {
                Integer id = Integer.parseInt(parameters);
                try {
                    User user = userDaoImplInMemory.getById(id);
                    outputStream.println(""
                            + "<html><head><title>User</title></head>" + "<body>"
                            + "ID: " + user.getId() + "<br>"
                            + "Username: " + user.getUsername() + "<br>"
                            + "Password: " + user.getPassword() + "<br>"
                            + "Email: " + user.getEmail() + "<br>"
                            + "</body>"
                            + "</html>");
                } catch (NoUserException noUserException) {
                    outputStream.println(noUserException.getMessage());
                }
            } else {
                outputStream.println("You need to send a number or 'all' as a parameter");

            }
        } else {
            outputStream.println("You need to log in");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            if (userService.login(request.getParameter("username"), request.getParameter("password"))) {
                loggedIn = true;
                outputStream.println("Logged in");
            } else {
                outputStream.println("Wrong username/password");
            }
        } catch (NoUserException noUserException) {
            outputStream.println(noUserException.getMessage());
            outputStream.println("Make sure admin user is created");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String idParam = request.getParameter("id");
        if (loggedIn) {
            if (idParam.matches("^[0-9]+$")) {
                Integer id = Integer.parseInt(idParam);
                try {
                    User user = userCreator(id, request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
                    userDaoImplInMemory.addOrUpdate(user);
                    outputStream.println(""
                            + "<html><head><title>The following User has been added or updated</title></head>" + "<body>"
                            + "ID: " + user.getId() + "<br>"
                            + "Username: " + user.getUsername() + "<br>"
                            + "Password: " + user.getPassword() + "<br>"
                            + "Email: " + user.getEmail() + "<br>"
                            + "</body>"
                            + "</html>");
                } catch (InvalidUserException invalidUserException) {
                    outputStream.println(invalidUserException.getMessage());
                }
            } else {
                outputStream.println("You need to provide an ID as the first parameter");
            }
        } else {
            outputStream.println("You need to log in");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String idParam = request.getParameter("id");
        if (loggedIn) {
            if (idParam.matches("^[0-9]+$")) {
                Integer id = Integer.parseInt(idParam);
                try {
                    User user = userDaoImplInMemory.getById(id);
                    userDaoImplInMemory.delete(user);
                    outputStream.println(""
                            + "<html><head><title>The following User has been deleted</title></head>" + "<body>"
                            + "ID: " + user.getId() + "<br>"
                            + "Username: " + user.getUsername() + "<br>"
                            + "Password: " + user.getPassword() + "<br>"
                            + "Email: " + user.getEmail() + "<br>"
                            + "</body>"
                            + "</html>");
                } catch (NoUserException noUserException) {
                    outputStream.println(noUserException.getMessage());
                }
            } else {
                outputStream.println("You need to provide an ID as the first parameter");
            }
        } else {
            outputStream.println("You need to log in");
        }
    }
}
