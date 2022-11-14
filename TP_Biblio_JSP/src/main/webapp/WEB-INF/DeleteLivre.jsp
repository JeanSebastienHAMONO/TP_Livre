<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="fr">

<head>
<c:import url="enteteBootstrap.jsp" charEncoding="UTF-8" />

</head>

<body class="colorBGApplication">

	<c:import url="/WEB-INF/MenuGlobal.jsp" charEncoding="UTF-8" />

	<!-- Corps de la page -->
	<div class="row">
		<div class="col-sm-12 text-center">
			<h2>Suppression d'un livre</h2>
			<hr />
		</div>

		<div class="row">

			<div class="col-sm-3"></div>

			<div class="col-sm-6">

				<c:import url="GestionMessages.jsp" charEncoding="UTF-8" />

				<div class="card alert-warning mb-3 shadow rounded">
					<div class="card-header bg-warning font-weight-bold">
						<i class="fas fa-user"></i> Suppression d'un livre
						<c:if test="${not empty idlivre}"> [id:${idlivre}] </c:if>
					</div>
					<div class="card-body">
						<div class="col-sm-12">
							<form action="<c:url value="/deleteLivre"></c:url>" method="POST">
								<input type="hidden" id="idlivre" name="idlivre"
									value="${livre.id}">
								<div class="form-group col-sm-12">
									<div class="col-sm-4">
										<b>Titre : </b>
									</div>
									<div class="col-sm-8">
										<c:out value="${livre.titre}" />
									</div>
								</div>
								<br />
								<div class="form-group col-sm-12">
									<div class="col-sm-4">
										<b>Nombre de pages : </b>
									</div>
									<div class="col-sm-8">
										<c:out value="${livre.nbPages}" />
									</div>
								</div>
								<br />
								<div class="form-group col-sm-12">
									<div class="col-sm-4">
										<b>Catégorie : </b>
									</div>
									<div class="col-sm-8">
										<c:out value="${livre.categorie}" />
									</div>
								</div>
								<br />
								<div class="form-group col-sm-12">
									<div class="col-sm-4">
										<b>Auteur : </b>
									</div>
									<div class="col-sm-8">
										<c:out value="${livre.auteur.nom} ${livre.auteur.prenom}" />
									</div>
								</div>
								<c:if test="${supprimer == 'N'}">
									<br />
									<hr />
									<div class="form-group col-sm-12 text-center">

										<button type="submit" class="btn btn-primary">Supprimer</button>

									</div>
								</c:if>
							</form>
						</div>
					</div>
				</div>
			</div>

			<div class="col-sm-3"></div>
		</div>
	</div>
</body>

</html>