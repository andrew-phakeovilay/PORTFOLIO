<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
    <link rel="stylesheet" href="include/bootstrap.min.css">
    <link rel="stylesheet" href="include/styles.css">
	<title>Mon site en PHP!</title>
</head>
<body>
	<?php include("./include/header.php"); ?>
    <?php include("./include/menus.php"); ?>
    <?php
        session_start();
        if(!empty($_SESSION['identifie'])){
        }
        else{
            header('location: FormConnexion.php?msgErreur=Tentative d\'acces interdite');
            exit();
        }
    ?>
    <div style="padding-top: 30px" id="main">
        <div style="text-align: center" class="col-md-12">
          <img src="./images/villaAccueil.jpg" 
		     alt="[Image de belle villa]"
		    width="70%"/>
        </div>
    </div>
	<?php include("./include/footer.php"); ?>
</body>
</html>
