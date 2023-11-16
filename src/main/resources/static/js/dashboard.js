  function showDiagram(content) {

    document.getElementById("diagram-image").innerHTML = content;
    var svgElement = document.getElementsByTagName('svg')[0]
        svgElement.setAttribute("width", "100%");
        svgElement.setAttribute("height", "100%");
        svgElement.style=''
  }
