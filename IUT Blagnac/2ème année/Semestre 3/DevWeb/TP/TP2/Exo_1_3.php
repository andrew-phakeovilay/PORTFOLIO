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
					// Exo_1_3.php
					$tab1 = [
						"Spider-Man" => ["Jon Watts", "USA", "2002"],
						"Batman" => ["Matt Reeves", "USA", "2022"],
						"Superman" => ["Richard Donner", "USA", "1979"]
						];
					$tab2 = array(
						"Spider-Man" => array("réalisateur" => "Jon Watts", "pays" => "USA", "année" => "2002"),
						"Batman" => array("réalisateur" => "Matt Reeves", "pays" => "USA", "année" => "2022"),
						"Superman" => array("réalisateur" => "Richard Donner", "pays" => "USA", "année" => "1979")
					);
					foreach ($tab1 as $cle => $val) {
						
						echo "<strong>Elément $cle </strong>";
						echo "<br>";

						foreach($val as $sousCle => $sousVal) {
							echo "élément $sousCle : $sousVal";
							echo "<br>";
						}
					}
					echo "<br>";
					foreach ($tab2 as $cle => $val) {
						
						echo "<strong>Elément $cle </strong>";
						echo "<br>";

						foreach($val as $sousCle => $sousVal) {
							echo "élément $sousCle : $sousVal";
							echo "<br>";
						}
					}
					echo "<br>";

				?>
            </div>
        </div>
    </div>

	<?php include("./include/footer.php"); ?>
</body>
</html>

