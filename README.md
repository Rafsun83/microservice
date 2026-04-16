# Microservice Project

A Spring Boot microservices project with load balancing via Nginx, MySQL, and MongoDB — all containerized with Docker Compose.

---

## Architecture Overview

```
Client
  └── Nginx (HTTPS :443 / HTTP :8081)
        ├── /self_management/ → self_management_1 | self_management_2  (least-conn load balanced)
        └── /narcos_team/     → narcos_1
              │
              ├── self_management_1 & _2 → MySQL (port 3306)
              └── narcos_1              → MongoDB (port 27017)
```

| Component            | Technology          | Role                                       |
|----------------------|---------------------|--------------------------------------------|
| `self_management`    | Spring Boot + MySQL | User, Task, Book, Wallet, Blog management  |
| `narcos`             | Spring Boot + MongoDB | Narcos members service                   |
| `nginx`              | Nginx               | Reverse proxy + SSL termination + LB       |
| `mysql`              | MySQL 8.0.36        | Database for self_management               |
| `mongodb`            | MongoDB 7           | Database for narcos                        |

---

## Prerequisites

Make sure you have the following installed:

- [Docker](https://docs.docker.com/get-docker/) (v20+)
- [Docker Compose](https://docs.docker.com/compose/install/) (v2+)

---

## Project Structure

```
microservice/
├── docker-compose.yml
├── nginx/
│   ├── default.conf        # Nginx reverse proxy config
│   └── certs/
│       ├── api.myapp.com.pem
│       └── api.myapp.com-key.pem
├── self_management/        # Spring Boot service (MySQL)
│   └── Dockerfile
└── narcos/                 # Spring Boot service (MongoDB)
    └── Dockerfile
```

---

## SSL Certificate Setup

The project uses HTTPS with a self-signed certificate for `api.myapp.com`. The certificate files are already present in `nginx/certs/`. If you need to regenerate them, use [mkcert](https://github.com/FiloSottile/mkcert):

```bash
mkcert api.myapp.com
mv api.myapp.com.pem nginx/certs/
mv api.myapp.com-key.pem nginx/certs/
```

Also add `api.myapp.com` to your `/etc/hosts` to resolve it locally:

```bash
echo "127.0.0.1  api.myapp.com" | sudo tee -a /etc/hosts
```

---

## Running the Project

```bash
# Clone the repository and navigate to the project root
cd microservice

# Build images and start all services
docker compose up --build -d

# Check running containers
docker compose ps

# View logs
docker compose logs -f
```

---

## API Endpoints

All requests go through Nginx. The base URL is `https://api.myapp.com`.

### Self Management Service
| Method | Endpoint                                | Description           |
|--------|-----------------------------------------|-----------------------|
| POST   | `/self_management/api/auth/...`         | Authentication        |
| GET    | `/self_management/api/users/...`        | User management       |
| GET    | `/self_management/api/tasks/...`        | Task management       |
| GET    | `/self_management/api/books/...`        | Book management       |
| GET    | `/self_management/api/wallet/...`       | Wallet management     |
| GET    | `/self_management/api/blog/...`         | Blog management       |

### Narcos Service
| Method | Endpoint                       | Description        |
|--------|--------------------------------|--------------------|
| GET    | `/narcos_team/api/hello`       | Hello from narcos  |

---

## Stopping the Project

```bash
# Stop all services
docker compose down

# Stop and remove volumes (wipes database data)
docker compose down -v
```
