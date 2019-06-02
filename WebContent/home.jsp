<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LIBRARY</title>
		<link rel="stylesheet" type="text/css" href="webRes/style.css" />
		<script src="webRes/jquery.js"></script>
		<script src="webRes/fetch.js"></script>
		<script src="webRes/model.js"></script>
	</head>
	<body>
		<!-- WELCOME -->
		<div class="centered"><div id="welcome" class="inline" onClick="refreshPage()">
    	     &nbsp;__&nbsp;&nbsp;&nbsp;&nbsp;_&nbsp;_&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />                          
			 &nbsp;| |&nbsp;&nbsp;(_) |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />                       
			 &nbsp;| |&nbsp;&nbsp;&nbsp;_| |__&nbsp;&nbsp;_ __ __ _ _ __ _&nbsp;&nbsp;&nbsp;_&nbsp;<br /> 
			 &nbsp;| |&nbsp;&nbsp;| | '_ \| '__/ _` | '__| | | |<br /> 
			 &nbsp;| |__| | |_) | | | (_| | |&nbsp;&nbsp;| |_| |<br /> 
			 &nbsp;|____|_|_.__/|_|&nbsp;&nbsp;\__,_|_|&nbsp;&nbsp;&nbsp;\__, |<br /> 
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;__/ |<br /> 
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|___/ <br /><br /> 
    	</div></div>
    	
    	<!-- ADD BOOK -->
    	<form class="centered" accept-charset="UTF-8" method="POST">
    		<input type="hidden" name="action" value="newBook" />
    		<input type="button" value="Pubblica!" class="submitButton" />
    		<input name="title" type="text" placeholder="Titolo..." />
    		<input name="author" type="text" placeholder="Autore..." />
    		<select name="genre" class="genresSelect"></select>
    		<input name="path" type="text" placeholder="Codename..." />
    	</form>
    	
    	<!-- FORM GENRES -->
    	<div class="formWrapper">
    		<form class="inline" accept-charset="UTF-8" method="POST">
    			<input type="hidden" name="action" value="newGenre" />
    			<input type="button" value="Aggiungi" class="submitButton" />
    			<input type="text" name="val" placeholder="nuovo genere..." />
    		</form>
    		<form class="inline shiftRight" accept-charset="UTF-8" method="POST">
    			<input type="hidden" name="action" value="delGenre" />
    			<input type="button" value="Rimuovi" class="submitButton" />
    			<select name="val" class="genresSelect"></select>
    		</form>
    	</div>
    	<form class="centered" accept-charset="UTF-8" method="POST">
    		<input type="hidden" name="action" value="setGenre" />
    		<input type="button" value="Modifica" class="submitButton" />
    		<select name="tit" class="titlesSelect"></select>
    		<select name="gen" class="genresSelect"></select>
    	</form>
    	
	    <!-- SEARCH & ORDER -->
		<div class="formWrapper" id="search">
			<div class="inline">
				<input type="text" id="filtro" placeholder="Cerca..." />
			</div>
			<form class="inline shiftRight white" id="orderBy">
				<input type="radio" id="radtitle" name="radGroup" value="title" checked> Titolo
				<input type="radio" id="radauthor" name="radGroup" value="author"> Autore
				<input type="radio" id="radgenre" name="radGroup" value="genre"> Genere
			</form>
		</div>
	
		<!-- TABLE -->
		<div>
			<table id="bookTable">
				<tr class="heads white">
					<th scope='col'>TITOLO</th>
					<th scope='col'>AUTORE</th>
					<th scope='col'>GENERE</th>
					<th scope='col'>PATH</th>
				</tr>
			</table>
		</div>
		<script src="webRes/controller.js"></script>
	</body>
</html>
