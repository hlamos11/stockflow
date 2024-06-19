(function($) {

    $("#typeMovement").change(function() {

        $('#dateReturn').val("");
        $('#dateTransfer').val("");
        $('#dateIn').val("");
        $('#dateOut').val("");
        $('#storeId').val(0).change();
        $('#siteId').val(0).change();

        if ($("#typeMovement").val() == "SALIDA") {
            $('#dateTransfer').prop('disabled', 'disabled');
            $('#dateIn').prop('disabled', 'disabled');
            $('#dateOut').prop('disabled', false);
            $('#dateReturn').prop('disabled', 'disabled');
            $('#storeId').prop('disabled', 'disabled');
            $('#siteId').prop('disabled', false);
        }

        if ($("#typeMovement").val() == "TRASLADO") {
            $('#dateTransfer').prop('disabled', false);
            $('#dateIn').prop('disabled', 'disabled');
            $('#dateOut').prop('disabled', 'disabled');
            $('#dateReturn').prop('disabled', 'disabled');
            $('#storeId').prop('disabled', false);
            $('#siteId').prop('disabled', 'disabled');
        }


        if ($("#typeMovement").val() == "RETORNO") {
            $('#dateTransfer').prop('disabled', 'disabled');
            $('#dateIn').prop('disabled', 'disabled');
            $('#dateOut').prop('disabled', 'disabled');
            $('#dateReturn').prop('disabled', false);
            $('#storeId').prop('disabled', false);
            $('#siteId').prop('disabled', 'disabled');

        }

        if ($("#typeMovement").val() == "DESCARTE") {
            $('#dateTransfer').prop('disabled', 'disabled');
            $('#dateIn').prop('disabled', 'disabled');
            $('#dateOut').prop('disabled', 'disabled');
            $('#dateReturn').prop('disabled', 'disabled');
            $('#storeId').prop('disabled', false);
            $('#siteId').prop('disabled', 'disabled');
        }

        if ($("#typeMovement").val() == "RMA") {
            $('#dateTransfer').prop('disabled', 'disabled');
            $('#dateIn').prop('disabled', 'disabled');
            $('#dateOut').prop('disabled', 'disabled');
            $('#dateReturn').prop('disabled', 'disabled');
            $('#storeId').prop('disabled', false);
            $('#siteId').prop('disabled', 'disabled');
        }



    });

})(jQuery);