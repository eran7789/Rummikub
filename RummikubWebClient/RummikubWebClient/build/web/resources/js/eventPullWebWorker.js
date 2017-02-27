/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 function getEvents(eventId) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var data;
            try {
                data = JSON.parse(xhttp.responseText);
                postMessage(data);
            } catch (e) {
            }
        }
    };
    xhttp.open("GET", "/RummikubWebClient/getEvents?eventId=" + eventId.toString(), true);
    xhttp.send();
}

self.onmessage = function (event) {
    getEvents(event.data);
};