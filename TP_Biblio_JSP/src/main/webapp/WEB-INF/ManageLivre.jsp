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
            <h2>${libelleAction} d'un livre</h2>
            <hr />
        </div>

        <div class="row">

            <div class="col-sm-3">
            </div>

            <div class="col-sm-6">
            
                <c:import url="GestionMessages.jsp" charEncoding="UTF-8" />
                 
                <div class="card alert-info mb-3 shadow rounded">
                    <div class="card-header bg-info font-weight-bold">
                        <i class="fas fa-user"></i> ${libelleAction} d'un livre <c:if test="${not empty idlivre}"> [id : <c:out value="${idlivre}" />] </c:if>
                    </div>
                    <div class="card-body">
                        <div class="col-sm-12">
                            <form action="<c:url value="/manageLivre"></c:url>" method="POST">
                                <input type="hidden" id="idlivre" name="idlivre" value='<c:out value="${idlivre}" />' />
                                <div class="form-group col-sm-12">
                                    <b>Titre</b>
                                    <input type="text" id="titre" name="titre" class="form-control" placeholder="Titre" value='<c:out value="${livre.titre}" />' maxlength="50" required />
                                </div>
                                <br />
                                <div class="form-group col-sm-12">
                                    <b>Nombre de pages</b>
                                    <input type="text" pattern='[0-9]+' id="nbpages" name="nbpages" class="form-control" placeholder="Nombre de pages"  value='<c:out value="${livre.nbPages}" />' oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" maxlength="10" required >
                                </div>
                                <br />
                                <div class="form-group col-sm-12">
                                    <b>Catégorie</b>
                                    <input type="text" id="categorie" name="categorie" class="form-control" placeholder="Catégorie"  value='<c:out value="${livre.categorie}" />' maxlength="20" />
                                </div>
                                <br />
                                <div class="form-group col-sm-12">
                                    <b>Auteur</b>
                                    <select id="idauteur" name="idauteur" class="form-control form-select">
                                    <c:forEach items="${listAuteur}" var="auteur" varStatus="infoBoucle">
         								<option value='<c:out value="${auteur.id}" />'  <c:out default="" value="${livre.auteur.id == auteur.id ? 'selected' : ''}"/> >
	                                        <c:out value="${auteur.nom}" /> <c:out value="${auteur.prenom}" />
                                        </option>
                                    </c:forEach>
                                    </select>
                                </div>
                                <br />
                                <br />
                                <hr />
                                <div class="form-group col-sm-12 text-center">
                                    <button type="submit" class="btn btn-primary">Valider</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-3">
            </div>
        </div>
    </div>
</body>

</html>