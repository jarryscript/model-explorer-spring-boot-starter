  function showDiagram(clickedNode,content) {

    document.getElementById("diagram-image").innerHTML = content;
    var svgElement = document.getElementsByTagName('svg')[0]
        svgElement.setAttribute("width", "100%");
        svgElement.setAttribute("height", "100%");
        svgElement.style='';
   document.querySelectorAll('.list-group-item').forEach(function(el) {
        el.classList.remove('bg-dark');
    });
    clickedNode.parentNode.classList.add('bg-dark');
  }
