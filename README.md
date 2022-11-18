#Parking - Control

#Run Database
Open terminal:
1º docker pull mesfind/postgres
2º docker run --name parking-db -p 5432:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d mesfind/postgres:10-alpine

3º Tack IPAddress to connect docker image with  pgadmin: 
	- docker ps:  copy container value
	- docker inspect container value
	- copy IPAddress

#Run pgAdmin
Open terminal:
1º docker pull dpage/pgadmin4
2º docker run -p 5050:80 -e "PGADMIN_DEFAULT_EMAIL=user@domain.com" -e "PGADMIN_DEFAULT_PASSWORD=123" -d dpage/pgadmin4
3º In browser: http://127.0.0.1:5050/ - put email and password created before
4º click on the server and choose Register option
5º In General put your server name
6º In Connection:
    - Hostname/Address: IPAdress copied before
	- Username: admin
	- Password: 123
And save 