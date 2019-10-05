include MakefileDocumentation

run: ##@application Starts API Locally
	./mvnw clean package && docker-compose up -d local-api db

stop: ##@application Stop all containers.
	docker-compose down

build: ##@application Creates docker containers.
	docker-compose build

container: ##@helpers Do a docker exec bash inside container.
	docker exec -it viridis_api /bin/sh

tests: ##@tests Run all tests
	./mvnw clean package && docker-compose up -d local-api db

psql: ##@helpers Start a postgres client
	docker exec -it viridis_db /bin/bash -c 'psql exercise viridis'
