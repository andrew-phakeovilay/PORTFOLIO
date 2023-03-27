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
					// Exo_1_2.php
					$tab = array(
						"Spider-Man" => array("réalisateur" => "Jon Watts", "pays" => "USA", "année" => "2002"),
						"Batman" => array("réalisateur" => "Matt Reeves", "pays" => "USA", "année" => "2022"),
						"Superman" => array("réalisateur" => "Richard Donner", "pays" => "USA", "année" => "1979")
					);
					echo "<pre>";
					print_r($tab);
					echo "</pre>";
				?>
            </div>
        </div>
    </div>

	<?php include("./include/footer.php"); ?>
</body>
</html>