# PluginCore

> PluginCore pour l'authentification des joueurs via un compte créé avec l'API EnderPortal.  
Construit avec **Java 8**, **Spigot 1.8.8**, et la librairie **JAPI**.
---

## 📦 Installation

1. Télécharger le projet ou le plugin compilé.
2. Placer le `.jar` dans le dossier `plugins` de votre serveur Spigot 1.8.8.
3. Démarrer le serveur.
4. Editer `env.yml` dans /plugins/EnderPortal :

```
env:
  API_KEY: "MY_API_KEY"
```
---

## 🔑 Commandes

### 1️⃣ `/login <password>`

Permet à un joueur de s'authentifier avec son compte API.

**Exemple :**
/login monmotdepasse


**Notes :**

- Vérifie que le joueur a saisi un mot de passe.
- Envoie la requête à l'API de manière asynchrone pour ne pas bloquer le serveur.
- Retourne un message au joueur selon le succès ou l'échec de la connexion.

**Réponse possible :**

- `&aAuthentification réussie !`
- `&cEchec de connexion à l'API`
- `&cEchec de connexion : <message d'erreur>`

---

## 💻 Architecture - Authentificaiton

### AuthServiceImpl

Classe responsable des appels HTTP vers l'API pour l'authentification :

- Utilise **Gson** pour la sérialisation/désérialisation JSON.
- Les requêtes sont exécutées dans un `CompletableFuture` pour ne pas bloquer le thread principal.
- Retourne un objet `AuthResponse` contenant le **token JWT**.

### DTOs

- `LoginRequest` : contiendra `username` et `password` pour la requête API.
- `AuthResponse` : contiendra le `token` et un `message` de l'API.

### Commandes

- `LoginCommand` : gère la commande `/login`, vérifie les arguments et appelle `AuthServiceImpl.login()` de façon asynchrone.

---

## 🔒 Sécurité

- Les mots de passe ne sont jamais stockés côté serveur Minecraft.
- L'authentification est gérée via **API externe** qui renvoie un **JWT**.
- Le token est ensuite utilisé pour identifier le joueur sur d'autres fonctionnalités protégées du plugin.

---

## 💾 Dépendances

- **Spigot 1.8.8**
- **Java 8**
- **JAPI** (librairie utilitaire pour les commandes et utils Minecraft)
