# language: es
Característica: Autenticación de operarios

 Como operario registrado en el sistema,
 quiero acceder a cualquiera de los servicios para
 poder gestionar las incidencias asignadas.
 
 @autenticacion
Esquema del escenario: Consulta de información por parte de un operario
  Dado un operario previamente registrado en el sistema con email :"<email>" y contraseña: "<password>"
  Cuando inicia sesion introduce "<email>" y "<contraseña"
  Entonces se loguea correctamente