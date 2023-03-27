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
					// Exo_1_1.php
					$tab1 = [
						"Spider-Man" => ["Jon Watts", "USA", "2002"],
						"Batman" => ["Matt Reeves", "USA", "2022"],
						"Superman" => ["Richard Donner", "USA", "1979"]
						];


					$tab2 = array(
						"Spider-Man" => array("Jon Watts", "USA", "2002"),
						"Batman" => array("Matt Reeves", "USA", "2022"),
						"Superman" => array("Richard Donner", "USA", "1979")
					);

					var_dump($tab1);
					echo '<br><br>';
					echo "<pre>";
					print_r($tab2);
					echo "</pre>";

				?>
            </div>
        </div>
    </div>

	<?php include("./include/footer.php"); ?>
</body>
</html>