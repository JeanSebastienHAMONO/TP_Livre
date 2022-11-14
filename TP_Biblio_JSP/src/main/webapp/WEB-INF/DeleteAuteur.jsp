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
			<h2>Suppression d'un auteur</h2>
			<hr />
		</div>

		<div class="row">

			<div class="col-sm-3"></div>

			<div class="col-sm-6">

				<c:import url="GestionMessages.jsp" charEncoding="UTF-8" />

				<div class="card alert-warning mb-3 shadow rounded">
					<div class="card-header bg-warning font-weight-bold">
						<i class="fas fa-user"></i> Suppression d'un auteur
						<c:if test="${not empty idauteur}"> [id:${idauteur}] </c:if>
					</div>
					<div class="card-body">
						<div class="col-sm-12">
							<form action="<c:url value="/deleteAuteur"></c:url>"
								method="POST">
								<input type="hidden" id="idauteur" name="idauteur"
									value="${auteur.id}">
								<div class="form-group col-sm-12">
									<div class="col-sm-4">
										<b>Nom : </b>
									</div>
									<div class="col-sm-8">
										<c:out value="${auteur.nom}" />
									</div>
								</div>
								<br />
								<div class="form-group col-sm-12">
									<div class="col-sm-4">
										<b>Prénom : </b>
									</div>
									<div class="col-sm-8">
										<c:out value="${auteur.prenom}" />
									</div>
								</div>
								<br />
								<div class="form-group col-sm-12">
									<div class="col-sm-4">
										<b>Téléphone : </b>
									</div>
									<div class="col-sm-8">
										<c:out value="${auteur.telephone}" />
									</div>
								</div>
								<br />
								<div class="form-group col-sm-12">
									<div class="col-sm-4">
										<b>Email : </b>
									</div>
									<div class="col-sm-8">
										<c:out value="${auteur.email}" />
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