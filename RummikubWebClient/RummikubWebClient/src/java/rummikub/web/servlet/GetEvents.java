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
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ws.rummikub.DuplicateGameName_Exception;
import ws.rummikub.Event;
import ws.rummikub.GameDetails;
import ws.rummikub.GameDoesNotExists_Exception;
import ws.rummikub.GameStatus;
import ws.rummikub.InvalidParameters_Exception;
import ws.rummikub.RummikubWebService;
import ws.rummikub.RummikubWebServiceService;

/**
 *
 * @author Eran Keren
 */
@WebServlet (name="getEvents", urlPatterns={"/getEvents"})
public class GetEvents extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession(false);
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        
        Integer playerID = (Integer) session.getAttribute(AttributeNames.PLAYER_ID_ATT);
        Integer eventId = Integer.parseInt(req.getParameter("eventId"));
        
        URL url = new URL("http://localhost:8080//RummikubWeb/RummikubWebServiceService");
        RummikubWebServiceService service = new RummikubWebServiceService(url);
        RummikubWebService clientService = service.getRummikubWebServicePort();
        
        try {
            List<Event> events = clientService.getEvents(playerID, eventId);
            String resJson = new Gson().toJson(events);
            out.println(resJson);
        } catch (InvalidParameters_Exception ex) {
            out.println("Game Name Does Not Exists!");
        }
    }
}
