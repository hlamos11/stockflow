var ctx = document.getElementById("mytable");
console.log('ctxHLA: ', ctx);

var tbl = $('table#mytable tr').map(function() {
  return $(this).find('td').map(function() {
    return $(this).text();
  }).get();
}).get();

console.log('tblHLA: ', tbl);