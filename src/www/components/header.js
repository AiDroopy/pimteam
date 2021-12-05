// render header for application
function renderHeader(){
    return `
    
        <a href="#"><h1>PIM</h1></a> 
        <nav>
            <a id="hoover" href="#"><i class="fas fa-home"></i><div class="navTxt">Home</div></a>
            <a id="hoover" href="#notes"><i class="far fa-sticky-note"></i><div class="navTxt">Notes</div></a>
            <a id="hoover" href="#images"><i class="fas fa-camera-retro"></i><div class="navTxt">Images</div></a>
            <a id="hoover" href="#files"><i class="fas fa-file-archive"></i><div class="navTxt">Files</div></a>  
        </nav>
   
    `
}