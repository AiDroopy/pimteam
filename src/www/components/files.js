let uploads = [];

function renderAddFiles(){
    return `
    <h3>Upload file</h3>
                    <!-- <input id="header" required type="text" placeholder="header"> -->
                    <input type="file" placeholder="select file">
                    <button id="addBtn" type="submit">Add file</button>
    `
}

async function uploadFile(e) {
    e.preventDefault()

    let files = document.querySelector('input[type=file]').files;
    let formData = new FormData();

    for(let file of files) {
        formData.append('files', file, file.name);
    }

    // upload selected files to server
    let uploadResult = await fetch('/api/documents', {
        method: 'POST',
        body: formData
    });

    // // get the uploaded image url from response
    // let documentUrl = await uploadResult.text();
    // let headerInput = document.querySelector('#header');

    // // create a post object containing values from inputs
    // // and the uploaded image url
    // let post = {
    //     header: headerInput.value,
    //     documentUrl: documentUrl
    // }

    // let result = await fetch("/rest/posts", {
    //     method: "POST",
    //     body: JSON.stringify(post)
    // });

    // posts.push(post)

    // console.log(await result.text())
}