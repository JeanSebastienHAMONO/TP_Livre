<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="fr">

<head>
    <c:import url="/WEB-INF/enteteBootstrap.jsp" charEncoding="UTF-8" />

    <c:import url="/WEB-INF/enteteTable.jsp" charEncoding="UTF-8"><c:param name="nomTable" value="tabLivres" /></c:import> 

</head>

<body class="colorBGApplication">

    <c:import url="/WEB-INF/MenuGlobal.jsp" charEncoding="UTF-8" />

	<!-- Corps de la page -->
	<div class="row">
		<div class="col-sm-12 text-center">
			<h2>Gestion des livres</h2>
			<hr />
		</div>

		<div class="col-sm-12 text-center">
			<div class="col-sm-12 text-center">
				<a class="navbar-brand" href="<c:url value="./manageLivre"></c:url>">
					<button	type="button" class="btn btn-outline-success fw-bold">Ajouter Livre</button>
				</a>
				<br/>
			</div>
			<div class="col-sm-12 text-center table-responsive-sm">
				<table id="tabLivres"
					class="table table-hover table-bordered table-sm"
					style="width: 100%">
					<thead>
						<tr class="table-primary">
							<th data-sortable="true">Titre</th>
							<th class="cacheInfo" data-sortable="true">Nb Pages</th>
							<th class="cacheInfo" data-sortable="true">Catégorie</th>
							<th class="cacheInfo" data-sortable="true">Nom Auteur</th>
							<th class="cacheInfo" data-sortable="true">Prénom Auteur</th>
							<th data-sortable="true">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="livre" items="${requestScope['listLivre']}">
							<tr>
								<td><c:out value='${livre.titre}' /></td>
								<td class="cacheInfo"><c:out value='${livre.nbPages}' /></td>
								<td class="cacheInfo"><c:out value='${livre.categorie}' /></td>
								<td class="cacheInfo"><c:out value='${livre.auteur.nom}' /></td>
								<td class="cacheInfo"><c:out value='${livre.auteur.prenom}' /></td>
								<td>
								    <a href="<c:url value="/manageLivre"><c:param name="idlivre" value="${livre.id}" /></c:url>"><button class="btn btn-info btn-sm"><i class="fas fa-edit"></i></button></a>
								    <a href="<c:url value="/deleteLivre"><c:param name="idlivre" value="${livre.id}" /></c:url>"><button class="btn btn-warning btn-sm"><i class="fas fa-trash"></i></button></a>
								    <a href="<c:url value="/deleteLivreV2"><c:param name="idlivre" value="${livre.id}" /></c:url>"><button class="btn btn-info btn-sm"><i class="fas fa-trash"></i></button></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

</html>