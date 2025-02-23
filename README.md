# Projet « RPG »

## État d'avancement

- [x] Système de combat
- [x] Système de repos
- [x] Système d’achats
- [x] Gestion de l'inventaire et de l'équipement
- [x] Génération de la documentation JavaDoc
- [x] Diagramme de classes UML généré
- [x] Diagramme de flux (à compléter)

## Diagramme de classes (UML)

![Diagramme UML](UML.png)

## Diagramme de flux

![Diagramme FLUX](flux.png)

## Difficultés rencontrées et solutions adoptées

### 1. Implémentation du système de combat
- **Problème** : Au départ, les attaques infligeaient peu de dégâts, ce qui rallongeait les combats.
- **Solution** : J'ai ajouter des item pour augmenter les degats.

### 2. Gestion du shop et de l’inventaire
- **Problème** : Le menu de la boutique s'ouvrait plusieurs fois ou empêchait le retour au menu principal.
- **Solution** : J'ai modifié la boucle d'achat pour qu'elle se termine correctement après chaque transaction.

### 3. Génération du diagramme UML
- **Problème** : Initialement, seul `Main` apparaissait dans le diagramme.
- **Solution** : J'ai sélectionné manuellement toutes les classes dans IntelliJ IDEA Ultimate pour générer un diagramme complet.

### 4. Problèmes rencontrés avec PlantUML
- **Problème** : Lors de la génération du **diagramme de flux**, j’ai rencontré plusieurs erreurs avec **PlantUML**, notamment :
    - Des erreurs de syntaxe (`Cannot find if`).
    - Des flèches mal reliées aux bonnes étapes.
    - Une difficulté à connecter certaines décisions au bon endroit.
## Bilan des acquis

- Amélioration de la gestion des classes en POO (héritage, polymorphisme, encapsulation).
- Mise en place d'un système de gestion d'inventaire et d'équipement dans un jeu textuel.
- Pratique de la documentation avec JavaDoc.
- Génération et intégration de diagrammes UML et de flux.
- Utilisation de Git/GitHub pour le versioning du projet.

## Remarques complémentaires

- L’implémentation du diagramme de flux est encore en cours et pourrait être améliorée.
- Quelques ajustements peuvent être apportés pour optimiser le système de combat et l'affichage du menu.
- Ajouter des tests unitaires pourrait être une prochaine étape pour assurer la stabilité du projet.

---

**Dépôt GitHub** : [Lien vers ton repo](lien_vers_ton_depot)

Si tu as d'autres éléments à ajouter, tu peux les compléter directement dans ce fichier. 🚀