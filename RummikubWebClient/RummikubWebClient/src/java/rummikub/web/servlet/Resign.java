/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ws.rummikub.InvalidParameters_Exception;
import ws.rummikub.RummikubWebService;
import ws.rummikub.RummikubWebServiceService;

/**
 *
 * @author assafyehudai
 */
@WebServlet(name = "Resign", urlPatterns = {"/Resign"})
public class Resign extends HttpServlet {

    private RummikubWebService clientService;
    private RummikubWebServiceService service;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60*30);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        
        URL url = new URL("http://LocalHost:8080/RummikubWeb/RummikubWebServiceService" );
        service = new RummikubWebServiceService(url);
        clientService = service.getRummikubWebServicePort();
        
        int playerId = (Integer) session.getAttribute(AttributeNames.PLAYER_ID_ATT);
        try {
            clientService.resign(playerId);
        } catch (InvalidParameters_Exception ex) {
            out.println(" invalid parameter Exception! ");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
