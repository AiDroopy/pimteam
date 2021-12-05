
let notes = [];

async function getNotes(){
    let result = await fetch('api/notes');
    notes = await result.json();

    console.log(notes);

    renderNotes();
}

function renderNotes(){
    let noteList = document.querySelector('.note-container');

    for(let note of notes){

        let noteLi = `
            <li>
                Header: ${note.header} <br>
                Note: ${note.note} <br>
            </li> <br>
        `;

        noteList.innerHTML += noteLi;
    }
}