var ctx = document.getElementById("ChartTop5Establishments");
//getChartData();

function renderChart(data, labels) {

    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                    label: 'count',
                    data: data[0],
                    backgroundColor: "rgba(2,117,216,1)",
                    borderColor: "rgba(2,117,216,1)",
                },
                {
                    label: 'name',
                    data: data[1],
                    borderColor: 'rgba(192, 192, 192, 1)',
                    backgroundColor: 'rgba(192, 192, 192, 0.2)',
                }
            ]
        },
        options: {
            scales: {
                xAxes: [{
                    time: {
                        unit: 'month'
                    },
                    gridLines: {
                        display: false
                    },
                    ticks: {
                        maxTicksLimit: 6
                    }
                }],
                yAxes: [{
                    ticks: {
                        min: 0,
                        max: 100,
                        maxTicksLimit: 10
                    },
                    gridLines: {
                        display: true
                    }
                }],
            },
            legend: {
                display: false
            }
        }
    });
}

var ctxm = document.getElementById("ChartInstallationsForMonth");

function renderChartm(datam, labelsm) {

    var myChart = new Chart(ctxm, {
        type: 'bar',
        data: {
            labels: labelsm,
            datasets: [{
                    label: 'countm',
                    data: datam[0],
                    backgroundColor: "rgba(2,117,216,1)",
                    borderColor: "rgba(2,117,216,1)",
                },
                {
                    label: 'namem',
                    data: datam[1],
                    borderColor: 'rgba(192, 192, 192, 1)',
                    backgroundColor: 'rgba(192, 192, 192, 0.2)',
                }
            ]
        },
        options: {
            scales: {
                xAxes: [{
                    time: {
                        unit: 'month'
                    },
                    gridLines: {
                        display: false
                    },
                    ticks: {
                        maxTicksLimit: 6
                    }
                }],
                yAxes: [{
                    ticks: {
                        min: 0,
                        max: 100,
                        maxTicksLimit: 10
                    },
                    gridLines: {
                        display: true
                    }
                }],
            },
            legend: {
                display: false
            }
        }
    });
}



function getChartData() {
    $.ajax({
        url: "chartswifi",
        dataType: 'json',
        success: function(result) {

            var data = [];
            var datam = [];

            data.push(result.count);
            data.push(result.name);

            datam.push(result.countm);
            datam.push(result.namem);

            var labels = result.name;
            var labelsm = result.namem;

            renderChart(data, labels);
            renderChartm(datam, labelsm);


        }
    });
}

var ct = document.getElementById("chart-material-all");
getChartDataMaterial();

function getChartDataMaterial() {
    $.ajax({
        url: "chartMaterial",
        dataType: 'json',
        success: function(result) {
            renderChartMaterial(result.tFWA, result.tWiFi, result.tRAN, result.tCELLFI, result.fFWA, result.fWiFi, result.fRAN, result.fCELLFI);
        }
    });
}

function renderChartMaterial(tFWA, tWiFi, tRAN, tCELLFI, fFWA, fWiFi, fRAN, fCELLFI) {
    'use strict';
    if ($("#chart-material-all").length) {
        Morris.Bar({
            element: 'chart-material-all',
            barColors: ['#63CF72', '#F36368', '#76C1FA', '#FABA66'],
            data: [{
                    y: 'FWA',
                    a: tFWA,
                    b: fFWA
                },
                {
                    y: 'WiFi',
                    a: tWiFi,
                    b: fWiFi
                },
                {
                    y: 'RAN',
                    a: tRAN,
                    b: fRAN
                },
                {
                    y: 'Cell-Fi',
                    a: tCELLFI,
                    b: fCELLFI
                },
            ],
            xkey: 'y',
            ykeys: ['a', 'b'],
            labels: ['Disponibles', 'Utilizados']
        });
    }

}


var ct = document.getElementById("ct-chart-stacked-bar-app");
getChartDataMaterialByStoreAndInventoryId();

function getChartDataMaterialByStoreAndInventoryId() {
    $.ajax({
        url: "chartMaterialByStoreAndInventoryId",
        dataType: 'json',
        success: function(result) {

            //console.log(result.object);

            renderChartMaterialByStoreAndInventoryId(result.object);
        }
    });
}

var ct = document.getElementById("pie-chart-user");
getChartMovementsByUser();

function getChartMovementsByUser() {
    $.ajax({
        url: "chartMovementsByUser",
        dataType: 'json',
        success: function(result) {
            //console.log(result.obj);

            renderChartMovementsByUser(result.obj);
        }
    });
}


function renderChartMovementsByUser(dataT) {

    let data = [];
    let color = ["#ef5350","#78909c","#ffca28","#bdbdbd","#66bb6a","#ffa726","#26a69a","#7e57c2","#ffee58","#8d6e63","#42a5f5","#ec407a"];

    for (let i = 0; i < dataT[0].length; i++) {
        console.log(dataT[0][i]);
        data[i] = {data: dataT[0][i][0],color: color[i],label: dataT[0][i][1]};
    }

    'use strict';

    if ($("#pie-chart-user").length) {
        $.plot("#pie-chart-user", data, {
            series: {
                pie: {
                    show: true,
                    radius: 1,
                    label: {
                        show: true,
                        radius: 3 / 4,
                        formatter: labelFormatter,
                        background: {
                            opacity: 0.5
                        }
                    }
                }
            },
            legend: {
                show: false
            }
        });
    }

    function labelFormatter(label, series) {
        return "<div style='font-size:8pt; text-align:center; padding:2px; color:black;'>" + label + "<br/>" + Math.round(series.percent) + "%</div>";
    }

}

function renderChartMaterialByStoreAndInventoryId(data) {
    //Stacked bar Chart
    if ($('#ct-chart-stacked-bar-app').length) {
        new Chartist.Bar('#ct-chart-stacked-bar-app', {
            labels: ['ILG', 'BOC', 'AGUADULCE', 'C. RADIAL', 'TERRONAL'],
            series: [
                [data[0][0], data[0][4], data[0][8], data[0][12], data[0][16]],
                [data[0][1], data[0][5], data[0][9], data[0][13], data[0][17]],
                [data[0][2], data[0][6], data[0][10], data[0][14], data[0][18]],
                [data[0][3], data[0][7], data[0][11], data[0][15], data[0][19]]
            ]
        }, {
            stackBars: true,
            showLabel: true,
            axisY: {
                labelInterpolationFnc: function(value) {
                    return (value);
                },
                onlyInteger: true
            }
        }).on('draw', function(data) {
            if (data.type === 'bar') {
                data.element.attr({
                    style: 'stroke-width: 30px'
                });
            }
        });
    }

    if ($("#chart-material-by-store-inventory").length) {
        Morris.Bar({
            element: 'chart-material-by-store-inventory',
            barColors: ['#63CF72', '#F36368', '#76C1FA', '#FABA66'],
            data: [{
                    y: 'ILG',
                    a: data[0][0],
                    b: data[0][1],
                    c: data[0][2],
                    d: data[0][3]
                },
                {
                    y: 'BOC',
                    a: data[0][4],
                    b: data[0][5],
                    c: data[0][6],
                    d: data[0][7]
                },
                {
                    y: 'AGUADULCE',
                    a: data[0][8],
                    b: data[0][9],
                    c: data[0][10],
                    d: data[0][11]
                },
                {
                    y: 'C. RADIAL',
                    a: data[0][12],
                    b: data[0][13],
                    c: data[0][14],
                    d: data[0][15]
                },
                {
                    y: 'TERRONAL',
                    a: data[0][16],
                    b: data[0][17],
                    c: data[0][18],
                    d: data[0][19]
                }
            ],
            xkey: 'y',
            ykeys: ['a', 'b', 'c', 'd'],
            labels: ['FWA', 'WIFI', 'RAN', 'CELL-FI']
        });
    }

    if ($("#chart-mat-by-store-inventory-app").length) {
        var ctx = document.getElementById('chart-mat-by-store-inventory-app').getContext("2d");
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['ILG', 'BOC', 'AGUADULCE', 'C. RADIAL', 'TERRONAL'],
                datasets: [{
                        label: "FWA",
                        borderColor: 'rgba(77, 124, 255, .8)',
                        backgroundColor: 'rgba(77, 124, 255, .8)',
                        pointRadius: 0,
                        fill: true,
                        borderWidth: 1,
                        fill: 'origin',
                        data: [data[0][0], data[0][4], data[0][8], data[0][12], data[0][16]]
                    },
                    {
                        label: "WIFI",
                        borderColor: 'rgba(255, 0, 0)',
                        backgroundColor: 'rgba(255, 0, 0)',
                        pointRadius: 0,
                        fill: true,
                        borderWidth: 1,
                        fill: 'origin',
                        data: [data[0][1], data[0][5], data[0][9], data[0][13], data[0][17]]
                    },
                    {
                        label: "RAN",
                        borderColor: 'rgba(238, 127, 35)',
                        backgroundColor: 'rgba(238, 127, 35)',
                        pointRadius: 0,
                        fill: true,
                        borderWidth: 1,
                        fill: 'origin',
                        data: [data[0][2], data[0][6], data[0][10], data[0][14], data[0][18]]
                    },
                    {
                        label: "CELL-FI",
                        borderColor: 'rgba(99, 207, 114)',
                        backgroundColor: 'rgba(99, 207, 114)',
                        pointRadius: 0,
                        fill: true,
                        borderWidth: 1,
                        fill: 'origin',
                        data: [data[0][3], data[0][7], data[0][11], data[0][15], data[0][19]]
                    }
                    /*,
                                        {
                                            label: "TERRONAL",
                                            borderColor: 'rgba(25, 116, 148, 1)',
                                            backgroundColor: 'rgba(25, 116, 148, 1)',
                                            pointRadius: 0,
                                            fill: true,
                                            borderWidth: 1,
                                            fill: 'origin',
                                            data: [data[0][16], data[0][17], data[0][18], data[0][19]]
                                        }*/
                ]
            },
            options: {
                maintainAspectRatio: false,
                legend: {
                    display: false,
                    position: "top"
                },
                scales: {
                    xAxes: [{
                        ticks: {
                            display: true,
                            beginAtZero: true,
                            fontColor: '#696969'
                        },
                        gridLines: {
                            display: false,
                            drawBorder: false,
                            color: 'transparent',
                            zeroLineColor: '#eeeeee'
                        }
                    }],
                    yAxes: [{
                        gridLines: {
                            drawBorder: false,
                            display: true,
                            color: '#b8b8b8',
                        },
                        categoryPercentage: 0.5,
                        ticks: {
                            display: true,
                            beginAtZero: true,
                            stepSize: 200,
                            max: 2000,
                            fontColor: '#696969'
                        }
                    }]
                },
            },
            elements: {
                point: {
                    radius: 0
                }
            }
        });
        document.getElementById('js-legend').innerHTML = myChart.generateLegend();
    }

}