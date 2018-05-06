var eventSource = new EventSource("/getEmitter");

eventSource.addEventListener("event", function(event) {

	var jsonIncidencia = JSON.parse(event.data);

	var tabla = document.getElementById("tableIncidencias");
	var tamTabla = tabla.rows.length;
	var row = tabla.insertRow( tamTabla );	
	
	var estado = row.insertCell(0);
	var agente = row.insertCell(1);
	var nombre = row.insertCell(2);
	var descripcion = row.insertCell(3);
	var localizacion = row.insertCell(4);
	var etiquetas = row.insertCell(5);

	
	estado.innerHTML =  '<td>' + jsonIncidencia.status + '</td>';
	agente.innerHTML = '<td>' + jsonIncidencia.username + '</td>';
	nombre.innerHTML = '<td>' + jsonIncidencia.incidenceName + '</td>';
	descripcion.innerHTML = '<td>' + jsonIncidencia.description + '</td>';
	localizacion.innerHTML = '<td>' + jsonIncidencia.location + '</td>';
	etiquetas.innerHTML = '<td>' + prueba de etiquetas + '</td>';

});