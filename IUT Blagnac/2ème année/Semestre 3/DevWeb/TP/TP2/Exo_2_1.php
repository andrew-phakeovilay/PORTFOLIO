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
					// Exo_2_1.php
                    $a=1;
                    $b=0;
                    try {
                        if($b == 0){
                            throw new Exception("Division par 0", 9);}
                        else {
                            echo "Resultat de : a/b = ".$a/$b;
                        }    
                    } catch(Exception $e) {
                        echo "Message d'erreur : ".$e->getMessage()."<hr>";
                        echo "Nom du fichier : ".$e->getFile()."<hr>";
                        echo "Numero de ligne : ".$e->getLine()."<hr>";
                        echo "Code d'erreur : ".$e->getCode()."<hr>";
                        echo "__toString : ".$e->__toString()."<hr>";}
				?>
            </div>
        </div>
    </div>

	<?php include("./include/footer.php"); ?>
</body>
</html>

