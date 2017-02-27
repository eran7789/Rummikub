/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.web.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ws.rummikub.RummikubWebService;
import ws.rummikub.RummikubWebServiceService;


@WebServlet(name = "getWaitingGames", urlPatterns = {"/getWaitingGames"})
public class getWaitingGames extends HttpServlet {

    private RummikubWebService clientService;
    private RummikubWebServiceService service;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        URL url = new URL("http://LocalHost:8080/RummikubWeb/RummikubWebServiceService" );
        service = new RummikubWebServiceService(url);
        clientService = service.getRummikubWebServicePort();
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        ArrayList<String> games  = (ArrayList) clientService.getWaitingGames();

        String json = new Gson().toJson(games);
        out.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
