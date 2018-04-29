# language: es
Característica: Lista de incidencias de los operarios

 Como operario logueado quiero acceder a mis 
  incidencias asignadas.
  
  Esquema del escenario: Consulta de informacion 
  Dado un operario logueado en el sistema con email "<email>"
  Cuando invoco el servicio con la solicitud de ver las incidencias
  Entonces el servicio devuelve la vista con la tabla de sus incidencias