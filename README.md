# KnowHub (Offline Q&A + Experiences)
An offline-friendly knowledge + Q&A hub for your local network.

## Stack
- Backend: Spring Boot (Java 21), Maven, JWT auth, RBAC, Flyway, MySQL 8
- Frontend: React + Vite + TypeScript
- Deployment: Docker Compose (db + api + web via Nginx)

## Quick start (Docker Compose)
```bash
cd deploy
docker compose up --build
```
- Web UI: http://localhost:8080
- API:    http://localhost:8080/api
- MySQL data persists in `db_data` volume.

## Local dev (without Docker)
- Start MySQL and create db `knowhub`.
- Update `backend/src/main/resources/application.yml` as needed.
- Backend: run from IDE or `mvn spring-boot:run`.
- Frontend: `cd frontend && npm i && npm run dev` (proxy to API via `/api`).

## Default credentials
On first launch, Flyway seeds roles and an `admin` user with password `admin123`.
Change it immediately.
