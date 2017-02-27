package rummikub.web.servlet;


import com.google.gson.*;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ws.rummikub.DuplicateGameName_Exception;
import ws.rummikub.RummikubWebService;
import ws.rummikub.RummikubWebServiceService;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eran Keren
 */
@WebServlet (name="createGame", urlPatterns={"/createGame"})
public class CreateGame extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(60*30);
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        int numOfComputerPlayers = Integer.parseInt(req.getParameter("numOfComputerPlayers"));
        int numOfHumanPlayers = Integer.parseInt(req.getParameter("numOfHumanPlayers"));
        String gameName = req.getParameter("gameName");
        String playerName = req.getParameter("playerName");
        
        URL url = new URL("http://localhost:8080//RummikubWeb/RummikubWebServiceService");
        RummikubWebServiceService service = new RummikubWebServiceService(url);
        RummikubWebService clientService = service.getRummikubWebServicePort();
        
        int playerID;
        
        try {
            clientService.createGame(gameName, numOfHumanPlayers, numOfComputerPlayers);
            playerID = clientService.joinGame(gameName, playerName);
            out.println("playerID: " + playerID);
            System.out.println(session.getId()); 
           session.setAttribute(AttributeNames.PLAYER_ID_ATT, playerID);
            session.setAttribute(AttributeNames.PLAYER_NAME_ATT, playerName);
            session.setAttribute(AttributeNames.GAME_NAME_ATT, gameName);
        } catch (DuplicateGameName_Exception ex) {
            out.println("Game Name Already Exists!");
        } catch (Exception ex) {
            out.println("Server Failure!");
        }
    }
}
