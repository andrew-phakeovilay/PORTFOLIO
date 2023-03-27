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
					// Exo_1_5.php
                    $tab1 = [
						'18/11/2003',
                        '03/09/2000',
                        '05/04/1985'
						];    
                    echo "<h3>Le tableau des dates de naissance :</h3><br>";
                    print_r($tab1);
                    echo "<br><br>";

                    echo "<h3>Le tableau des années de naissance :</h3><br>";
                    $tabAnnee = [];
                    foreach ($tab1 as $cle => $val) {
						array_push($tabAnnee, substr($val, -4)); 
                    }
                    print_r($tabAnnee);
				?>
            </div>
        </div>
    </div>

	<?php include("./include/footer.php"); ?>
</body>
</html>

