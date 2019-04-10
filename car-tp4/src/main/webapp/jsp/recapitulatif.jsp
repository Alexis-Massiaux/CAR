<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recapitulaif de l'ajout</title>
</head>
<body>

	Recapitulatif :

	<%
		String titre = (String) request.getAttribute("titre");
		String auteur = (String) request.getAttribute("auteur");
		String date = (String) request.getAttribute("date");
		
		out.println("Titre 	: "+titre);
		out.println("Auteur : "+titre);
		out.println("Date 	: "+titre);
	%>

</body>
</html>