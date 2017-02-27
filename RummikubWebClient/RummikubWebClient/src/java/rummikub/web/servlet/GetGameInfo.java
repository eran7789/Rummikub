/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.web.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import ws.rummikub.GameDetails;
import ws.rummikub.GameDoesNotExists_Exception;
import ws.rummikub.GameStatus;
import ws.rummikub.RummikubWebService;
import ws.rummikub.RummikubWebServiceService;

/**
 *
 * @author Eran Keren
 */
@WebServlet (name="getGameInfo", urlPatterns={"/getGameInfo"})
public class GetGameInfo extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession(false);
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        
        Integer playerID = (Integer) session.getAttribute(AttributeNames.PLAYER_ID_ATT);
        String gameName = (String) session.getAttribute(AttributeNames.GAME_NAME_ATT);
        String playerName = (String) session.getAttribute(AttributeNames.PLAYER_NAME_ATT);
        
        URL url = new URL("http://localhost:8080//RummikubWeb/RummikubWebServiceService");
        RummikubWebServiceService service = new RummikubWebServiceService(url);
        RummikubWebService clientService = service.getRummikubWebServicePort();
        
        try {
            GameDetails details = clientService.getGameDetails(gameName);
            GameStatus status = details.getStatus();
            JsonObject json = new JsonObject();
            json.addProperty("gameName", gameName);
            json.addProperty("status", status.name());
            json.addProperty("playerName", playerName);
            json.addProperty("numOfComputerPlayers", details.getComputerizedPlayers());
            json.addProperty("numOfHumanPlayers", details.getHumanPlayers());
            json.addProperty("numOfJoinedHumanPlayers", details.getJoinedHumanPlayers());
            Gson gson = new GsonBuilder().create();
            out.println(gson.toJson(json));
        } catch (GameDoesNotExists_Exception ex) {
            out.println("Game Name Does Not Exists!");
        }
    }
}
