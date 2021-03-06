//Render form to add images
function renderAddImage(){
    return `
    <h3>Upload Image</h3>
                    <input id="header" required type="text" placeholder="header">
                    <input type="file" placeholder="select image to upload">
                    <button id="addBtn" type="submit">Upload image</button>
    `
}
//function to uppload image on server and database
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
    alert("Image uploaded!")
    window.location.reload();
}

// get images and render as list on page
async function getImages(){
    let result = await fetch('/api/images');
    images = await result.json();

    console.log(images);

    renderImages();
}
//delete image from computer and from database
async function deleteImage(id) {
    let result = await fetch('api/images/' + id);
    images = await result.json();
    
    console.log(images);

    let resultImage = await fetch('api/images/' + id, {
        method: "DELETE",
        body: JSON.stringify(images)
    });
    alert("Image deleted from server")
    window.location.reload();
}

//render images as list
function renderImages() {
    let imageList = document.querySelector(".image-container");

    // clear list before update
    imageList.innerHTML = "<a id='addBtn' href=#addImage><i class='fas fa-plus'></i> Add image</a><br>";

    for(let image of images) {
        let date = new Date(image.timestamp).toLocaleString();

        let imgLi = `
            <div id="listTxt">
                <a href="${image.fileUrl}"> <img src="${image.fileUrl}" alt="thumbnail n/a"></a>
                <h4><button onclick="deleteImage(${image.id})"><i class="fas fa-trash-alt"></i></button></h4>
                <br>
                <br>
            </div>
        `;

        imageList.innerHTML += imgLi;
    }

}