$(function() {
    function refreshMenu(reload){
            var url = `/model-explorer/left-section${reload?'/reload':''}`
               $.get(url).done(function(result){
                   $('.left-section').replaceWith(result);
               }).always(function() {
             $("#spinner").hide();
           });
    }
    $('.menu').on('click','.model-link',function(e){
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
               $('#right-section').data('selected',modelId);
               $('.full-screen-btn').show();
       }).always(function() {
             $("#spinner").hide();
       });
    }).on('click','.del-btn',function(e){
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
                           refreshMenu(false);
                           if(modelId==$('#right-section').data('selected')){
                           $("#diagram-image").html('');
                           $('.full-screen-btn').hide();
                           }
                       },
                       always: function() {
                           $("#spinner").hide();
                       }
                   });
               }
    }).on('click','.reload-btn',function(e){
       e.preventDefault();
      refreshMenu(true);
    });

    $('.full-screen-btn').on('click',function(e){
         e.preventDefault();
 var serializer = new XMLSerializer();
    var svgStr = serializer.serializeToString($("svg").get(0));

    var blob = new Blob([svgStr], {type: 'image/svg+xml;charset=utf-8'});
    var url = URL.createObjectURL(blob);

    window.open(url, '_blank');
    })
});
