Exercice :

Créer deux microservices.

Le premier micro service est school. C'est un CRUD classique avec postgresql (vous pouvez reprendre un ancien exo qu'on avait fait en Opti BDD).

Le deuxième est par contre différents, student :

Student est constitué de MongoDB. Le service est en CRUD lui aussi. Cependant, lorsque FindById est appelé, il va aussi récupérer l'école associé au student en question (l'autre service distant donc).

Voici les données que composes School :

    Id
    name
    adress
    directorName

Mongo -> JPA

CLass -> @Entity @Document

Voici les données que composes Student :

    Id
    Name
    Genre
    SchoolId