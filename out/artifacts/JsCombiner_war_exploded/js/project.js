window.onload = function(){
    function clickA(){
        var display = document.getElementById("display").style.display ="inline-block";  
        var weight = document.getElementById("weight").style.display ="inline-block";  
    }
    function clickB(){
        var display = document.getElementById("display").style.display ="none";  
        var weight = document.getElementById("weight").style.display ="none";  
    }
    
    
    var bread_1 = document.getElementById("bread_1");
    var bread_2 = document.getElementById("bread_2");

    bread_1.addEventListener("click",function(){
        bread_1.style.display = "none";
        bread_2.style.display = "block";
        var center1 = document.getElementById('mask1').style.display ="none";
        var center2 = document.getElementById('mask2').style.display ="block";
        var name = document.getElementById('name').style.display ="none";
        var status = document.getElementById('status').style.display ="none";
        var size = document.getElementById('size').style.display ="none";
    });
    bread_2.addEventListener("click",function(){
        bread_1.style.display = "block";
        bread_2.style.display = "none";
        var center1 = document.getElementById('mask1').style.display ="block";
        var center2 = document.getElementById('mask2').style.display ="none";
        var name = document.getElementById('name').style.display ="block";
        var status = document.getElementById('status').style.display ="block";
        var size = document.getElementById('size').style.display ="block";
    });

    
}