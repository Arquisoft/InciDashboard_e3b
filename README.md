# InciDashboard_e3b
[![Build Status](https://travis-ci.org/Arquisoft/InciDashboard_e3b.svg?branch=master)](https://travis-ci.org/Arquisoft/InciDashboard_e3b)
[![codecov](https://codecov.io/gh/Arquisoft/InciDashboard_e3b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciDashboard_e3b)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/20f4862789f44608a8d6781dcacfda57)](https://www.codacy.com/app/UO252010/InciDashboard_e3b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/InciDashboard_e3b&amp;utm_campaign=Badge_Grade)
[![Gitter](https://badges.gitter.im/Arquisoft/InciDashboard_e3b.svg)](https://gitter.im/inciDashboard_e3b/Lobby?utm_source=share-link&utm_medium=link&utm_campaign=share-link)


InciDashboard_e3b
# Autores 2017-2018
+ Diego Álvarez Guinarte (UO251682)
+ Manuel Junco Diez (UO252010)
+ Ivan Suarez Castiñeiras (UO244730)
+ Pablo Gonzalez Martinez (UO245699)


### Requisitos para ejecutar el proyecto
- [Apache Maven](https://maven.apache.org) (versión: >= 3.5).
- [Apache Kafka](https://kafka.apache.org) (versión: >= 1.0).
- [Hsqldb](http://hsqldb.org/) (version : 2.4.0).

#### Inicio con Apache Kafka en MS-Windows

~~~batchfile
REM Start Apache Zookeeper server:
start "ZooKeeper" /D ".\bin\windows\" "zookeeper-server-start.bat" "..\..\config\zookeeper.properties"
REM Wait 10 seconds:
timeout 10
REM Start Apache Kafka server:
start "Kafka" /D ".\bin\windows\" "kafka-server-start.bat" "..\..\config\server.properties"
~~~

### Inicio del servicio InciDashboard
Situarse en el directorio donde se encuentre el proyecto.
~~~
mvn spring-boot:run
~~~


## Datos de prueba

### Datos del operario
|Email del operario      |       Contraseña       | 
|------------------------|------------------------|
|oper12@gmail.es         |         123456         | 

### Datos de las incidencias
|Nombre de la incidencia |       Descripcion      |  Localizacion  | Agente emisor | Operario asignado |
|------------------------|------------------------|----------------|---------------|-------------------|
|Incidencia1             |       Descripcion1     |     21,33      | Agent1        | oper12@gmail.es   |
|Incidencia2             |       Descripcion2     |     60,43      | Agent2        | oper12@gmail.es   |
|Incidencia3             |       Descripcion3     |     -8,-99     | Agent3        | oper12@gmail.es   |
|Incidencia4             |       Peligro	      |     -8,-99     | Agent3        | oper12@gmail.es   |

### Datos de los agentes

|Nombre del agente      |       Email               |  Identificador  |  Tipo   |
|-----------------------|---------------------------|-----------------|---------|
|Agent1                 |  agent121223221@gmail.com |  Ag111223221    | Person  |
|Agent2                 |  agent221223221@gmail.com |  Ag221223221    | Sensor  |
|Agent3                 |  agent321223221@gmail.com |  Ag331223221    | Entity  |