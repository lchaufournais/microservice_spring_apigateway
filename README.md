## Introduction

Le projet est un prototype d'un programme utilisants plusieurs microservices. Il n'a pas pour finalité d'être utilisé. De plus il est perfectible en ajoutant par exemple un autre microservice centralisant les configs de chaque microservice.

## EXO_MICROSERVICE_EUREKA_LOIC

Microservice Eureka permettant d'avoir l'url des ends points fix. Ainsi en cas que changement de ports ou de configuration les différents microservices peuvent quand même être appelé.

## EXO_MICROSERVICE_SCHOOL_LOIC

API Rest utilisant postgres

## EXO_MICROSERVICE_STUDENT_LOIC

API Rest utilisant mangodb

##api-gateway

La gateway permet de centraliser tous les end points sur la même base url et le même port. C'est aussi ici que nous allons vérifier les autorisations (jwt)

##auth-service

Service responsable de l'authentification, elle permet de de se connecter et générer un token.
