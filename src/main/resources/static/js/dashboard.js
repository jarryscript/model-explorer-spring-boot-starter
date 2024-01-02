$(function() {
var n = $("svg").first();
n.attr("width", "100%"), n.attr("height", "100%"), n.removeAttr("style");
$('svg').on('click',function(e){
 e.preventDefault();
			var t = (new XMLSerializer)
				.serializeToString($("svg")
					.get(0)),
				n = new Blob([t], {
					type: "image/svg+xml;charset=utf-8"
				}),
				i = URL.createObjectURL(n);
			window.open(i, "_blank")
})

});