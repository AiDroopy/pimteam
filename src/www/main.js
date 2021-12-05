
//renders constant header from header.js
document.querySelector('header').innerHTML = renderHeader();

onhashchange = changePage();

changePage();

//change page on hashchange using switch case
function changePage() {

    let page = location.hash.replace('#', '');

    switch(page){
        
        case "files":

        break;

        case "notes":
        
        break;

        case "images":

        break;

        default: 

        // empty HTML for main
            document.querySelector('main').innerHTML = `<div class='form-container'></div>`
        // add HTML to main
            document.querySelector('.form-container').innerHTML =  `
                <a id="hoover"><i class="far fa-sticky-note"></i><div class="formTxt">Add note<i class="fas fa-plus"></i></div></a>
                <a id="hoover"><i class="fas fa-camera-retro"></i><div class="formTxt">Add image<i class="fas fa-plus"></i></div></a>
                <a id="hoover"><i class="fas fa-file-archive"></i><div class="formTxt">Add file<i class="fas fa-plus"></i></div></a>
                `
        break;
    } 
}