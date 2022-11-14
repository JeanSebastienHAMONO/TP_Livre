<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!-- Entete de la page -->
    <div class="row">
        <h1 class="text-center fw-bold">Bibliothèque</h1>
        <hr />
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                    <a class="navbar-brand" href="<c:url value="/index.jsp"></c:url>"><button type="button"
                            class="btn btn-outline-success fw-bold">Home</button></a>
                    <a class="navbar-brand" href="<c:url value="/ListAuteurs"></c:url>"><button type="button"
                            class="btn btn-outline-success fw-bold">Les auteurs</button></a>
                    <a class="navbar-brand" href="<c:url value="/ListLivres"></c:url>"><button type="button" class="btn btn-outline-success fw-bold">Les
                            Livres</button></a>
                </div>
            </div>
        </nav>
    </div>