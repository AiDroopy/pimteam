
// Create an array as a placeholder for our notes, both to render them and to push new items into.
let notes = [];

//render html for adding notes page
function renderAddNotes(){
    return ` <h3>Create note</h3>
                     <input id="header" required type="text" placeholder="header">
                     <textarea id="notes" required placeholder="content" cols="30" rows="10"></textarea>
                     <button id="addBtn" type="submit">Add note</button>
     `
}

//Create and add note to database
async function createNote(event) {
    event.preventDefault();

    let headerInput = document.querySelector('#header');
    let notesInput = document.querySelector('#notes');

    let note = {
        /*Always user 1, until login works. Global user*/
        userId: 1,
        header: headerInput.value,
        notes: notesInput.value,
    }

    let result = await fetch("api/notes", {
        method: "POST",
        body: JSON.stringify(note)
    });

    notes.push(note)

    console.log(await result.text())

    alert("Note Created");

    window.location.reload();
}

//get notes from database using our Express-server routes
async function getNotes(){
    let result = await fetch('api/notes');
    notes = await result.json();

    console.log(notes);

    renderNotes();
}


//Delete note by trashcan
async function deleteNote(id){

    let result = await fetch('api/notes/' + id);
    notes = await result.json();
    
    console.log(notes);

    let resultNote = await fetch('api/notes/' + id, {
        method: "DELETE",
        body: JSON.stringify(notes)
    });
    
    getNotes();
}

async function goNote(id){

    let result = await fetch('api/notes/' + id);
    notes = await result.json();
    
    console.log(notes.id)

    window.sessionStorage.setItem('editNoteId', notes.id);

    renderNote();
}
    
function renderNote(){
    
    document.querySelector('main').innerHTML = `<form class='addForm' onsubmit="editNote(event)"></form>`;
    document.querySelector('.addForm').innerHTML =
    ` <h3>Update note</h3>
    <textarea id="header" required type="text">${notes.header}</textarea>
    <textarea id="notes" cols="30" rows="10">${notes.notes}</textarea>
    <button id="addBtn" type="submit">Update note</button>
    `
}

async function editNote(){

    let headerInput = document.querySelector('#header');
    let notesInput = document.querySelector('#notes');

    let note = {
        /*Always user 1, until login works. Global user*/
        header: headerInput.value,
        notes: notesInput.value,
    }

    let result = await fetch("api/notes/" + id, {
        method: "PUT",
        body: JSON.stringify(note)
    });

    notes.push(note)

    console.log(await result.text())

    renderNotes();
}

// render all notes as a list of notes

function renderNotes(){
    let noteList = document.querySelector('.note-container');

    noteList.innerHTML = "<a id='addBtn' href=#addNote><i class='fas fa-plus'></i>Add note</a>";

    for(let loopNote of notes){

        let noteLi = `
            <div id="listTxt">
                <a href="#editNotes" onclick="goNote(${loopNote.id})"><h2>${loopNote.header}</h2></a>
                <h3>${loopNote.notes}</h3><h4><button onclick="deleteNote(${loopNote.id})"><i class="fas fa-trash-alt"></i></button></h4>
                
                <br>
                <br>
                
            </div>
        `;

        noteList.innerHTML += noteLi;

    }
}



