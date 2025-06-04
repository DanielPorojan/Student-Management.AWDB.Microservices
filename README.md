# Student Management Microservices System

Acesta este un sistem distribuit dezvoltat în Java, bazat pe arhitectura de microservicii. Proiectul gestionează diferite aspecte ale unei aplicații universitare: autentificare, studenți, profesori, cursuri, note și interfață utilizator. Fiecare microserviciu are propria bază de date și este orchestrat printr-un sistem central de descoperire și gateway API.

---

## 🧩 Microservicii componente

Proiectul este format din următoarele servicii:

### 1. **API Gateway (`api-gateway`)**
- Responsabil pentru rutarea cererilor către microserviciile interne.
- Asigură logica de autentificare și autorizare la nivel global.
- Implementat probabil cu Spring Cloud Gateway.

### 2. **Service Discovery (`discovery-server`)**
- Utilizează Eureka pentru înregistrarea și descoperirea serviciilor.
- Facilitează scalarea dinamică și comunicarea între microservicii.

### 3. **Config Server (`config-server`)**
- Centralizează fișierele de configurare pentru toate microserviciile.
- Permite actualizarea configurației fără redeploy.

### 4. **Auth Service (`auth-service`)**
- Gestionează autentificarea și autorizarea utilizatorilor.
- Probabil implementează JWT (JSON Web Tokens) pentru securitate.

### 5. **Student Service (`student-service`)**
- Gestionează informațiile despre studenți.
- CRUD pentru înregistrarea, modificarea și ștergerea studenților.

### 6. **Professor Service (`professor-service`)**
- Administrează informațiile despre profesori.

### 7. **Course Service (`course-service`)**
- Gestionează cursurile disponibile.
- Asocieri între cursuri, profesori și studenți.

### 8. **Grade Service (`grade-service`)**
- Administrează notele atribuite studenților.

### 9. **UI Service (`ui-service`)**
- Interfața frontend care consumă serviciile REST.
- Probabil o aplicație Spring MVC sau un client web extern integrat.

---
## 🌐 Porturi Microservicii

| Microserviciu         | Port | Fișier de configurare                                  |
|------------------------|------|--------------------------------------------------------|
| **API Gateway**        | `8080` | `api-gateway/src/main/resources/application.yml`       |
| **Auth Service**       | `8090` | `auth-service/src/main/resources/application.yml`      |
| **UI Service**         | `8091` | `ui-service/src/main/resources/application.yml`        |
| **Student Service**    | `8092` | `student-service/src/main/resources/application.yml`   |
| **Professor Service**  | `8093` | `professor-service/src/main/resources/application.yml` |
| **Course Service**     | `8094` | `course-service/src/main/resources/application.yml`    |
| **Grade Service**      | `8095` | `grade-service/src/main/resources/application.yml`     |
| **Discovery Server**   | `8761` | `discovery-server/src/main/resources/application.yml`  |
| **Config Server**      | `8888` | `config-server/src/main/resources/application.yml`     |

---

## 🐳 Docker & Orchestration

Fișierul `docker-compose-microservices.yml` permite rularea întregii aplicații local, folosind containere Docker pentru fiecare serviciu și bazele lor de date.

### Comenzi utile:
```bash
docker-compose -f docker-compose-microservices.yml up --build
