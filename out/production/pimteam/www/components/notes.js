
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
async function createNote(e) {
    e.preventDefault();

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

async function goNote(e, id){
    
    e.preventDefault()

    let result = await fetch('api/notes/' + id);
    notes = await result.json();

    console.log(notes)

}

function renderEditNotes(){
    return `<h3>Create note</h3> <input id="header" required type="text" placeholder="header">
    <textarea id="notes" required placeholder="content" cols="30" rows="10"></textarea>
<button id="addBtn" type="submit">Add note</button>
`
}

// render all notes as a list of notes

function renderNotes(){
    let noteList = document.querySelector('.note-container');

    noteList.innerHTML = "<a id='addBtn' href=#addNote><i class='fas fa-plus'></i> Add note</a>";

    for(let note of notes){

        let noteLi = `
            <div id="listTxt">
                <button onclick="goNote(${note.id})"><h2>${note.header}</h2>
                <!-- <a href="#editNotes/${note.id}"><h2>${note.header}</h2></a>   -->
                <h3>${note.notes}</h3> 
                <br>
                <button onclick="deleteNote(${note.id})"><i class="fas fa-trash-alt"></i></button>
            </div>
        `;

        noteList.innerHTML += noteLi;
    }
}



