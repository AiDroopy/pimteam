
//renders constant header from header.js
document.querySelector('header').innerHTML = renderHeader();

onhashchange = changePage;

changePage();

//change page on hashchange using switch case
function changePage() {

    let page = location.hash.replace('#', '');
    let noteId = window.sessionStorage.getItem('editNoteId');

    switch(page){

        case "notes":

            document.querySelector('main').innerHTML = `<div class='note-container'></div>`;
            document.querySelector('.note-container').innerHTML = getNotes();

        break;

        case "editNotes/" + noteId:
           
            if(noteId !== undefined){  
                goNote(noteId);
                
            } else {
                location.hash = "";
                
            }

        break;

        case "addNote":
            
            document.querySelector('main').innerHTML = `<form class='addForm' onsubmit="createNote(event)"></form>`;
            document.querySelector('.addForm').innerHTML = renderAddNotes();
                
        break;

        case "images":
            document.querySelector('main').innerHTML = `<div class='image-container'></div>`;
            document.querySelector('.image-container').innerHTML = getImages();
        break;

        case "addImage":
            document.querySelector('main').innerHTML = `<form class='addForm' onsubmit="uploadImage(event)"></form>`;
            document.querySelector('.addForm').innerHTML = renderAddImage();
        break;
        
        case "files":
            document.querySelector('main').innerHTML = `<div class='file-container'></div>`;
            document.querySelector('.file-container').innerHTML = getFiles();
        break;

        case "addFile":
            document.querySelector('main').innerHTML = `<form class='addForm' onsubmit="uploadFile(event)"></form>`;
            document.querySelector('.addForm').innerHTML = renderAddFiles();
        break;
     
        default: 

        // empty HTML for main and add div form-container
            document.querySelector('main').innerHTML = `<div class='form-container'></div>`
        // add HTML to main
            document.querySelector('.form-container').innerHTML =  `
                <a id="hoover" href="#addNote"><i class="far fa-sticky-note"></i><div class="formTxt">Add note<i class="fas fa-plus"></i></div></a>
                <a id="hoover" href="#addImage"><i class="fas fa-camera-retro"></i><div class="formTxt">Add image<i class="fas fa-plus"></i></div></a>
                <a id="hoover" href="#addFile"><i class="fas fa-file-archive"></i><div class="formTxt">Add file<i class="fas fa-plus"></i></div></a>
                `
        break;
    } 
}