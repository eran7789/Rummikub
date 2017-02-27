/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getGameInfo() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var json;
            try {
                postMessage(JSON.parse(xhttp.responseText));
            } catch (e) {}
            self.close();
        }
    };
    xhttp.open("GET", "/RummikubWebClient/getGameInfo", true);
    xhttp.send();
}

self.onmessage = function(event) {
    getGameInfo();
};