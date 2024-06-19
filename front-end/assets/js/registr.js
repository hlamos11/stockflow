$(document).ready(function(){

	var current_fs, next_fs, previous_fs; //fieldsets
	var opacity;
	var current = 1;
	var steps = $("fieldset").length;
	var inputClient, inputProvince, inputTypeBusiness, inputTypeName, status;



	setProgressBar(current);

	$(".next").click(function(){

	    console.log('current :',current);


        validate = validate_steps(current);
        console.log('validate :',validate);
        if(validate){
            return;
        }




		current_fs = $(this).parent();
		next_fs = $(this).parent().next();

		//Add Class Active
		$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

		//show the next fieldset
		next_fs.show();
		//hide the current fieldset with style
		current_fs.animate({opacity: 0}, {
			step: function(now) {
			// for making fielset appear animation
			opacity = 1 - now;

			current_fs.css({
			'display': 'none',
			'position': 'relative'
			});
			next_fs.css({'opacity': opacity});
			},
			duration: 500
		});
		setProgressBar(++current);
	});

	$(".previous").click(function(){

		current_fs = $(this).parent();
		previous_fs = $(this).parent().prev();

		//Remove class active
		$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

		//show the previous fieldset
		previous_fs.show();

		//hide the current fieldset with style
		current_fs.animate({opacity: 0}, {
		step: function(now) {
		// for making fielset appear animation
		opacity = 1 - now;

		current_fs.css({
		'display': 'none',
		'position': 'relative'
		});
		previous_fs.css({'opacity': opacity});
		},
		duration: 500
		});
		setProgressBar(--current);
	});

	function setProgressBar(curStep){
		var percent = parseFloat(100 / steps) * curStep;
		percent = percent.toFixed();
		$(".progress-bar")
		.css("width",percent+"%")
	}

	$(".submit").click(function(){
		return false;
	})

});

function add(){

    var lastmille = document.getElementById("lastmille").value;
    var selector = document.getElementById("selector").value;
    var modelap = document.getElementById("modelap").value;
    var selectorPoe = document.getElementById("selectorPoe").value;
    var modelswitch = document.getElementById("modelswitch").value;
    var selectorSwitch = document.getElementById("selectorSwitch").value;

    var lb_cantap = document.getElementById("lbcantAP");
    var lb_modelap = document.getElementById("lbmodelAP");



    if(selector.trim()=='0'){
           //alert("La cantidad de APs debe ser mayor a 0");
           const input = document.getElementById('selector');
           lb_cantap.setAttribute('required', '');


           // ✅ Set required attribute
           input.setAttribute('required','');

           // ✅ Remove required attribute
           //input.removeAttribute('required');
           return;
    }

    if(modelap.trim()==''){
          alert("Ingrese el modelo del AP");
          return;
    }

    if(lastmille.trim()=='HFC'){
        if(selectorPoe.trim()=='0'){
            elem.style.color = 'red';
           alert("Ingrese la cantidad de PoE");
           return;
        }
    }else if (lastmille.trim()=='FIBRA'){
        if(modelswitch.trim()=='N/A'){
              alert("Ingrese el modelo de switch");
              return;
        }else{
           if(selectorSwitch.trim()=='0'){
              alert("Ingrese la cantidad de switch");
              return;
           }
        }
    }


    var fila="<tr><td id='countAp'>"+selector+"</td><td id='modelAp'>"+modelap+"</td><td id='countPoe'>"+selectorPoe+"</td><td id='modelSwitch'>"+modelswitch+"</td><td id='modelSwitch'>"+selectorSwitch+"</td>"+"<td><button type='button' onclick='deleteRow(this)' class='btn btn-danger'><i class='fa fa-trash'></i></button>"+"</td></tr>";

    var btn = document.createElement("TR");
   	btn.innerHTML=fila;
    document.getElementById("table_detail_establish").appendChild(btn);
    detail_save();


}


function deleteRow(r)
{
    var i=r.parentNode.parentNode.rowIndex
    document.getElementById('mytable').deleteRow(i)
    detail_save();
}

function detail_save(){
    const resume_table = document.getElementById("mytable");
    console.log('resume_table: ', resume_table);

    for (var i = 0, row; row = resume_table.rows[i]; i++) {
      //alert(cell[i].innerText);
      for (var j = 0, col; col = row.cells[j]; j++) {
        //alert(col[j].innerText);
        console.log(`Txt: ${col.innerText} \tFila: ${i} \t Celda: ${j}`);
      }
    }
}

function validate_steps(step)
{
var error = 0;

console.log('step:',step)

switch (step) {
  case 1:

    inputClient = document.getElementById('clientId').value;
	if(inputClient.trim()==''){
	    inputClient.required;
        document.getElementById("lb_client").innerHTML = "*";
	    error = error + 1;
	}

    inputProvince = document.getElementById('inputProvince').value;
	if(inputProvince.trim()==''){
        inputProvince.required;
        document.getElementById("lb_province").innerHTML = "*";
        error = error + 1;
    }

    inputTypeBusiness = document.getElementById('inputTypeBusiness').value;
    if(inputTypeBusiness.trim()==''){
        inputTypeBusiness.required;
        document.getElementById("lb_typebusiness").innerHTML = "*";
        error = error + 1;
    }

    inputTypeName = document.getElementById('inputTypeName').value;
    if(inputTypeName.trim()==''){
        inputTypeName.required;
        document.getElementById("lb_name").innerHTML = "*";
        error = error + 1;
    }

    status = document.getElementById('status').value;
    if(status.trim()==''){
       status.required;
       document.getElementById("lb_status").innerHTML = "*";
       error = error + 1;
    }
    break;
  case 2:

    break;

  case 3:

    break;
  default:
    console.log('default');
    break;
}


    if (error>0){
        error =0
        return true;
    }else{
        return false;
    }

}