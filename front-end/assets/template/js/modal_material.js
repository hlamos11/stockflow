$('#tbl_material').on('click', 'tr td', function(evt) {
    var target, id, name, comment, inventory, site, stored, enabled;
    target = $(event.target);
    id = target.parent().data('id');
    name = target.parent("tr").find("td").eq(0).html();
    inventory = target.parent("tr").find("td").eq(1).html();
    site = target.parent("tr").find("td").eq(2).html();
    stored = target.parent("tr").find("td").eq(3).html();
    enabled = target.parent("tr").find("td").eq(9).html();
    comment = target.parent("tr").find("td").eq(10).html();

    if (site == 8) {
        site = 'N/A';
    }

    $("#txt_id").val(id);
    $("#txt_name").val(name);
    $("#txt_inventory").val(inventory);
    $("#txt_site").val(site);
    $("#txt_stored").val(stored);
    $("#txt_enabled").val(enabled);
    $("#txt_comment").val(comment);
    $("#modal_material").modal('show');

});

function searchMaterialByStore(id) {

    //    $('#btnApp').trigger('click');

    $.ajax({
        url: "searchMaterialByStore",
        data: {
            id: id
        },
        dataType: 'json',
        success: function(result) {

            console.log(result);


            var data = [];
            var dbm = {};
            window.dbm = dbm;

            data.push(result.ListMat);

            let claves = Object.keys(result.ListMat);

            for(let i=0; i< claves.length; i++){
              let clave = claves[i];
              console.log(result.ListMat[clave]);

            }


            console.log("dbm.mats: " + dbm.mats);

            if ($("#js-grid-mat").length) {
                $("#js-grid-mat").jsGrid({
                    height: "500px",
                    width: "100%",
                    filtering: false,
                    editing: false,
                    inserting: false,
                    sorting: true,
                    paging: true,
                    autoload: true,
                    pageSize: 5,
                    pageButtonCount: 5,
                    data: result.ListMat[0],
                    fields: [{
                            name: "part",
                            type: "text",
                            width: 70
                        },
                        {
                            name: "description",
                            type: "text",
                            width: 70
                        },
                        {
                            name: "model",
                            type: "text",
                            width: 70
                        },
                        {
                            name: "sn",
                            type: "text",
                            width: 200
                        }
                    ]
                });
            }



        }
    });

    $("#modal_material_view").modal('show');

}

$("#btnApp").on('click', function() {
    console.log('Acción ejecutada!')
});

$('#btnViewMat').on('click', function(evt) {
    var target;
    target = $(event.target);
    $("#modal_material_view").modal('show');

});