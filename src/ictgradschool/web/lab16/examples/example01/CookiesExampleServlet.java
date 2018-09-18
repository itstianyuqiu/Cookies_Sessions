package ictgradschool.web.lab16.examples.example01;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Really basic servlet which creates cookies
 */
public class CookiesExampleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        // Header stuff
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Web Lab 16 Examples - Cookies</title>");
        out.println("</head>");
        out.println("<body>");

        //TODO - if attributes exist, add the appropriate first name, last name, city and country
        out.println("Welcome FNAME LNAME from CITY, COUNTRY");

        out.println("<a href=\"index.html\">HOME</a><br>");

        // Print out a form allowing users to enter new cookies
        out.println("<form action=\"Cookies\" method=\"GET\">");
        out.println("<p>Create a new cookie:</p>");
        out.println("<label for=\"inputCookieName\">New cookie name:</label>");
        out.println("<input type=\"text\" id=\"inputCookieName\" name=\"cookieName\" />");
        out.println("<br>");
        out.println("<label for=\"inputCookieValue\">New cookie value:</label>");
        out.println("<input type=\"text\" id=\"inputCookieValue\" name=\"cookieValue\" />");
        out.println("<br>");
        out.println("<input type=\"hidden\" name=\"option\" value=\"create\" />");
        out.println("<input type=\"submit\"/>");
        out.println("</form>");

        // Print out a form allowing users to see the values of existing cookies
        out.println("<form action=\"Cookies\" method=\"GET\">");
        out.println("<p>Read an existing cookie:</p>");
        out.println("<label for=\"readCookieName\">Existing cookie name:</label>");
        out.println("<input type=\"text\" id=\"readCookieName\" name=\"cookieName\" />");
        out.println("<br>");
        out.println("<input type=\"hidden\" name=\"option\" value=\"read\" />");
        out.println("<input type=\"submit\"/>");
        out.println("</form>");

        String option = request.getParameter("option");

        // If the user wants to create a cookie, do that now and output the result
        if (option != null && option.equals("create")) {

            // Get the values from the form
            String cookieName = request.getParameter("cookieName");
            String cookieValue = request.getParameter("cookieValue");

            System.out.println("Values from user: (name=" + cookieName + ", value=" + cookieValue + ")");

            // Create a cookie with those values and add it to the response so it is stored on the client
            // IMPORTANT: URLEncoder.encode will allow "special characters" such as spaces to be stored in cookies. Otherwise such
            // characters couldn't be used!
            Cookie cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, "UTF-8"));
            response.addCookie(cookie);

            // Print out the result to the output
            out.println("<p>Created new cookie:</p>");
            out.println("<p><strong>Name:</strong> <em>" + cookie.getName() + "</em></p>");
            // The opposite of URLEncoder.encode
            out.println("<p><strong>Value:</strong> <em>" + URLDecoder.decode(cookie.getValue(), "UTF-8") + "</em></p>");

            // This line prints to the server's console, not the client.
            System.out.println("Created cookie (name=" + cookie.getName() + ", value=" + cookie.getValue() + ")");
        }

        // If the user wants to read a cookie, do that now and output the result
        else if (option != null && option.equals("read")) {

            // Get the values from the form
            String cookieName = request.getParameter("cookieName");

            // Read the cookie
            Cookie[] cookies = request.getCookies();
            Cookie cookie = null;
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals(cookieName))   {
                        cookie = cookies[i];
                    }
                }
            }

            // Print out the result
            if (cookie != null) {

                System.out.println("Read cookie (name=" + cookie.getName() + ", value=" + cookie.getValue() + ")");

                // The opposite of URLEncoder.encode.
                String decodedValue = URLDecoder.decode(cookie.getValue(), "UTF-8");

                out.println("<p>Found existing cookie:</p>");
                out.println("<p><strong>Name:</strong> <em>" + cookie.getName() + "</em></p>");
                out.println("<p><strong>Value:</strong> <em>" + decodedValue + "</em></p>");
            }

            else {

                System.out.println("Cookie named " + cookieName + " not found.");

                out.println("<p>Couldn't find a cookie named <em>" + cookieName + "</em> :(</p>");
            }

        }

        // Footer stuff
        out.println("</body>");
        out.println("</html>");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
