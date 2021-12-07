function renderAddFiles(){
    return `
    <h3>Upload file</h3>
                    <input id="header" required type="text" placeholder="header">
                    <textarea id="content" required placeholder="content" cols="10" rows="10"></textarea>
                    <input type="file" placeholder="select file">
                    <button id="addBtn" type="submit">Add file</button>
    `
}