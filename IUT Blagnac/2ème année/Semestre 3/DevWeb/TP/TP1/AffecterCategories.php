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
					// AffecterCategories.php
					$sexe = "H";
					if($sexe == "F"){
						$genre = "Féminin";
					}
					else{
						$genre = "Masculin";
					}

					$age = 15;
					$typeAge = "";
					$poids = 88.5;
					$categPoids = "";

					$typeAge = ($age < 21) ? "junior" : "senior";

					switch(true){
						case $poids < 73:
							$categPoids = "légers";
							break;
						case $poids < 90:
							$categPoids = '"super" moyens';
							break;
						case $poids >= 90:
							$categPoids = "lourds";
							break;
						default:
							$categPoids = "Erreur";
					}

					echo "L'athlete est de genre $genre, de type age $typeAge et de catégories de poids $categPoids"
				?>
            </div>
        </div>
    </div>

	<?php include("./include/footer.php"); ?>
</body>
</html>