function getAllTable(tableID) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById(tableID).innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "table", true);
    xhttp.send();
}

function addMotorbike() {
    var model = document.getElementById("model").value;
    var price = document.getElementById("price").value;
    var displacement = document.getElementById("displacement").value;
    var power = document.getElementById("power").value;
    var type = document.getElementById("type").value;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("tableID").innerHTML = this.responseText;
        }
        if (this.readyState === 4 && this.status === 400) {
            console.error("Error: " + this.responseText);
        }
    };

    xhttp.open("GET", "catalog?action=add&model=" + model + "&price=" + price + "&displacement=" + displacement + "&power=" + power + "&type=" + type, true);
    xhttp.send();
}

function editMotorbike(model) {
    var newModel = document.getElementById("model").value;
    var price = document.getElementById("price").value;
    var displacement = document.getElementById("displacement").value;
    var power = document.getElementById("power").value;
    var type = document.getElementById("type").value;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("tableID").innerHTML = this.responseText;
        }
        if (this.readyState === 4 && this.status === 400) {
            console.error("Error: " + this.responseText);
        }
    };

    xhttp.open("GET", "catalog?action=edit&oldModel=" + model + "&model=" + newModel + "&price=" + price + "&displacement=" + displacement + "&power=" + power + "&type=" + type, true);
    xhttp.send();
}

function deleteMotorbike(model) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("tableID").innerHTML = this.responseText;
        }
        if (this.readyState === 4 && this.status === 400) {
            console.error("Error: " + this.responseText);
        }
    };

    xhttp.open("GET", "catalog?action=delete&model=" + model, true);
    xhttp.send();
}

//function insertPerson(firstName, lastName, email, vip, gender, dateOfBirth, tableId, errorInfo) {
//    document.getElementById(errorInfo).innerHTML ="&nbsp;";
//    var xhttp = new XMLHttpRequest();
//    xhttp.onreadystatechange = function() {
//        
//        console.log(this);
//        if (this.readyState === 4 && this.status === 200) {
//            document.getElementById(tableId).innerHTML = this.responseText;
//        }
//        if (this.readyState === 4 && this.status === 400) {
//            document.getElementById(errorInfo).innerHTML = this.responseText;
//        }
//        
//    };
//  var ar1 = document.getElementById(firstName).value;
//  var ar2 = document.getElementById(lastName).value;
//  var ar3 = document.getElementById(email).value;
//  var ar4 = document.getElementById(vip).checked;
//  var ar5 = document.getElementById(gender).value;
//  var ar6 = document.getElementById(dateOfBirth).value;
//  
//  xhttp.open("GET", "peopleInsert?firstName=" + ar1 + "&lastName=" + ar2 + "&email=" + ar3 + "&vip=" + ar4 + "&gender=" + ar5 + "&dateOfBirth=" + ar6, true);
//  xhttp.send();
//}
//function updatePerson(personId, firstName, lastName, email, vip, gender, dateOfBirth, tableId, errorInfo) {
//    document.getElementById(errorInfo).innerHTML ="&nbsp;";
//    var xhttp = new XMLHttpRequest();
//    xhttp.onreadystatechange = function() {
//        if (this.readyState === 4 && this.status === 200) {
//            document.getElementById(tableId).innerHTML = this.responseText;
//        }
//        if (this.readyState === 4 && this.status === 400) {
//            document.getElementById(errorInfo).innerHTML = this.responseText;
//        }
//    };
//  var ar1 = document.getElementById(firstName).value;
//  var ar2 = document.getElementById(lastName).value;
//  var ar3 = document.getElementById(email).value;
//  var ar4 = document.getElementById(vip).checked;
//  var ar5 = document.getElementById(gender).value;
//  var ar6 = document.getElementById(dateOfBirth).value;
//  
//  xhttp.open("GET", "peopleUpdate?personId="+personId+"&firstName=" + ar1 + "&lastName=" + ar2 + "&email=" + ar3 + "&vip=" + ar4 + "&gender=" + ar5 + "&dateOfBirth=" + ar6, true);
//  xhttp.send();
//}
//
//function deletePerson(personId, tableId, errorInfo) {
//    document.getElementById(errorInfo).innerHTML ="&nbsp;";
//    var xhttp = new XMLHttpRequest();
//    xhttp.onreadystatechange = function() {
//        if (this.readyState === 4 && this.status === 200) {
//            document.getElementById(tableId).innerHTML = this.responseText;
//        }
//        if (this.readyState === 4 && this.status === 400) {
//            document.getElementById(errorInfo).innerHTML = this.responseText;
//        }
//    };
//  
//  xhttp.open("GET", "peopleDelete?id=" + personId, true);
//  xhttp.send();
//}