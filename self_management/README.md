## Spring-boot project run by command
``./mvnw spring-boot:run``
## Maven install in your application
``sudo apt install maven``
## Clean maven or build

``mvn clean``
## Added swagger for API Documentation 
``https://medium.com/javaguides/generate-rest-api-documentation-for-spring-boot-project-using-swagger-996e48fe0cea``
### Access Swagger
``http://localhost:8080/swagger-ui/index.html#/``

# (Spring security)
``Basic AUTH: Added spring security dependency and set password in application properties file instead of defaults user and password``
## 🔐 JWT Authentication Structure
```text
src/main/java
└── com/example/selfmanagement
    ├── config
    │   └── SecurityConfig.java       # Security filtering & filter chain setup
    ├── security
    │   └── jwt
    │       ├── JwtAuthFilter.java    # Request interceptor for token validation
    │       └── JwtService.java       # Token generation & parsing logic
    ├── controller
    │   └── AuthController.java       # Authentication endpoints (Login/Sign-up)
    └── dto
        ├── LoginRequest.java         # DTO for incoming credentials
        └── JwtResponse.java          # DTO for outgoing JWT tokens
```

### 📁 `config`
- **SecurityConfig.java**  
  Configures Spring Security, enables JWT-based authentication, disables session management, and registers the JWT filter.

### 📁 `security/jwt`
- **JwtService.java**  
  Responsible for generating, parsing, and validating JWT tokens.

- **JwtAuthFilter.java**  
  Intercepts incoming requests, extracts JWT from the `Authorization` header, validates it, and sets authentication in the security context.

### 📁 `controller`
- **AuthController.java**  
  Exposes authentication endpoints (e.g., login) and issues JWT tokens after successful authentication.

### 📁 `dto`
- **LoginRequest.java**  
  Data Transfer Object for receiving login credentials.

- **JwtResponse.java**  
  Data Transfer Object for returning the generated JWT token to the client.

### 📁 `JWT Generate note:`
- **JWT-**
  Pass userId and username for generate jwt token. Implemented here. 

#### `Why Utility Classes Should Be final`
- **Are not meant to be inherited** 
- **Contain static methods only** 
- **Represent helpers, not domain objects** 
- **No other class can extend this class** 

#### `JWT Logout & Token Invalidation (simple + practical)`

JWT is stateless, so you cannot “delete” a token on the server like session logout.
You must invalidate it logically.

### 📁 `There are 3 real-world ways to implement logout`
✅ OPTION 1: Short-Lived Access Token (RECOMMENDED SIMPLE WAY)

How it works:
* Access token expires quickly (e.g. 15–30 min)
* Logout = client deletes token

Steps:
1. User clicks logout
2. Frontend deletes token (localStorage / cookie)
3. Done

Pros:
* Very simple
* No DB needed

Cons:
* Token stays valid until it expires
👉 Best for small apps / MVP

✅ OPTION 2: Refresh Token + Logout (INDUSTRY STANDARD)

How it works:

* Access token (short-lived)
* Refresh token (stored in DB)
* Logout = revoke refresh token

Flow:

1. Login → issue access + refresh token
2. Store refresh token in DB (hashed)
3. Logout API:
* Delete refresh token from DB

1. Client deletes access token

Pros:

* Secure
* Proper logout
* Scales well

Cons
* Slightly more complex

👉 BEST PRACTICE for production

✅ OPTION 3: JWT Blacklist (Not Recommended at Scale)

How it works:

* Store JWT (or jti) in DB/Redis
* On every request → check blacklist

Pros:

* Immediate logout

Cons:

* DB/Redis lookup every request
* Loses JWT stateless benefit

👉 Use only if absolutely needed

### 🧠 `Important Notes`

* Never store access token in DB
* Always hash refresh tokens
* Use HttpOnly cookies if possible
* Rotate refresh tokens

Note: `jwt token is a acces token`

When people talk about:

* Access Token
* Refresh Token

They are talking about two different purposes, not two JWTs by default.

### Recommended Structure (Professional)
```text
com.example.authdemo
│
├── auth
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   └── security
│
├── course
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   └── dto
│
├── book
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   └── dto
│
├── common
│   ├── exception
│   ├── config
│   └── util
```

### Automatic Reload After Changes
1. Added DevTools Dependency
2. Go to IDE Settings → Build, Execution, Deployment → Compiler. and Check Build project automatically.
3. Go to advance setting bottom of the settings and check allow auto-make to start
4. Then reload or newly start IDE
5. Change any method and test.