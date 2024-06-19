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


