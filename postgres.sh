#!/bin/sh
docker run  --name postgres-db -d --network telegram-bot -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres  -e POSTGRES_DB=telegram --rm -p 5432:5432 postgres
