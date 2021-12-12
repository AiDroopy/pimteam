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

    let files = document.querySelector('input[type=file]').files;
    let formData = new FormData();

    let headerInput = document.querySelector('#header');

    console.log(headerInput.value);

    for(let file of files) {
        formData.append('image', file, file.name);
        formData.append('header', headerInput.value);
    }

    // upload selected files to server
    let uploadResult = await fetch('/api/images', {
        method: 'POST',
        body: formData
    });

}

// get images and render as list on page
async function getImages(){
    let result = await fetch('/api/images');
    images = await result.json();

    console.log(images);

    renderImages();
}

//render images as list
function renderImages() {
    let imageList = document.querySelector(".image-container");

    // clear list before update
    imageList.innerHTML = "<a id='addBtn' href=#addImage><i class='fas fa-plus'></i> Add image</a>";

    for(let image of images) {
        let date = new Date(image.timestamp).toLocaleString();

        let imgLi = `
            <div id="listTxt">
                <a href="${image.fileUrl}"> <img src="${image.fileUrl}" alt="thumbnail n/a"><h3>${image.header}</h3></a>
                <br>
                <button onclick="deleteImage()"><i class="fas fa-trash-alt"></i></button>
                <br>
            </div>
        `;

        imageList.innerHTML += imgLi;
    }

}