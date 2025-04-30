// Función principal: Buscar historial de generadores por ID
function searchGeneratorHistById(id) {
    $.ajax({
        url: "searchGeneratorHistById",
        data: { id: id },
        dataType: 'json',
        success: function(result) {
            const data = [];

            if (result.ListGeneratorHist && result.ListGeneratorHist.length > 0) {
                for (let i = 0; i < result.ListGeneratorHist[0].length; i++) {
                    const item = result.ListGeneratorHist[0][i];
                    data.push({
                        Fecha: formatDate(item[0]),
                        ID: item[1],
                        Nombre: item[2],
                        Capacidad: item[5],
                        Nivel_Ant: item[6],
                        Nivel_Actual: item[7],
                        Horometro: item[9],
                        Horas_Trab: item[14],
                        Recargar: item[13],
                        Suministrado: item[8],
                        Monto: item[10],
                    });
                }
            }

            // Si ya existe el grid, destruirlo antes de crear uno nuevo
            if ($("#js-grid-generator_hist").length) {
                $("#js-grid-generator_hist").jsGrid("destroy");
            }

            // Inicializar jsGrid
            $("#js-grid-generator_hist").jsGrid({
                height: "500px",
                width: "100%",
                selecting: true,
                filtering: false,   // Activar filtros
                editing: false,
                inserting: false,
                sorting: true,
                paging: true,      // Activar paginación
                autoload: true,
                pageSize: 10,
                pageButtonCount: 5,
                data: data,
                fields: [
                    { name: "Fecha", type: "text", title: "Fecha", align: "center", width: 100, filtering: false },
                    { name: "ID", type: "text", title: "ID", align: "center", width: 50, filtering: false },
                    { name: "Nombre", type: "text", title: "Nombre", align: "center", width: 150, filtering: false },
                    { name: "Capacidad", type: "text", title: "Capacidad", align: "center", width: 80, filtering: false },
                    { name: "Nivel_Ant", type: "text", title: "Nivel Ant.", align: "center", width: 80, filtering: false },
                    { name: "Nivel_Actual", type: "text", title: "Nivel Actual", align: "center", width: 80, filtering: false },
                    { name: "Horometro", type: "text", title: "Horómetro", align: "center", width: 80, filtering: false },
                    { name: "Horas_Trab", type: "text", title: "Horas Trab.", align: "center", width: 80, filtering: false },
                    { name: "Recargar", type: "text", title: "Recargar", align: "center", width: 80, filtering: false },
                    { name: "Suministrado", type: "text", title: "Suministrado", align: "center", width: 80, filtering: false },
                    { name: "Monto", type: "text", title: "Monto", align: "center", width: 80, filtering: false }
                ]
            });

            // Mostrar el modal
            $("#modal_generator_hist_view").modal('show');
        },
        error: function() {
            alert("Ocurrió un error al obtener los datos del historial.");
        }
    });
}

// Función para formatear fechas
function formatDate(dateString) {
    if (!dateString) return '';

    const date = new Date(dateString);
    if (isNaN(date.getTime())) {
        return ''; // Fecha inválida
    }

    const day = ("0" + date.getDate()).slice(-2);
    const month = ("0" + (date.getMonth() + 1)).slice(-2);
    const year = date.getFullYear();

    return `${day}/${month}/${year}`;
}

// Evento: Limpiar filtros
$(document).on('click', '#btnClearFilters', function() {
    var $grid = $("#js-grid-generator_hist");

    // Limpiar todos los inputs de filtro
    $grid.find("input").val("");

    // Refrescar el grid
    $grid.jsGrid("search");
});

// Evento: Exportar a Excel
$(document).on('click', '#btnExportExcel', function() {
    var data = $("#js-grid-generator_hist").jsGrid("option", "data");

    if (!data || data.length === 0) {
        alert("No hay datos para exportar.");
        return;
    }

    // Convertir datos a hoja
    var worksheet = XLSX.utils.json_to_sheet(data);

    // Crear libro
    var workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, "Historial Generadores");

    // Obtener fecha actual
    const now = new Date();
    const day = String(now.getDate()).padStart(2, '0');
    const month = String(now.getMonth() + 1).padStart(2, '0'); // Enero es 0
    const year = now.getFullYear();
    const fechaActual = `${day}-${month}-${year}`;

    // Nombre dinámico
    const fileName = `Historial_Generadores_${fechaActual}.xlsx`;

    // Descargar automáticamente
    XLSX.writeFile(workbook, fileName, { cellStyles: true });
});
