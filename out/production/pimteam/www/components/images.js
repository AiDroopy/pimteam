function renderAddImage(){
    return `
    <h3>Upload Image</h3>
                    <input id="header" required type="text" placeholder="header">
                    <input type="file" placeholder="select image">
                    <button id="addBtn" type="submit">Add image</button>
    `
}

async function uploadImage(e) {
    e.preventDefault()

    let images = document.querySelector('input[type=file]').files;
    let formData = new FormData();

    let headerInput = document.querySelector('#header');

    console.log(headerInput.value);

    for(let image of images) {
        formData.append('image', image, image.name);
        formData.append('header', headerInput.value);
    }

    // upload selected files to server
    let uploadResult = await fetch('/api/images', {
        method: 'POST',
        body: formData
    });

}
async function getImages(){
    let result = await fetch('api/image');
    images = await result.json();

    console.log(images);

    renderFiles();
}

function renderFiles() {
    let imageList = document.querySelector(".image-container");

    // clear list before update
    imageList.innerHTML = "";

    for(let image of images) {
        let date = new Date(file.timestamp).toLocaleString();

        let imgLi = `
            <div id="listTxt">
                <img src=${image.fileUrl}><h3>${image.header}</h3></a>
                <br>
                <br>
            </div>
        `;

        imageList.innerHTML += imgLi;
    }

}