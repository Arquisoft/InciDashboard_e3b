#Instalar postgres
#sudo apt install postgresql
#
#Si hay problemas con el  usuario hacer lo siguiente:
#sudo -u postgres psql
#ALTER USER postgres PASSWORD 'changeit';
createdb -h localhost -p 5432 -U postgres postgres