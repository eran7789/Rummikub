/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ws.rummikub.GameDoesNotExists_Exception;
import ws.rummikub.InvalidParameters_Exception;
import ws.rummikub.RummikubWebService;
import ws.rummikub.RummikubWebServiceService;


@WebServlet(name = "joinGame", urlPatterns = {"/joinGame"})
public class joinGame extends HttpServlet {

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
                
        String gameName = request.getParameter("gameName");
        String playerName = request.getParameter("playerName");
        
        try {
            int playerID = clientService.joinGame(gameName, playerName);
            session.setAttribute(AttributeNames.PLAYER_ID_ATT, playerID);
            session.setAttribute(AttributeNames.PLAYER_NAME_ATT, playerName);
            session.setAttribute(AttributeNames.GAME_NAME_ATT, gameName);
        } catch (GameDoesNotExists_Exception ex) {
            out.println("Game does not Exists!");
        } catch (InvalidParameters_Exception ex) {
            out.println("Serever failure!");
        }
    
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
