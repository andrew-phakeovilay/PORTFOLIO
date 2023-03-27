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
    <div style="padding-top: 30px" id="main">
        <div style="text-align: center" class="col-md-12">
				<?php
					// Exo_2_2.php
                    //error_reporting(0); // bloque tous les affichages d'erreurs et de warning;
                    //$a=1;
                    //$b=0;
                    //echo $a/$b; // division par 0
                    //fopen("inconnu.txt","r"); // ouverture fichier inconnu 

                    error_reporting(E_ALL); // affiche toutes les erreurs, warning...;
                    $a=1;
                    $b=0;
                    echo $a/$b; // division par 0
                    fopen("inconnu.txt","r"); // ouverture fichier inconnu
				?>
            </div>
        </div>
    </div>

	<?php include("./include/footer.php"); ?>
</body>
</html>

