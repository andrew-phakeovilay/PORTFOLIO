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
					// Test_multiplie.php
					require_once("Fonction.php");
					multiplie();
					echo "<br/><br/>";
					multiplie(12);
				?>
            </div>
        </div>
    </div>

	<?php include("./include/footer.php"); ?>
</body>
</html>

