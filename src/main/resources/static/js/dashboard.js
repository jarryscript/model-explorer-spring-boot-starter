$(function() {
    function showDiagram(clickedNode, modelId) {

    }

    $('.model-link').on('click',function(e){
       e.preventDefault();
       var modelId = $(this).parent().data('model-id');
       var url = `/model-explorer/model/${modelId}`
       $("#spinner").show();
       $.get(url).done(function(response){
            $("#diagram-image").html(response.diagram);
               var svgElement = $('svg').first();
               svgElement.attr("width", "100%");
               svgElement.attr("height", "100%");
               svgElement.removeAttr("style");
               $('.list-group-item').removeClass('bg-dark');
               $(this).parent().addClass('bg-dark');
       }).always(function() {
             $("#spinner").hide();
       });
    });

    $('.del-btn').on('click',function(e){
        e.preventDefault();
        var modelId = $(this).parent().data('model-id');
         var modelName = $(this).parent().data('model-name')
          if(confirm(`Are you sure to delete the model ${modelName}?`)) {
                   var deleteUrl = `/model-explorer/model/${modelId}`
                   $("#spinner").show();
                   $.ajax({
                       url: deleteUrl,
                       type: 'DELETE',
                       success: function(result) {
                           window.location.reload();
                       },
                       always: function() {
                           $("#spinner").hide();
                       }
                   });
               }
    })

//     $('.reload-btn').on('click',function(e){
//       e.preventDefault();
//       $("#spinner").show();
//       $.get(url).done(function(response){
//        $("#diagram-image").html(response.diagram);
//           var svgElement = $('svg').first();
//           svgElement.attr("width", "100%");
//           svgElement.attr("height", "100%");
//           svgElement.removeAttr("style");
//           $('.list-group-item').removeClass('bg-dark');
//           $(this).parent().addClass('bg-dark');
//       }).always(function() {
//         $("#spinner").hide();
//       });
//    })

    function reloadMenu(){
        var url='';
        $.get(url).success(function(result){
            $('.left-section').replaceWith(result);
        })
    }

});
