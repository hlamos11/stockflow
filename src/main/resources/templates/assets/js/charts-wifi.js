var ctx = document.getElementById("ChartTop5Establishments");
getChartData() ;

function renderChart(data, labels) {

    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [
                {
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
            datasets: [
                {
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
        success: function (result) {

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

