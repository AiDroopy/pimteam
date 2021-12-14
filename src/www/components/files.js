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

async function getFiles(){
    let result = await fetch('/api/files');
    files = await result.json();

    console.log(files);

    renderFiles();
}

async function deleteFile(id) {
    let result = await fetch('api/files/' + id);
    files = await result.json();
    
    console.log(files);

    let resultFiles = await fetch('api/files/' + id, {
        method: "DELETE",
        body: JSON.stringify(files)
    });
    
    getFiles();
}

function renderFiles() {
    let fileList = document.querySelector(".file-container");

    // clear list before update
    fileList.innerHTML = "<a id='addBtn' href=#addFile><i class='fas fa-plus'></i> Add file</a>";

    for(let file of files) {
        let date = new Date(file.timestamp).toLocaleString();

        let fileLi = `
            <div id="listTxt">
                <a href="${file.fileUrl}"><i class="fas fa-file-archive"></i>   <h3>${file.header}</h3></a>
                <br>
                <button onclick="deleteFile(${file.id})"><i class="fas fa-trash-alt"></i></button>
                <br>
            </div>
        `;

        fileList.innerHTML += fileLi;
    }

}