function quantity() {
    var select = document.getElementById('selector');
    for (var i = 0; i <= 99; i++) {
      select.options[select.options.length] = new Option(i , i);
    }

    var selectPoe = document.getElementById('selectorPoe');
    for (var i = 0; i <= 99; i++) {
          selectPoe.options[selectPoe.options.length] = new Option(i , i);
        }

    var selectSwitch = document.getElementById('selectorSwitch');
    for (var i = 0; i <= 99; i++) {
      selectSwitch.options[selectSwitch.options.length] = new Option(i , i);
    }

    var selectSwitch = document.getElementById('selectorInventory');
    for (var i = 2020; i <= 2024; i++) {
      selectSwitch.options[selectSwitch.options.length] = new Option(i + 1, i);
    }
}

window.onload = quantity;