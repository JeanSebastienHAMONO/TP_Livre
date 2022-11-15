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
            <h2>${libelleAction} d'un auteur</h2>
            <hr />
        </div>

        <div class="row">

            <div class="col-sm-3">
            </div>

            <div class="col-sm-6">
            
            	<c:import url="GestionMessages.jsp" charEncoding="UTF-8" />
                 
                <div class="card alert-info mb-3 shadow rounded">
                    <div class="card-header bg-info font-weight-bold">
                        <i class="fas fa-user"></i> ${libelleAction} d'un auteur <c:if test="${not empty idauteur}"> [id:${idauteur}] </c:if>
                    </div>
                    <div class="card-body">
                        <div class="col-sm-12">
                            <form action="<c:url value="/manageAuteur"></c:url>" method="POST">
                                <input type="hidden" id="idauteur" name="idauteur" value="${idauteur}">
                                <div class="form-group col-sm-12">
                                    <b>Nom</b>
                                    <input type="text" id="nom" name="nom" class="form-control" placeholder="Nom" value='<c:out value="${nom}" />' maxlength="20" required />
                                 </div>
                                <br />
                                <div class="form-group col-sm-12">
                                    <b>Prénom</b>
                                    <input type="text" id="prenom" name="prenom" class="form-control" placeholder="Prénom"  value='<c:out value="${prenom}" />' maxlength="20" />
                                </div>
                                <br />
                                <div class="form-group col-sm-12">
                                    <b>Téléphone</b>
                                    <input type="text" id="telephone" name="telephone" class="form-control" placeholder="Téléphone"  value='<c:out value="${telephone}" />' maxlength="10"  pattern="[0-9]{10}" title="10 chiffres attendus" required />
                                </div>
                                <br />
                                <div class="form-group col-sm-12">
                                    <b>Email</b>
                                    <input type="text" id="email" name="email" class="form-control" placeholder="Email" value='<c:out value="${email}" />' maxlength="60" />
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