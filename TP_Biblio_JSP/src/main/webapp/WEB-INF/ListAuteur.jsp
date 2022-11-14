<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!doctype html>
<html lang="fr">

<head>

    <c:import url="/WEB-INF/enteteBootstrap.jsp" charEncoding="UTF-8" />

    <c:import url="/WEB-INF/enteteTable.jsp" charEncoding="UTF-8"><c:param name="nomTable" value="tabAuteurs" /></c:import>

</head>

<body class="colorBGApplication">

    <c:import url="/WEB-INF/MenuGlobal.jsp" charEncoding="UTF-8" />
    
    <!-- Corps de la page -->
    <div class="row">
        <div class="col-sm-12 text-center">
            <h2>Gestion des auteurs</h2>
            <hr />
        </div>

        <div class="col-sm-12 text-center">

            <div class="col-sm-12 text-center">
                <a class="navbar-brand" href="<c:url value="/manageAuteur"></c:url>"><button type="button" class="btn btn-outline-success fw-bold">Ajouter Auteur</button></a>
                <br/>
            </div>

            <div class="col-sm-12 text-center table-responsive-sm">
                <table id="tabAuteurs" class="table table-hover table-bordered table-sm" style="width:100%">
                    <thead>
                        <tr class="table-primary">
                            <th data-sortable="true">Nom</th>
                            <th data-sortable="true">Prénom</th>
                            <th class="cacheInfo" data-sortable="true">Téléphone</th>
                            <th class="cacheInfo" data-sortable="true">Email</th>
                            <th data-sortable="true">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    
						<c:forEach var="auteur"  items="${requestScope['listAuteur']}" >
   	                        <tr>
    	                        <td><c:out value='${auteur.nom}'/></td>
        	                    <td><c:out value='${auteur.prenom}'/></td>
            	                <td class="cacheInfo"><c:out value='${auteur.telephone}'/></td>
                	            <td class="cacheInfo"><c:out value='${auteur.email}'/></td>
                        	    <td>
                        	        <a href="<c:url value="/manageAuteur"><c:param name="idauteur" value="${auteur.id}" /></c:url>"><button class="btn btn-info btn-sm"><i class="fas fa-edit"></i></button></a>
                        	        <a href="<c:url value="/deleteAuteur"><c:param name="idauteur" value="${auteur.id}" /></c:url>"><button class="btn btn-warning btn-sm"><i class="fas fa-trash"></i></button></a>
                        	        <a href="<c:url value="/deleteAuteurV2"><c:param name="idauteur" value="${auteur.id}" /></c:url>"><button class="btn btn-info btn-sm"><i class="fas fa-trash"></i></button></a>
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