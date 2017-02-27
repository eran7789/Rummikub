/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getMyBoard() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            postMessage(JSON.parse(xhttp.responseText));
            self.close();
        }
    };
    xhttp.open("GET", "/RummikubWebClient/getMyBoard", true);
    xhttp.send();
}

self.onmessage = function(event) {
    getMyBoard();
};