
let notes = [];

async function getNotes(){
    let result = await fetch('api/notes');
    notes = await result.json();

    console.log(notes);

    renderNotes();
}

function renderNotes(){
    let noteList = document.querySelector('.note-container');

    noteList.innerHTML = "<a class='adding' href=#addNote>Add note</a>";

    for(let note of notes){

        let noteLi = `
            <div id="listTxt">
                <a href="#goNote"><h2>${note.header}</h2></a>  
                <h3>${note.notes}</h3> 
            </div>
        `;

        noteList.innerHTML += noteLi;
    }
}

function renderAddNotes(){
   return ` <h3>Create note</h3>
                    <input id="title" required type="text" placeholder="title">
                    <textarea id="content" required placeholder="content" cols="30" rows="10"></textarea>
                    <button type="submit">Create note</button>
    `
}
