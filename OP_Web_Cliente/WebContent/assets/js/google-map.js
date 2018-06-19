//Declaramos las variables que vamos a user
var lat = null;
var lng = null;
var map = null;
var geocoder = null;
var marker = null;
         
$(function(){
     //obtenemos los valores en caso de tenerlos en un formulario ya guardado en la base de datos
     lat = $('#lat').val();
     lng = $('#long').val();
     //Asignamos al evento click del boton la funcion codeAddress
     $('#pasar').click(function(){
        codeAddress();
        return false;
     });
     //Inicializamos la función de google maps una vez el DOM este cargado
    initialize();
});
     
function initialize() {
     
  geocoder = new google.maps.Geocoder();

  if(lat !='' && lng != ''){
    var latLng = new google.maps.LatLng(lat,lng);
  }else{
    //UBICACION POR DEFAULT FUNDACION UADE
    var latLng = new google.maps.LatLng(-34.6170941,-58.3820478);
  }


  //Definimos algunas opciones del mapa a crear
  var myOptions = {
    center: latLng,//centro del mapa
    zoom: 15,//zoom del mapa
    mapTypeId: google.maps.MapTypeId.ROADMAP //tipo de mapa, carretera, híbrido,etc
  };
  //creamos el mapa con las opciones anteriores y le pasamos el elemento div
  map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

  //creamos el marcador en el mapa
  marker = new google.maps.Marker({
    map: map,//el mapa creado en el paso anterior
    position: latLng,//objeto con latitud y longitud
    draggable: true //que el marcador se pueda arrastrar
  });

  //función que actualiza los input del formulario con las nuevas latitudes
  //Estos campos suelen ser hidden
  updatePosition(latLng);

  //Añado un listener para cuando el markador se termine de arrastrar
  //actualize el formulario con las nuevas coordenadas
  google.maps.event.addListener(marker, 'dragend', function(){
    updatePosition(marker.getPosition());
  });

  google.maps.event.addListener(map, 'click', function(event) {
    geocoder.geocode(
        {'latLng': event.latLng},
        function(results, status) {
          if (status == google.maps.GeocoderStatus.OK) {
            if (results[0]) {
              if (marker) {
                marker.setPosition(event.latLng);
              } else {
                marker = new google.maps.Marker({
                   position: event.latLng,
                   map: map});
              }
              updatePosition(marker.getPosition());
            } else {
              document.getElementById('geocoding').innerHTML =
                  'No se encontraron resultados';
            }
          } else {
            document.getElementById('geocoding').innerHTML =
                'Geocodificación  ha fallado debido a: ' + status;
          }
        });
  });
}

function showPosition(position) {
  var latLng = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);

  //Definimos algunas opciones del mapa a crear
  var myOptions = {
    center: latLng,//centro del mapa
    zoom: 15,//zoom del mapa
    mapTypeId: google.maps.MapTypeId.ROADMAP //tipo de mapa, carretera, híbrido,etc
  };

  //creamos el mapa con las opciones anteriores y le pasamos el elemento div
  map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

  //creamos el marcador en el mapa
  marker = new google.maps.Marker({
    map: map,//el mapa creado en el paso anterior
    position: latLng,//objeto con latitud y longitud
    draggable: true //que el marcador se pueda arrastrar
  });

  //función que actualiza los input del formulario con las nuevas latitudes
  //Estos campos suelen ser hidden
  updatePosition(latLng);

}
     
    //funcion que traduce la direccion en coordenadas
function codeAddress() {
  //obtengo la direccion del formulario
  var address = document.getElementById("direccion").value;
  //hago la llamada al geodecoder
  geocoder.geocode({ 'address': address}, function(results, status) {

    //si el estado de la llamado es OK
    if (status == google.maps.GeocoderStatus.OK) {
      //centro el mapa en las coordenadas obtenidas
      map.setCenter(results[0].geometry.location);
      //coloco el marcador en dichas coordenadas
      marker.setPosition(results[0].geometry.location);
      //actualizo el formulario      
      updatePosition(results[0].geometry.location);

    }else{
    //si no es OK devuelvo error
      alert("No podemos encontrar la direcci&oacute;n, error: " + status);
    }
  });
}
   
//funcion que simplemente actualiza los campos del formulario
function updatePosition(latLng){
  $('#lat').val(latLng.lat());
  $('#long').val(latLng.lng());
  //alert($('#lat').val());
  //alert($('#long').val());
}