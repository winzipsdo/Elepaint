window.onload = function () {

    const tag_size = document.querySelector('#tag-size');
    const tag_yonezawa = document.querySelector('#tag-yonezawa');
    const tag_zoom = document.querySelector('#tag-zoom');
    const big_tag_size = document.querySelector("#big-tag-size");
    const textinch = document.querySelector('#textinch');
    const textweight = document.querySelector('#textweight');
    const download = document.querySelector('#download');
    const btn_clear = document.querySelector('#btn_clear');

    let pixvalue = 'pix192';
    let screeninch = '15.0\"';
    let proweight = '2.50kg';
    let copystyle = 'sizetag';
    let country = 'cap';

    let pixelcontrol = document.getElementById("pixelsel");
    let weightcontrol = document.getElementById("textweight");
    let inchcontrol = document.getElementById("textinch");
    let countrycontrol = document.getElementById("country");

    function size_hidden() {
        textinch.style.visibility = 'hidden';
        textweight.style.visibility = 'hidden';
    }

    function size_show() {
        textinch.style.visibility = 'visible';
        textweight.style.visibility = 'visible';
    }

    function slidedown() {
        let position_bottom = -1.5625 * download.querySelectorAll('p').length;
        download.style.bottom = position_bottom + 'em';
    }

    async function filelist_clear() {
        download.style.bottom = '0';
        await sleep.sleep(500);
        p = download.querySelectorAll('p');
        for (let i = 0; i<p.length-1; i++){
            download.removeChild(p[i]);
        }
    }

    btn_clear.onclick = () => {
        filelist_clear();
    };

    tag_yonezawa.classList.add('gray');
    tag_zoom.classList.add('gray');
    big_tag_size.classList.add('gray');

    tag_size.onclick = () => {
        copystyle = 'sizetag';
        tag_size.classList.remove('gray');
        tag_yonezawa.classList.add('gray');
        tag_zoom.classList.add('gray');
        big_tag_size.classList.add('gray');
        size_show();
    };

    tag_yonezawa.onclick = () => {
        copystyle = 'yonezawa';
        tag_size.classList.add('gray');
        tag_yonezawa.classList.remove('gray');
        tag_zoom.classList.add('gray');
        big_tag_size.classList.add('gray');
        size_hidden();
    };

    tag_zoom.onclick = () => {
        copystyle = 'zoom';
        tag_size.classList.add('gray');
        tag_yonezawa.classList.add('gray');
        tag_zoom.classList.remove('gray');
        big_tag_size.classList.add('gray');
        size_hidden();
    };

    big_tag_size.onclick = () => {
        copystyle = 'big_sizetag';
        tag_size.classList.add('gray');
        tag_yonezawa.classList.add('gray');
        tag_zoom.classList.add('gray');
        big_tag_size.classList.remove('gray');
        size_show();
    };

    //test line-------------
    let logo_text = document.querySelector('#logo_text');
    logo_text.onclick = () => {
        let p = document.createElement("p");
        let p_1 = download.querySelector('p');
        download.insertBefore(p,p_1);
        p.innerHTML = "hello elephant :)";
        slidedown();
    };

    pixelcontrol.addEventListener("change", function () {
        pixvalue = pixelcontrol.value;
    });

    weightcontrol.addEventListener("change", function () {
        proweight = weightcontrol.value;
    });

    inchcontrol.addEventListener("change", function () {
        screeninch = inchcontrol.value;
    });

    countrycontrol.addEventListener("change", function () {
        country = countrycontrol.value;
    });

    let pro_div = document.getElementById("draginto");
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

        let img_form = new FormData();
        let files = e.dataTransfer.files;

        for (let i = 0; i < files.length; i++) {
            img_form.append("file", files[i]);
        }

        img_form.append("pixvalue", pixvalue);
        img_form.append("screeninch", screeninch);
        img_form.append("proweight", proweight);
        img_form.append("copystyle", copystyle);
        img_form.append("country", country);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", "elephant", true);
        xhr.onreadystatechange = function (e) {
            if (xhr.readyState == 4 && xhr.status == 200) {
                let responseJSON = xhr.responseText;
                let js = JSON.parse(responseJSON);
                for (event in js) {
                    let p = document.createElement("p");
                    let p_1 = download.querySelector('p');
                    download.insertBefore(p,p_1);
                    p.innerHTML = "<a download='" + event + ".png' href='" + js[event] + "'>" + event + "</a>";
                    slidedown();
                }
            }
        };
        xhr.enctype = "multipart/form-data";
        xhr.send(img_form);

    });

    pro_div.addEventListener("dragleave", function (e) {
        e.stopPropagation();
        e.preventDefault();
    });

}



