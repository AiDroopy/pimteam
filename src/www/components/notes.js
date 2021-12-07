
let notes = [];

async function getNotes(){
    let result = await fetch('api/notes');
    notes = await result.json();

    console.log(notes);

    renderNotes();
}

function renderNotes(){
    let noteList = document.querySelector('.note-container');

    noteList.innerHTML = "";

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

