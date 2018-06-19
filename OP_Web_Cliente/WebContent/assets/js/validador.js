$(document).ready(function() {

    $('input').focus(function(){this.select()});
    $('input').click(function(){this.select()});

    $('.letras').keypress(function(tecla) {
        if(tecla.charCode > 48 || tecla.charCode < 57) return false;
    });

   $('.numero').keypress(function(tecla) {
        if(tecla.charCode < 48 || tecla.charCode > 57) return false;
    });

   $('.date').mask('00/00/0000');
   $('.time').mask('00:00:00');
   $('.date_time').mask('00/00/0000 00:00:00');
   $('.cep').mask('00000-000');
   $('.phone').mask('0000-0000');
   $('.phone_with_ddd').mask('(00) 0000-0000');
   $('.phone_us').mask('(000) 000-0000');
   $('.mixed').mask('AAA 000-S0S');
   $('.cpf').mask('000.000.000-00', {reverse: true});
   $('.money2').mask('000.000.000.000.000,00', {reverse: true});
   $('.money3').mask("#.##0,00", {reverse: true, maxlength: false});
   $('.ip_address').mask('0ZZ.0ZZ.0ZZ.0ZZ', {translation: {'Z': {pattern: /[0-9]/, optional: true}}});
   $('.ip_address').mask('099.099.099.099');
   $('.percent').mask('##0,00%', {reverse: true});
   $('.clear-if-not-match').mask("00/00/0000", {clearIfNotMatch: true});
   $('.placeholder').mask("00/00/0000", {placeholder: "__/__/____"});

   $(".placeholder, .date").focusout(function(fecha){
      var fechaf = fecha.split("/");
      var day = fechaf[0];
      var month = fechaf[1];
      var year = fechaf[2];
      var date = new Date(year,month,'0');

      if((day-0)>(date.getDate()-0)){
         alert("Fecha incorrecta");
         $(this).val = "";
         return false;
      }
      return true;
  });
});

obligatorio = function(){
  branch.brconsole.add("Valida campos obligatorios");
  var correcto=true;
  $('.obligatorio').each(function() {
    var valor = $(this).val();
    if($.trim(valor)==''){
      correcto=false;
      $(this).addClass('error');
      alert("Campo obligatorio");
      $(this).focus();
    }else{
      $(this).removeClass('error');
    }
  });
  return correcto;
}

setcontrolingreso = function (){
  
  $('.letras').keypress(function(tecla) {
    //if(tecla.keyCode > 48 || tecla.keyCode < 57)  return false;
    if(!(tecla.keyCode < 48 || tecla.keyCode > 57)) return false;
  });

  $('.numero').keypress(function(tecla) {
    if(tecla.keyCode < 48 || tecla.keyCode > 57) return false;
  });

  $("input[type=text]").keypress(function(tecla){
    if(tecla.keyCode == 13) $(this).trigger('blur');
  });

  //VERIFICA FECHA VALIDA
  $('.fecha').blur(function(){
    if($(this).val()!=''&&$(this).val().length<10){
      $(this).val('');
      return false;
    }
    $(this).trigger("keypress");
  });

  //MASCARA PARA LA FECHA
  $('.fecha').keypress(function(tecla) {
    if((tecla.keyCode < 48 || tecla.keyCode > 57) && (tecla.keyCode != 8 && tecla.keyCode != 9 && tecla.keyCode != 13)  ) return false;

    var nc = tecla.which==null?'':String.fromCharCode(tecla.which);
    var t = $(this).val()+nc;
    var val = t.split('/').join('');
    if($.isNumeric(val)){
      if(val.length==8){
        var dias = [31,28,31,30,31,30,31,31,30,31,30,31];
        var dia = parseInt(val.substring(0,2)); var mes = parseInt(val.substring(2,4)); var ano = parseInt(val.substring(4,8));
        if(mes>12){
          $(this).val('');
        }
        if(((ano%4)==0)&&((ano%100)!=0)||((ano%100)==0)&&(ano%400)==0){
          dias[1]=29;
        }
        if(dia>dias[mes-1]){
          $(this).val('');
          return false;
        }
      }
      if(t.length>10){
        $(this).val(t.substring(0, t.length-1));
        return false;
      }
      if(val.length<8){
        if(t.length==2||t.length==5){
          $(this).val(t+'/');
          return false;
        }
      }
    }else{
      $(this).val('');
      return false;
    }
    $(this).val($(this).val()+nc);
    return false;
  });

//VERIFICA CUENTA VALIDA
$(document).on('blur', '.cuenta', function() {
    var t = $(this).val();
    var val = t.split('-').join('');
    if($.isNumeric(val) && (val.length == 15)){
    }else{
      if ((val.length != 0) && (val.length < 15)){
        $(this).val('');
        alert("Cuenta invalida")
        return false;
      }
    }
  });

  //MASCARA PARA LA CUENTA
  $(document).on('keypress', '.cuenta', function(tecla) {
    if((tecla.keyCode < 48 || tecla.keyCode > 57) && (tecla.keyCode != 8 && tecla.keyCode != 9 && tecla.keyCode != 13)  ) return false;

    var nc = tecla.which==null?'':String.fromCharCode(tecla.which);
    var t = $(this).val()+nc;
    var val = t.split('-').join('');
    if($.isNumeric(val)){
      if(t.length>18){
        $(this).val(t.substring(0, t.length-1));
        return false;
      }

      if(val.length<15){
        if(t.length==1||t.length==5||t.length==16){
          $(this).val(t+'-');
          return false;
        }
      }
    }else{
      $(this).val('');
      return false;
    }
    $(this).val($(this).val()+nc);
    return false;
  });

  //VALIDA LARGO DE LA CUENTA
  $(document).on('change', '.cuenta', function() {
    var t = $(this).val()
    var val = t.split('-').join('');
    if($.isNumeric(val)){
      if(t.length>18){
        $(this).val(t.substring(0, t.length-1));
        return false;
      }
      
      if(val.length==15){
        var cta = branch.formatocta(val);
      }
    }else{
      $(this).val('');
      return false;
    }
    $(this).val(cta);
    return cta;
  });

  $('.cuil').blur(function() {
    var t = $(this).val();
    var val = t.split('-').join('');
    if($.isNumeric(val) && (val.length<12)){
      //EMPIEZO LOGICA DIGITO VERIFICADOR
      var VTTot = 0
      for (var i = 1; i < val.length; i++) {
          var VTNumero = val.substring(i -1, i)
          switch (i) {
              case 1:
              case 7:
                  VTNumero = VTNumero * 5
                  break;
              case 2:
              case 8:
                  VTNumero = VTNumero * 4
                  break;
              case 3:
              case 9:
                  VTNumero = VTNumero * 3
                  break;
              case 4:
              case 10:
                  VTNumero = VTNumero * 2
                  break;
              case 5:
                  VTNumero = VTNumero * 7
                  break;
              case 6:
                  VTNumero = VTNumero * 6 
                  break;
          }
          //Suma el total del numero
          VTTot = VTTot + VTNumero
       }
       //Divide el nuevo numero para 11
       var VTTot1 = VTTot % 11
       //Toma el residuo del nuevo numero
       if (VTTot1 == 0){
          VTTot = 0
       }   
       else{
          VTTot = 11 - VTTot1
          if (VTTot == 10) {
              VTTot = 0
          }
       }
       //Validar el ultimo numero de digito verificador
       if (val.substring(val.length -1, val.length) != VTTot){
          $(this).val('');
          alert("El CUIL ingresado es incorrecto")
          return false;
       }
      //TERMINO LOGICA DIGITO VERIFICADOR
    }else{
      $(this).val('');
      return false;
    }
  });

  //MASCARA PARA EL CUIL
  $('.cuil').keypress(function(tecla) {
    if((tecla.keyCode < 48 || tecla.keyCode > 57) && (tecla.keyCode != 8 && tecla.keyCode != 9 && tecla.keyCode != 13)  ) return false;

    var nc = tecla.which==null?'':String.fromCharCode(tecla.which);
    var t = $(this).val()+nc;
    var val = t.split('-').join('');
    if($.isNumeric(val)){
      if(t.length>13){
        $(this).val(t.substring(0, t.length-1));
        return false;
      }
      if(val.length<11){
        if(t.length==2||t.length==11){
          $(this).val(t+'-');
          return false;
        }
      }
    }else{
      $(this).val('');
      return false;
    }
    $(this).val($(this).val()+nc);
    return false;
  });
}