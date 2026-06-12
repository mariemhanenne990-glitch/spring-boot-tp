<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Article</title>
</head>
<body>
    <a th:href="@{/articles}">← Retour aux articles</a>

    <h1 th:text="${article.title}">Titre</h1>
    <p th:text="${article.content}">Contenu</p>

    <h2>💬 Commentaires (<span th:text="${article.comments.size()}">0</span>)</h2>

    <div th:each="comment : ${article.comments}">
        <b th:text="${comment.author}">Auteur</b>
        <p th:text="${comment.text}">Texte</p>
        <hr/>
    </div>

<h3>Ajouter un commentaire</h3>
    <form th:action="@{/comments/add/{id}(id=${article.id})}"
th:object="${newComment}" method="post">

        <label>Nom :</label><br/>
        <input type="text" th:field="*{author}"/><br/><br/>

        <label>Commentaire :</label><br/>
        <textarea th:field="*{text}" rows="3" cols="40"></textarea><br/><br/>

        <button type="submit">Envoyer</button>
    </form>
</body>
</html>