(function($) {
  'use strict';
  var form = $("#example-form");
  form.children("div").steps({
    headerTag: "h3",
    bodyTag: "section",
    transitionEffect: "slideLeft",
    onFinished: function(event, currentIndex) {
      alert("Submitted!");
    }
  });
  var validationForm = $("#example-validation-form");
  validationForm.val({
    errorPlacement: function errorPlacement(error, element) {
      element.before(error);
    },
    rules: {
      confirm: {
        equalTo: "#password"
      }
    }
  });
  validationForm.children("div").steps({
    headerTag: "h3",
    bodyTag: "section",
    transitionEffect: "slideLeft",
    onStepChanging: function(event, currentIndex, newIndex) {
      validationForm.val({
        ignore: [":disabled", ":hidden"]
      })
      return validationForm.val();
    },
    onFinishing: function(event, currentIndex) {
      validationForm.val({
        ignore: [':disabled']
      })
      return validationForm.val();
    },
    onFinished: function(event, currentIndex) {
      alert("Submitted!");
    }
  });






  var verticalForm = $("#formRegisterGenerator");

  function validarInputs($scope) {
          const inputs = $scope.find(':input[required]');
          let valid = true;

          inputs.each(function() {
              const input = $(this);

              if (this.tagName === 'SELECT' && input.hasClass('select2-hidden-accessible')) {
                  if (!input.val() || input.val() === '') {
                      valid = false;
                      input.next('.select2-container').addClass('select2-error');
                  } else {
                      input.next('.select2-container').removeClass('select2-error');
                  }
              } else {
                  if (!this.checkValidity()) {
                      valid = false;
                      input.addClass('is-invalid');
                  } else {
                      input.removeClass('is-invalid');
                  }
              }
          });

          return valid;
      }

  verticalForm.children("div").steps({
    headerTag: "h3",
    bodyTag: "section",
    transitionEffect: "slideLeft",
    stepsOrientation: "vertical",
    labels:{
        finish:"Guardar",
        next:"Siguiente",
        previous: "Anterior"
    },
    onInit: function() {
                verticalForm.find('.js-example-basic-single').each(function() {
                    const $sel = $(this);
                    if (!$sel.hasClass('select2-hidden-accessible')) {
                        $sel.select2({
                            width: '100%',
                            placeholder: 'Seleccione...',
                            allowClear: true,
                            dropdownParent: $('body')
                        });
                    }
                });
                console.log("→ Select2 inicializado en onInit");
            },

            onStepChanging: function(event, currentIndex, newIndex) {
                const section = verticalForm.find('section').eq(currentIndex);
                const valid = validarInputs(section);

                if (!valid) {
                    const firstInput = section.find(':input[required]')[0];
                    firstInput?.reportValidity?.();
                }

                return valid;
            },

            onFinished: function() {
                const valid = validarInputs(verticalForm);

                if (valid) {
                    verticalForm[0].submit();
                } else {
                    const firstInvalid = verticalForm.find(':input[required]')[0];
                    firstInvalid?.reportValidity?.();
                }
            }
  });

})(jQuery);