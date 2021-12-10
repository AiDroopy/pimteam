let uploads = [];

function renderAddFiles(){
    return `
    <h3>Upload file</h3>
                    <input id="header" required type="text" placeholder="header">
                    <input type="file" placeholder="select file">
                    <button id="addBtn" type="submit">Add file</button>
    `
}

async function uploadFile(e) {
    e.preventDefault()

    let files = document.querySelector('input[type=file]').files;
    let formData = new FormData();

    let headerInput = document.querySelector('#header');

    console.log(headerInput.value);

    for(let file of files) {
        formData.append('files', file, file.name);
        formData.append('header', headerInput.value);
    }

    // upload selected files to server
    let uploadResult = await fetch('/api/files', {
        method: 'POST',
        body: formData
    });

}