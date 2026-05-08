package services;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import static java.lang.System.console;
import model.Motorbike;
import model.MotorbikeType;
import model.Singleton;
import model.AppException;
/**
 * Servlet responsible for handling catalog actions such as adding, editing, and deleting motorbikes.
 * The actions are determined by the 'action' parameter in the HTTP request.
 *
 * @author Kamil Kotorc
 * @version 5.0
 */
@WebServlet("/catalog")
public class CatalogActionServlet extends HttpServlet {
    
    /**
     * Handles HTTP GET requests.
     * Depending on the action parameter, it triggers the corresponding catalog action (add, edit, delete).
     *
     * @param request the HTTP request containing parameters for the action
     * @param response the HTTP response to send back the result
     * @throws ServletException if an error occurs in the servlet
     * @throws IOException if an I/O error occurs during processing
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
            return;
        }

        try {
            switch (action) {
                case "add":
                    addMotorbike(request);
                    break;
                case "edit":
                    editMotorbike(request);
                    break;
                case "delete":
                    deleteMotorbike(request);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                    return;
            }

            response.setContentType("text/html");
            response.getWriter().write(generateTableHtml());
        } catch (AppException | NumberFormatException e) {
            response.setContentType("text/html");
            response.getWriter().write(generateTableHtml());
            response.getWriter().write(generateError(e.getMessage()));
            //response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
    
    /**
     * Adds a new motorbike to the catalog based on the HTTP request parameters.
     *
     * @param request the HTTP request containing the motorbike details
     * @throws AppException if an error occurs while adding the motorbike
     */
    private void addMotorbike(HttpServletRequest request) throws AppException {
        String model = request.getParameter("model");
        double price = Double.parseDouble(request.getParameter("price"));
        int displacement = Integer.parseInt(request.getParameter("displacement"));
        int power = Integer.parseInt(request.getParameter("power"));
        MotorbikeType type = MotorbikeType.fromString(request.getParameter("type"));
        
        Singleton.getInstance().addMotorbike(new Motorbike(model, price, displacement, power, type));

    }
    
    /**
     * Edits an existing motorbike in the catalog based on the HTTP request parameters.
     * The motorbike to edit is identified by its 'oldModel'.
     *
     * @param request the HTTP request containing the updated motorbike details
     * @throws AppException if an error occurs while editing the motorbike
     */
    private void editMotorbike(HttpServletRequest request) throws AppException {
        String oldModel = request.getParameter("oldModel");
        String newModel = request.getParameter("model");
        double price = Double.parseDouble(request.getParameter("price"));
        int displacement = Integer.parseInt(request.getParameter("displacement"));
        int power = Integer.parseInt(request.getParameter("power"));
        MotorbikeType type = MotorbikeType.fromString(request.getParameter("type"));

        Motorbike oldMotorbike = Singleton.getInstance().getMotorbikeList().stream()
                .filter(m -> m.model().equals(oldModel))
                .findFirst()
                .orElseThrow(() -> new AppException("Motorbike not found"));

        Singleton.getInstance().editMotorbike(oldMotorbike, new Motorbike(newModel, price, displacement, power, type));
    }
    
    /**
     * Deletes a motorbike from the catalog based on the HTTP request parameter.
     *
     * @param request the HTTP request containing the motorbike model to delete
     * @throws AppException if an error occurs while deleting the motorbike
     */
    private void deleteMotorbike(HttpServletRequest request) throws AppException {
        String model = request.getParameter("model");
        Motorbike motorbike = Singleton.getInstance().getMotorbikeList().stream()
                .filter(m -> m.model().equals(model))
                .findFirst()
                .orElseThrow(() -> new AppException("Motorbike not found"));

        Singleton.getInstance().removeMotorbike(motorbike);
    }
    
    /**
     * Generates the HTML for a table representing all motorbikes in the catalog.
     * Each row includes buttons for editing and deleting a motorbike.
     *
     * @return the HTML table as a string
     */
    private String generateTableHtml() {
        StringBuilder tableHtml = new StringBuilder();
        for (Motorbike bike : Singleton.getInstance().getMotorbikeList()) {
            tableHtml.append("<tr>")
                     .append("<td>").append(bike.model()).append("</td>")
                     .append("<td>").append(bike.price()).append("</td>")
                     .append("<td>").append(bike.displacement()).append("</td>")
                     .append("<td>").append(bike.power()).append("</td>")
                     .append("<td>").append(bike.type()).append("</td>")
                     .append("<td>")
                     .append("<button onclick=\"editMotorbike('").append(bike.model()).append("')\">Edit</button>")
                     .append("<button onclick=\"deleteMotorbike('").append(bike.model()).append("')\">Delete</button>")
                     .append("</td>")
                     .append("</tr>");
        }
        return tableHtml.toString();
    }
    
    private String generateError(String errorMessage) {
        StringBuilder errorHtml = new StringBuilder();
        
        errorHtml.append("<div id=\"errorInfo\" class=\"errorInfo\">");
        errorHtml.append("Error: ").append(errorMessage);
        errorHtml.append("</div>");
        
        return errorHtml.toString();
    }

}
