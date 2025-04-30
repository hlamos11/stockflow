(function($) {

    //Crear Generador .INI

    $("#IdRefill").keyup(function() {
        var value = parseFloat($(this).val()).toFixed(2);
        $("#IdEestimatedAmount").val((value * 3.28).toFixed(2));
    });


    $("#IdCurrentLevel").keyup(function() {

        let currentLevel = parseFloat($(this).val());
        let capTank = parseFloat($("#tankLevelId").val());

        // Validaciones básicas
        if (isNaN(currentLevel) || isNaN(capTank)) {
            $("#IdRefill").val('');
            $("#IdEestimatedAmount").val('');
            return;
        }

        let value = capTank - currentLevel;

        // Evitar valores negativos
        if (value < 0) {
            value = 0;
        }

        $("#IdRefill").val(value);

        let val = value * 3.28;
        let total = val.toFixed(2);

        $("#IdEestimatedAmount").val(total);

    });

    //Crear Generador .FIN


    //Crear Detalle Generador .INI
    /*$("#refillId").keyup(function() {
        let value = $(this).val() * 3.28;
        let total = value.toFixed(2);
        $("#estimatedAmountId").val(total);
    });*/


    $("#refillId").keyup(function() {
            var value = parseFloat($(this).val()).toFixed(2);
            $("#estimatedAmountId").val((value * 3.28).toFixed(2));
        });


        $("#currentLevelId").keyup(function() {

            let currentLevel = parseFloat($(this).val());
            let capTank = parseFloat($("#iTankLevelId").val());

            // Validaciones básicas
            if (isNaN(currentLevel) || isNaN(capTank)) {
                $("#refillId").val('');
                $("#estimatedAmountId").val('');
                return;
            }

            let value = capTank - currentLevel;

            // Evitar valores negativos
            if (value < 0) {
                value = 0;
            }

            $("#refillId").val(value);

            let val = value * 3.28;
            let total = val.toFixed(2);

            $("#estimatedAmountId").val(total);

        });

    //Crear Detalle Generador .FIN HLA

})(jQuery);


$(document).ready(function () {
    const maxLevel = parseFloat($("#iTankLevelId").val()); // Valor máximo permitido
    const $input = $("#currentLevelId");

    $input.on("input", function () {
        let value = parseFloat($input.val());

        if (value < 0) {
            $input.val(0);
        } else if (value > maxLevel) {
            $input.val(maxLevel);
        }

        if (value > maxLevel) {
            alert("No puede ingresar un valor mayor que la capacidad del tanque.");
            $input.val(maxLevel);
        }
    });
});