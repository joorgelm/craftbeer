#!/bin/bash

export POSTGRES_DB_NAME=craftbeer
export POSTGRES_USER=pgadmin
export POSTGRES_PASSWORD=pgadmin
export DATABASE_PORT=5432
export DATABASE_HOST=postgresql

mvn clean package -Dmaven.test.skip=true

docker-compose up --build
