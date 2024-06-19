(function($) {

$("#storeId").change(function() {
      if($("#storeId").val() !== "0"){
        $('#siteId').prop('disabled', 'disabled');
      }else{
        $('#siteId').prop('disabled', false);
      }
    });

    $("#siteId").change(function() {
      if($("#siteId").val() !== "0"){
        $('#storeId').prop('disabled', 'disabled');
      }else{
        $('#storeId').prop('disabled', false);
      }
    });

})(jQuery);
