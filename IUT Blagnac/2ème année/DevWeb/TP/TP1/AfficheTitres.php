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
					// AfficheTitre.php
					define("NB", 6);
					for($i = 1; $i<=NB; $i++){
						echo "<h$i>$i :Titre de niveau $i</h$i>";
					}
				?>
            </div>
        </div>
    </div>

	<?php include("./include/footer.php"); ?>
</body>
</html>