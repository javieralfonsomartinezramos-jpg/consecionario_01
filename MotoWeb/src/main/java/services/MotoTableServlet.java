package services;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Motorbike;
import model.Singleton;

/**
 * Servlet for displaying motorbike data as an HTML table.
 * This servlet queries the singleton catalog and generates an HTML table.
 * 
 * @author Kamil Kotorc
 * @version 5.0
 */
@WebServlet("/table")
public class MotoTableServlet extends HttpServlet {
    
    /**
     * Handles HTTP GET requests by outputting an HTML table displaying motorbike details.
     *
     * @param request the servlet request containing parameters
     * @param response the servlet response for sending the result
     * @throws ServletException if an error occurs in the servlet
     * @throws IOException if an I/O error occurs during processing
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        for (Motorbike motorbike : Singleton.getInstance().getMotorbikeList()) {
            out.println("<tr>");
            out.println("<td>" + motorbike.model() + "</td>");
            out.println("<td>" + motorbike.price() + "</td>");
            out.println("<td>" + motorbike.displacement() + "</td>");
            out.println("<td>" + motorbike.power() + "</td>");
            out.println("<td>" + motorbike.type() + "</td>");
            out.println("<td>");
            out.println("<button onclick=\"editMotorbike('" + motorbike.model() + "')\">Edit</button>");
            out.println("<button onclick=\"deleteMotorbike('" + motorbike.model() + "')\">Delete</button>");
            out.println("</td>");
            out.println("</tr>");
        }
    }
}

