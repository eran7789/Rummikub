/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import engine.Game;

/**
 *
 * @author Eran Keren
 */
public interface Controller {
    
    // set a relationship between controllers to the main app
    public void setApp(RummikubApp app);
    public void initGame();
}
