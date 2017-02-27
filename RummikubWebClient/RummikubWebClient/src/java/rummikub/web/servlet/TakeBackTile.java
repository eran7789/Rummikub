package rummikub.web.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/TakeBackTile"})
public class TakeBackTile extends HttpServlet {

    private RummikubWebService clientService;
    private RummikubWebServiceService service;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
         HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60*30);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        
        URL url = new URL("http://LocalHost:8080/RummikubWeb/RummikubWebServiceService" );
        service = new RummikubWebServiceService(url);
        clientService = service.getRummikubWebServicePort();
        
        int playerId = (Integer) session.getAttribute(AttributeNames.PLAYER_ID_ATT);
        int sequenceIndex = Integer.parseInt(request.getParameter("sequenceIndex"));
        int SequencePos = Integer.parseInt(request.getParameter("sequencePos"));
        
        try {
            clientService.takeBackTile(playerId, sequenceIndex, SequencePos);
        } catch (InvalidParameters_Exception ex) {
            out.println(" some thing!  ****************** TO CHANGE *****************");
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
