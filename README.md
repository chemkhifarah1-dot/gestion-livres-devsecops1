# Gestion des livres - Mini-projet DevSecOps

Application web Spring Boot de gestion d'une bibliotheque (livres, auteurs, emprunts),
servant de support a une chaine CI/CD securisee (Jenkins, SonarQube, Docker, Kubernetes).

## Technologies

- Java 17 (compile et teste avec OpenJDK 21), Spring Boot 3.3.2, Maven
- Spring Data JPA, Thymeleaf, MySQL
- Tests : JUnit 5 et Mockito

## Prerequis

- Ubuntu avec Java 21 et Maven
- MySQL avec une base nommee biblio
- Git

## Installation

1. Cloner le depot :

    git clone git@github.com:chemkhifarah1-dot/gestion-livres-devsecops1.git
    cd gestion-livres-devsecops1

2. Creer la base MySQL (parametres definis dans application.properties) :

    sudo mysql -e "CREATE DATABASE IF NOT EXISTS biblio;"

## Execution

    mvn spring-boot:run

Par defaut l'application ecoute sur le port 8081. Pour changer de port :

    mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8082

Pages principales : /livres, /auteurs, /emprunts

## Tests

    mvn test

## Organisation des branches

- main : version stable
- develop : integration du travail en cours
- feature/... : une branche par fonctionnalite

## Roadmap DevSecOps

- [x] Phase 1 : developpement et tests unitaires
- [x] Phase 2 : Git et GitHub
- [ ] Phase 3 : conteneurisation Docker
- [ ] Phase 4 : deploiement Kubernetes (Minikube)
- [ ] Phase 5 : pipeline Jenkins
- [ ] Phase 6 : securite (SonarQube, Dependency-Check, Trivy, OWASP ZAP)
