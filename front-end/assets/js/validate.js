document.addEventListener("DOMContentLoaded", function() {
  document.getElementById("msform").addEventListener('submit', validarFormulario);
});

function validarFormulario(evento) {
  evento.preventDefault();
  console.log('antes del error')
  var cliente = document.getElementById('clientId').value;
  if(cliente.length == 0) {
    alert('debe seleccionar un client');
    return;
  }

  this.submit();
}