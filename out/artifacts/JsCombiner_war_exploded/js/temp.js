$(document).ready(function () {
    var scrWidth, scrHeight, browsWidth, browsHeight, imgMarginVer, imgMarginHon, imgWidth, imgHeight;
    $("head").append('');
    $("body").append("");
    $("#outerLine").css("display", "block");
    $("#closedBtn").click(function () {
        $("#outerLine").css("display", "none");
    });
    var boundWidth = 900 + 0.002;
    var boundHeight = 400 + 0.002;
    var propWH = 900 / 400;
    scrWidth = screen.width;
    browsWidth = document.documentElement.clientWidth;
    scrHeight = screen.height;
    browsHeight = document.documentElement.clientHeight;
    if (browsWidth >= boundWidth) {
        if (browsHeight >= boundHeight) {
            imgWidth = 900;
            imgHeight = 400;
            imgMarginVer = (browsWidth - imgWidth) / 2;
            imgMarginHon = (browsHeight - imgHeight) / 2;
        } else {
            imgHeight = browsHeight;
            imgWidth = propWH * imgHeight;
            imgMarginVer = (browsWidth - imgWidth) / 2;
            imgMarginHon = 0;
        }
    } else {
        if (browsHeight >= boundHeight) {
            imgWidth = browsWidth;
            imgHeight = imgWidth / propWH;
            imgMarginHon = (browsHeight - imgHeight) / 2;
            imgMarginVer = 0;
        } else {
            imgWidth = browsWidth;
            imgHeight = browsHeight;
            imgMarginVer = 0;
            imgMarginHon = 0;
        }
    }
    $("#imgContent").css("margin-left", imgMarginVer);
    $("#imgContent").css("margin-top", imgMarginHon);
    $("#imgContent").css("width", imgWidth);
    $("#imgContent").css("height", imgHeight);
    window.addEventListener("resize", function () {
        browsWidth = document.documentElement.clientWidth;
        browsHeight = document.documentElement.clientHeight;
        if (browsWidth >= boundWidth) {
            if (browsHeight >= boundHeight) {
                imgWidth = 900;
                imgHeight = 400;
                imgMarginVer = (browsWidth - imgWidth) / 2;
                imgMarginHon = (browsHeight - imgHeight) / 2;
            } else {
                imgHeight = browsHeight;
                imgWidth = propWH * imgHeight;
                imgMarginVer = (browsWidth - imgWidth) / 2;
                imgMarginHon = 0;
            }
        } else {
            if (browsHeight >= boundHeight) {
                imgWidth = browsWidth;
                imgHeight = imgWidth / propWH;
                imgMarginHon = (browsHeight - imgHeight) / 2;
                imgMarginVer = 0;
            } else {
                imgWidth = browsWidth;
                imgHeight = browsHeight;
                imgMarginVer = 0;
                imgMarginHon = 0;
            }
        }
        $("#imgContent").css("margin-left", imgMarginVer);
        $("#imgContent").css("margin-top", imgMarginHon);
        $("#imgContent").css("width", imgWidth);
        $("#imgContent").css("height", imgHeight);
    });
});