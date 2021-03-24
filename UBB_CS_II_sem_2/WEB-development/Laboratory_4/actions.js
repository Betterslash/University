const IMAGE_LINK_1 = "assets/image_1.jpg";
const IMAGE_LINK_2 = "assets/image_2.jfif";
const IMAGE_LINK_3 = "assets/image_3.jfif";
const IMAGE_LINK_4 = "assets/image_4.jpg";
const IMAGE_LINK_5 = "assets/image_5.jpg";
let currentIndex = 4;
var images = [
  IMAGE_LINK_1,
  IMAGE_LINK_2,
  IMAGE_LINK_3,
  IMAGE_LINK_4,
  IMAGE_LINK_5,
];
var fontFamilies = [
    '"Times New Roman", Times, serif',
    'Arial, Helvetica, sans-serif',
    '"Lucida Console", "Courier New", monospace'
];
var fontsStiles = [
    'italic',
    'normal',
    'oblique'
];
function changeBackground() {
  if (currentIndex > 0) {
    console.log(currentIndex);
    document.body.style.backgroundImage = "url(" + images[currentIndex] + ")";
    currentIndex -= 1;
  }
  if (currentIndex == 0) {
    currentIndex = 4;
  }
}
function allLi() {
  var ul = document.getElementById("list");
  var li = document.createElement("li");
  var customDiv = document.getElementsByClassName("ul-custom-div");
  for (i = 0; i < 10; i++) {
    var cloneLi = li.cloneNode();
    ul.appendChild(cloneLi);
    var link = document.createElement("a");
    link.appendChild(document.createTextNode("Link_" + Math.round(Math.random() * 20)));
    link.className = "custom-link"
    link.href = "https://www.geeksforgeeks.org";
    cloneLi.appendChild(link);
  }
  customDiv[0].style.display = "block";
}
function changeLinks(){
    var linksList = document.getElementsByClassName("custom-link");
    for(i = 0; i < linksList.length; i++){
        let colorChanger = Math.round(Math.random() * 255);
        let colorChanger1 = Math.round(Math.random() * 255);
        let colorChanger2 = Math.round(Math.random() * 255);
        let fontSizeChanger = Math.random() * 3;
        let opacity = Math.random() * 2;
        linksList[i].style.color = "rgb("+ colorChanger + ", "+ colorChanger1 +", "+colorChanger2+", " + opacity +")";
        linksList[i].style.fontSize = fontSizeChanger.toString() + "vh";
        linksList[i].style.fontFamily = fontFamilies[Math.round(Math.random() * 2)];
        linksList[i].style.fontStyle = fontsStiles[Math.round(Math.random() * 2)];
    }
}
function doAll(){
    changeBackground();
    allLi();
    changeLinks();
}