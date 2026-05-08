package services;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AppException;
import model.Motorbike;
import model.MotorbikeType;
import model.Singleton;

/**
 * Servlet responsible for handling the addition, editing, and deletion of motorbikes in the catalog.
 * Handles HTTP GET and POST requests to update the catalog and respond with a result.
 * 
 * @author Kamil Kotorc
 * @version 5.0
 */
@WebServlet(name = "CatalogTableServlet", urlPatterns = {"/CatalogTableServlet"})
public class CatalogTableServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP GET and POST methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CatalogTableServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CatalogTableServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Dodawanie nowego rekordu
            String model = request.getParameter("model");
            double price = Double.parseDouble(request.getParameter("price"));
            int displacement = Integer.parseInt(request.getParameter("displacement"));
            int power = Integer.parseInt(request.getParameter("power"));
            MotorbikeType type = MotorbikeType.fromString(request.getParameter("type"));

            Motorbike newMotorbike = new Motorbike(model, price, displacement, power, type);
            
            Singleton.getInstance().addMotorbike(newMotorbike);
            
        } else if ("edit".equals(action)) {
            // Edycja rekordu
            String oldModel = request.getParameter("oldModel");
            Motorbike oldMotorbike = Singleton.getInstance().getMotorbikeList().stream()
                    .filter(m -> m.model().equals(oldModel))
                    .findFirst()
                    .orElse(null);

            if (oldMotorbike != null) {
                String newModel = request.getParameter("model");
                double newPrice = Double.parseDouble(request.getParameter("price"));
                int newDisplacement = Integer.parseInt(request.getParameter("displacement"));
                int newPower = Integer.parseInt(request.getParameter("power"));
                MotorbikeType newType = MotorbikeType.fromString(request.getParameter("type"));

                Motorbike updatedMotorbike = new Motorbike(newModel, newPrice, newDisplacement, newPower, newType);
                
                Singleton.getInstance().editMotorbike(oldMotorbike, updatedMotorbike);
                
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Motorbike not found");
            }
        } else if ("delete".equals(action)) {
            // Usuwanie rekordu
            String model = request.getParameter("model");
            Motorbike motorbikeToRemove = Singleton.getInstance().getMotorbikeList().stream()
                    .filter(m -> m.model().equals(model))
                    .findFirst()
                    .orElse(null);

            if (motorbikeToRemove != null) {
                
                Singleton.getInstance().removeMotorbike(motorbikeToRemove);
                
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Motorbike not found");
            }
        }

        // Po każdej operacji przeładuj tabelę
        response.sendRedirect("table");
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
