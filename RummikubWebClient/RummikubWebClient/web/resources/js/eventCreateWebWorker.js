/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

self.onmessage = function (event) {
    switch (event.data.cmd) {
        case "finishTurn":
            finishTurn(event);
            break;
        case "addTile":
            addTile(event);
            break;
        case "moveTile":
            moveTile(event);
            break;
        case "takeBackTile":
            takeBackTile(event);
            break;
        case "createSequence":
            createSequence(event);
            break;
    }
};

function finishTurn(event) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/RummikubWebClient/FinishTurn", true);
    xhttp.send();
}

function addTile(event) {
    var xhttp = new XMLHttpRequest();
    var url = "/RummikubWebClient/AddTile?";
    url += "sequenceIndex=";
    url += event.data.targetSeqIndex;
    url += "&sequencePos=";
    url += event.data.targetSeqPos;
    url += "&tile=";
    url += JSON.stringify(event.data.tile);
    xhttp.open("GET", url, true);
    xhttp.send();
}

function moveTile(event) {
    var xhttp = new XMLHttpRequest();
    var url = "/RummikubWebClient/AddTile?";
    url += "sequenceIndex=";
    url += event.data.sourceSeqIndex;
    url += "&sequencePos=";
    url += event.data.sourceSeqPos;
    url += "&SourcePos=";
    url += event.data.targetSeqIndex;
    url += "&SourceIndex=";
    url += event.data.targetSeqPos;
    xhttp.open("GET", url, true);
    xhttp.send();
}

function takeBackTile(event) {
    var xhttp = new XMLHttpRequest();
    var url = "/RummikubWebClient/TakeBackTile?";
    url += "sequenceIndex=";
    url += event.data.targetSeqIndex;
    url += "&sequencePos=";
    url += event.data.targetSeqPos;
    xhttp.open("GET", url, true);
    xhttp.send();
}

function createSequence(event) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/RummikubWebClient/CreateSequence", true);
    xhttp.send();
}