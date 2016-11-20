package Presentation;

import dataaccess.UserMapper;
import domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import static java.util.Objects.isNull;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Iwan
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        UserMapper um = new UserMapper();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String brugernavn = request.getParameter("brugernavn");
        String adgangskode = request.getParameter("adgangskode");
        boolean isAuthenticated = um.authenticateUser(username, password);
        
        if(!isNull(username) && !isNull(password)){
        
            if(isAuthenticated){            
                try (PrintWriter out = response.getWriter()) {

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Databaseforespørgsel</title>");
                    out.println("<link href=\"css/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\" />");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div id=\"login\">Logget ind som: " +username+ "</div>");
                    out.println("<h1>Du er nu logget ind</h1>");
                    out.println("<h1>Her følger en liste over brugerne i databasen:</h1>");                    
                    List<User> allUsers = um.getAllUsers();
                    out.println("<table id=\"table\">");
                    out.println("<th>Bruger-ID:</th>");
                    out.println("<th>Brugernavn:</th>");
                    for (User U : allUsers) {                
                        out.println("<tr><td>" + U.getId() + "</td><td>" +U.getUserName()+"</td></tr>");                            
                    }
                    out.println("</table>");  
                }          
            }
            else {
                try (PrintWriter out = response.getWriter()){
                    out.println("<h1>Du kunne ikke logges ind!</h1>");
                }
        }
        try (PrintWriter out = response.getWriter()){
            out.println("</body>");
            out.println("</html>");
        }
        }
        else {
            try (PrintWriter out = response.getWriter()) {
                um.insertUserIntoDatabase(brugernavn, adgangskode);
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<link href=\"css/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\" />");
                out.println("<title>Tilmelding</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Du er nu tilmeldt med de følgende oplysninger:</h1>");
                out.println("<p>Brugernavn: " +brugernavn+ ". Adgangskode: "+adgangskode+ "</p>");                
                out.println("</body>");
                out.println("</html>");
        }
        
        }
    }
    
        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo() {
            return "Short description";
        }// </editor-fold>
        
    }

    

    

    


