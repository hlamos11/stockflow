function searchGeneratorHistById(id) {

    $.ajax({
        url: "searchGeneratorHistById",
        data: {
            id: id
        },
        dataType: 'json',
        success: function(result) {

            const data = [];

            for (let i = 0; i < result.ListGeneratorHist[0].length; i++) {
                console.log('resp: ' + result.ListGeneratorHist[0][i]);
                console.log(data);
                data.push({
                    Fecha: result.ListGeneratorHist[0][i][0],
                    ID: result.ListGeneratorHist[0][i][1],
                    Sitio: result.ListGeneratorHist[0][i][2],
                    Provincia: result.ListGeneratorHist[0][i][3],
                    Region: result.ListGeneratorHist[0][i][4],
                    Max: result.ListGeneratorHist[0][i][5],
                    Actual: result.ListGeneratorHist[0][i][6],
                    Recargar: result.ListGeneratorHist[0][i][12],
                    Suministrado: result.ListGeneratorHist[0][i][7],
                    Horas: result.ListGeneratorHist[0][i][8],
                    Costo: result.ListGeneratorHist[0][i][9],
                    Previo: result.ListGeneratorHist[0][i][10],
                    Final: result.ListGeneratorHist[0][i][11]
                });
            }

            console.log(data);


            if ($("#js-grid-generator_hist").length) {
                $("#js-grid-generator_hist").jsGrid({
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
                    data: data,
                    fields: [{
                            name: "Fecha",
                            type: "text",
                            width: 70
                        },
                        {
                            name: "ID",
                            type: "text",
                            width: 60
                        },
                        {
                            name: "Sitio",
                            type: "text",
                            width: 70
                        },
                        {
                            name: "Provincia",
                            type: "text",
                            width: 100
                        },
                        {
                            name: "Region",
                            type: "text",
                            width: 70
                        },
                        {
                            name: "Max",
                            type: "text",
                            width: 50
                        },
                        {
                            name: "Actual",
                            type: "text",
                            width: 70
                        },
                        {
                            name: "Recargar",
                            type: "text",
                            width: 70
                        },
                        {
                            name: "Suministrado",
                            type: "text",
                            width: 100
                        },
                        {
                            name: "Horas",
                            type: "text",
                            width: 60
                        },
                        {
                            name: "Costo",
                            type: "text",
                            width: 60
                        },
                        {
                            name: "Previo",
                            type: "text",
                            width: 70
                        },
                        {
                            name: "Final",
                            type: "text",
                            width: 60
                        },

                    ]
                });
            }




        }
    });

    $("#modal_generator_hist_view").modal('show');

}