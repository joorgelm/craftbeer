#!/bin/bash

export POSTGRES_DB_NAME=craftbeer
export POSTGRES_USER=craftbeer
export POSTGRES_PASSWORD=craftbeer
export DATABASE_PORT=5432
export DATABASE_HOST=beerhouse-database

mvn clean package -Dmaven.test.skip=true

docker-compose up --build
