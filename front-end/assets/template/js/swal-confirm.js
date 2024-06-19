(function($) {

    $("#btn_save_material").click(function(e) {
        e.preventDefault();

        if ($('#snId').val().length == 0) {
            return false;
        }

        if ($('#descriptionId').val().length == 0) {
            return false;
        }

        if ($('#partId').val().length == 0) {
            return false;
        }

        if ($('#modelId').val().length == 0) {
            return false;
        }

        if ($('#brandId').val().length == 0) {
            return false;
        }

        if ($('#storeId').val().length == 0) {
            return false;
        }

        if ($('#inventoryMaterialId').val().length == 0) {
            return false;
        }


        swal({
            title: '¿Desea registrar el material?',
            text: "El material sera registrado en el inventario!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3f51b5',
            cancelButtonColor: '#ff4081',
            confirmButtonText: 'Great ',
            buttons: {
                cancel: {
                    text: "Cancel",
                    value: null,
                    visible: true,
                    className: "btn btn-danger",
                    closeModal: true,
                },
                confirm: {
                    text: "OK",
                    value: true,
                    visible: true,
                    className: "btn btn-info",
                    closeModal: true
                }
            }
        }).then((value) => {
            if (value === true) {
                $("#formRegMat").submit();
                // Add Your Custom Code for CRUD
            }
            return false;
        });
    });




    $("#btnRegMovement").click(function(e) {
        e.preventDefault();
        //$("#formMovement").validate();

        var typeMovement = $.trim($('#typeMovement').val());
        var siteId = $.trim($('#siteId').val());
        var storeId = $.trim($('#storeId').val());
        var materialId = $.trim($('#materialId').val());
        var dateReturn = $('#dateReturn').val();
        var dateOut = $('#dateOut').val();
        var dateTransfer = $('#dateTransfer').val();
        var materialId = $.trim($('#materialId').val());
        var responsibleId = $.trim($('#responsibleId').val());

        if (materialId == null || materialId == '' || materialId == 'Seleccione' || typeMovement == null || typeMovement == '' || typeMovement == 'Seleccione' || responsibleId == null || responsibleId == '' || responsibleId == 'Seleccione') {
            alertError();
            return false;
        }


        switch (typeMovement) {
            case "SALIDA":
                if (dateOut == null || dateOut == '' || siteId == null || siteId == '' || siteId == 'Seleccione') {
                    alertError();
                    return false;
                }
                break;

            case "TRASLADO":
                console.log("valor bodega : " + storeId);
                console.log("valor dateTransfer : " + dateTransfer);
                if (dateTransfer == null || dateTransfer == '' || storeId == null || storeId == '' || storeId == 'Seleccione') {
                    alertError();
                    return false;
                }
                break;

            case "RETORNO":
                if (dateReturn == null || dateReturn == '' || storeId == null || storeId == '' || storeId == 'Seleccione') {
                    alertError();
                    return false;
                }
                break;

            case "DESCARTE":
                break;
            case "RMA":
                break;
            default:
                alert("Tipo de movimiento no encontrado");
                return false;
        }

        swal({
            title: '¿Desea registrar el movimiento?',
            text: "El movimiento sera registrado y se actualizara el inventario!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3f51b5',
            cancelButtonColor: '#ff4081',
            confirmButtonText: 'Great ',
            buttons: {
                cancel: {
                    text: "Cancel",
                    value: null,
                    visible: true,
                    className: "btn btn-danger",
                    closeModal: true,
                },
                confirm: {
                    text: "OK",
                    value: true,
                    visible: true,
                    className: "btn btn-success",
                    closeModal: true
                }
            }
        }).then((value) => {
            if (value === true) {
                $("#formMovement").submit();
                // Add Your Custom Code for CRUD
            }
            return false;
        });
    });


    $("#btn_save_user").click(function(e) {
        e.preventDefault();

        var firstname = $.trim($('#firstname').val());
        var lastname = $.trim($('#lastname').val());
        var username = $.trim($('#username').val());
        var password = $.trim($('#password').val());
        var rpassword = $.trim($('#rpassword').val());

        if (firstname == null || firstname == '') {
            alertErrorUser('Nombre');
            return false;
        }

        if (lastname == null || lastname == '') {
            alertErrorUser('apellido');
            return false;
        }
        if (username == null || username == '') {
            alertErrorUser('usuario');
            return false;
        }
        if (password == null || password == '') {
            alertErrorUser('Contraseña');
            return false;
        }
        if (rpassword == null || rpassword == '') {
            alertErrorUser('Confirmar Contraseña');
            return false;
        }


        swal({
            title: 'Usuario Nuevo',
            text: "¿Desea registrar el usuario?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3f51b5',
            cancelButtonColor: '#ff4081',
            confirmButtonText: 'Great ',
            buttons: {
                cancel: {
                    text: "Cancel",
                    value: null,
                    visible: true,
                    className: "btn btn-danger",
                    closeModal: true,
                },
                confirm: {
                    text: "OK",
                    value: true,
                    visible: true,
                    className: "btn btn-info",
                    closeModal: true
                }
            }
        }).then((value) => {
            if (value === true) {

                $("#formRegUser").submit();
                /*$.ajax({
                        method: "POST",
                        url: "/register",
                        data: $('#regFormUser').serialize(),
                        success: function(status){
                        console.log("Status: "+ status);
                            if(status) {
                                //here you check the response from your controller and add your business logic
                            }
                        }
                    });*/
            }
            return false;
        });
    });


    function alertError() {
        swal({
            title: 'Error!',
            text: 'Complete todos los campos',
            icon: 'error',
            button: {
                text: "OK",
                value: true,
                visible: true,
                className: "btn btn-success"
            }
        })
    }

    function alertErrorUser(param) {
        swal({
            title: 'Error!',
            text: 'Complete el campo: ' + param,
            icon: 'error',
            button: {
                text: "OK",
                value: true,
                visible: true,
                className: "btn btn-success"
            }
        })
    }


})(jQuery);