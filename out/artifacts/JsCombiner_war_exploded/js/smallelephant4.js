/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function () {

    var pixvalue;
    var screeninch;
    var proweight;
    var copystyle;
    var country;

    var pixelcontrol = document.getElementById("pixelsel");
    var stylecontrol = document.getElementById("stylesel");
    var weightcontrol = document.getElementById("textweight");
    var inchcontrol = document.getElementById("textinch");
    var countrycontrol = document.getElementById("country");

    pixelcontrol.addEventListener("change", function () {
        refresh();
    });

    stylecontrol.addEventListener("change", function () {
        refresh();
    });

    weightcontrol.addEventListener("change", function () {
        refresh();
    });

    inchcontrol.addEventListener("change", function () {
        refresh();
    });

    countrycontrol.addEventListener("change", function () {
        refresh();
    });

    function refresh() {
        pixvalue = pixelcontrol.value;
        copystyle = stylecontrol.value;
        proweight = weightcontrol.value;
        screeninch = inchcontrol.value;
        country = countrycontrol.value;
    }

    var pro_div = document.getElementById("draginto");
    pro_div.addEventListener("dragenter", function (e) {
        e.stopPropagation();
        e.preventDefault();
    });
    pro_div.addEventListener("dragover", function (e) {
        e.stopPropagation();
        e.preventDefault();
    });
    pro_div.addEventListener("drop", function (e) {
        e.stopPropagation();
        e.preventDefault();

        var img_form = new FormData();
        var files = e.dataTransfer.files;

        for (var i = 0; i < files.length; i++) {
            img_form.append("file", files[i]);
        }

        img_form.append("pixvalue", pixvalue);
        img_form.append("screeninch", screeninch);
        img_form.append("proweight", proweight);
        img_form.append("copystyle", copystyle);
        img_form.append("country", country);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "elephant", true);
        xhr.onreadystatechange = function (e) {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var downloadList = document.getElementById("download");
                var responseJSON = xhr.responseText;
                var js = JSON.parse(responseJSON);
                for (event in js){
                    var p = document.createElement("p");
                    downloadList.appendChild(p);
                    p.innerHTML = "<a download='"+event+".png' href='"+js[event]+"'>"+event+"</a>";
                }
            }
        };
        xhr.send(img_form);

    });

    pro_div.addEventListener("dragleave", function (e) {
        e.stopPropagation();
        e.preventDefault();
    });

}



