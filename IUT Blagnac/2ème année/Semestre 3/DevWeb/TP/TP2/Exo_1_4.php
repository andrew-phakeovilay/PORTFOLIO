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
					// Exo_1_4.php
                    $tab1 = [
						"Spider-Man" => ["Jon Watts", "USA", "2002"],
						"Batman" => ["Matt Reeves", "USA", "2022"],
						"Superman" => ["Richard Donner", "USA", "1979"]
						];    
                    echo "<h3>Avant tri...</h3>";
                    var_dump($tab1);
                    echo "<br>";

                    echo "<br><h3>Après tri...</h3>";
                    asort($tab1);
                    var_dump($tab1);

                    echo "<br><br><h3>Tirage au sort du nom de film : ";
                    $tabFilms = [];
                    foreach ($tab1 as $cle => $val) {
						array_push($tabFilms, $cle);
                    }
                    $i = rand(0,2);
                    echo "$tabFilms[$i]</h3><br>";

                    echo "<h3>Avant mélange...</h3>";
                    $tabContinent =  ["Afrique", "Amerique", "Asie", "Europe", "Oceanie"];
                    var_dump($tabContinent);
                    echo "<br>";

                    echo "<h3>Après mélange...</h3>";
                    shuffle($tabContinent);
                    var_dump($tabContinent);
                    echo "<br>";

                    echo "<h3>Après un autre mélange...</h3>";
                    shuffle($tabContinent);
                    var_dump($tabContinent);
				?>
            </div>
        </div>
    </div>

	<?php include("./include/footer.php"); ?>
</body>
</html>

