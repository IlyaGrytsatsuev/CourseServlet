
var cur_page ;
var pages_num ;


function AddToCart(num, price, amount){
    var xhr = new XMLHttpRequest();
    //var item = document.getElementsByClassName('product-wrapper');

    var img = document.getElementById(num + "Image");
    var info = document.getElementById(num + "Info");
    var title = document.getElementById(num + "Title");
    var button1 = document.getElementById(num + "Button1");
    var button2 = document.getElementById(num + "Button2");

    var data = "num=" + num + "&state=add";


    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            if(amount - 1 != 0)
                info.innerHTML = "Price: " + price + " Available: " + (amount-1) ;
             else{
                img.remove();
                title.remove();
                info.remove();
                button1.remove();
                button2.remove();
                item.remove();
             }
        }
    }

    xhr.open("POST", "/CourseServlet/Items", true);

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");


    xhr.send(data);

        
}

function Delete(num){
    var xhr = new XMLHttpRequest();
    //var img = document.getElementsById("pro");
    var img = document.getElementById(num + "Image");
    var title = document.getElementById(num + "Title");
    var info = document.getElementById(num + "Info");
    var data = "num=" + num + "&state=delete";
    var button1 = document.getElementById(num + "Button1");
    var button2 = document.getElementById(num + "Button2");


    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            img.remove();
            title.remove();
            info.remove();
            button1.remove();
            button2.remove();
        }
    }

    xhr.open("POST", "/CourseServlet/Items", true);

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");


    xhr.send(data);

        
}


function DeleteFromCart(num){
    var xhr = new XMLHttpRequest();
    //var img = document.getElementsById("pro");
    var img = document.getElementById(num + "Image");
    var title = document.getElementById(num + "Title");
    var info = document.getElementById(num + "Info");
    var data = "num=" + num + "&state=delete";
    var button1 = document.getElementById(num + "Button1");
    var button2 = document.getElementById(num + "Button2");



    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            img.remove();
            title.remove();
            info.remove();
            button1.remove();
            button2.remove();
        }
    }

    xhr.open("POST", "/CourseServlet/Cart", true);

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");


    xhr.send(data);

        
}

function Buy(num){
    var xhr = new XMLHttpRequest();
    //var img = document.getElementsById("pro");
    var img = document.getElementById(num + "Image");
    var title = document.getElementById(num + "Title");
    var info = document.getElementById(num + "Info");
    var data = "num=" + num + "&state=buy";
    var button1 = document.getElementById(num + "Button1");
    var button2 = document.getElementById(num + "Button2");



    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            img.remove();
            title.remove();
            info.remove();
            button1.remove();
            button2.remove();
        }
    }

    xhr.open("POST", "/CourseServlet/Cart", true);

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");


    xhr.send(data);

        
}

function ShowFirst(num){
    
    pages_num = num;
    cur_page = 1;

    for( var i = 2; i < num+1; i++ ){
        var p = document.getElementById(i);
        p.style.display = 'none';
    }

}

function Next(){
    var page = document.getElementById(cur_page);
    page.style.display = 'none';
    cur_page += 1;

    page = document.getElementById(cur_page);
    page.style.display = 'block';

    ShowNavButtons();
}

function Prev(){
    var page = document.getElementById(cur_page);
    page.style.display = 'none';
    cur_page -= 1;

    page = document.getElementById(cur_page);
    page.style.display = 'block';

    ShowNavButtons();
    
}

function ShowNavButtons(){
    var Pbutton = document.getElementById("PButton");
    var Nbutton = document.getElementById("NButton");

    if(cur_page == 1){
        Pbutton.style.display = 'none';
        Nbutton.style.display = 'block';
    }
    
    if(cur_page > 1 && cur_page < pages_num){
        Pbutton.style.display = 'block';
        Nbutton.style.display = 'block';
    }

    if(pages_num == cur_page){
        Nbutton.style.display = 'none';
        Pbutton.style.display = 'block';
    }
    
}