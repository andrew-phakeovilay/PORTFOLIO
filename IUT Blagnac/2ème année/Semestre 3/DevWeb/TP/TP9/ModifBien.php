<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
    <link rel="stylesheet" href="include/bootstrap.min.css">
    <link rel="stylesheet" href="include/styles.css">
	<title>Mon site en PHP!</title>
</head>
<body>
	<?php
        session_start();
        if(!empty($_SESSION['identifie'])){
        }
        else{
            header('location: FormConnexion.php?msgErreur=Tentative d\'acces interdite');
            exit();
        }
    ?>
	<?php include("./include/header.php"); ?>

    <?php include("./include/menus.php"); ?>
    <div style="padding-top: 30px" id="main">
        <div style="text-align: center" class="col-md-12">
			<p>Veuillez entrer les nouvelles informations du bien</p>
			<br>
			<form method='post' action='ModifBien.php' enctype="multipart/form-data">
				<fieldset>
					<div>
                        <?php
                            require_once("include/connect.inc.php");
                            $request = $con->prepare("SELECT * FROM Biens WHERE idBien ='".$_GET['pIdBien']."'");
                            $request->execute();
                            foreach($request as $column){
                                echo "Id Bien : <input type='text' name='idBien' value='".$column['idBien']."' readonly/><BR><BR>";
                                echo "Titre du Bien : <input type='text' name='titreBien' value='".$column['titreBien']."' required/><BR><BR>";
                                echo "Détail du Bien : <input type='text' name='detailBien' value='".$column['detailBien']."' required/><BR><BR>";
                                echo "Prix du Bien : <input type='text' name='prixBien' value='".$column['prixBien']."' required/><BR><BR>";
                                echo "Type du Bien : <select name='LD_Pièces'>";
                                $requestListe = $con->prepare("SELECT nomType, idType FROM Types");
                                $requestListe->execute();
                                foreach($requestListe as $ligneListe){
    								if ($column['idType'] == $ligneListe['idType']){
                                        echo "<option  value='".$ligneListe['idType']."' selected = 'true'>".$ligneListe['nomType']."</option>";
                                    }
                                    else{
                                        echo "<option  value='".$ligneListe['idType']."'>".$ligneListe['nomType']."</option>";
                                    }
                                }
                                echo "</select>";
                                $requestListe->closeCursor();
                            }
                            $request->closeCursor();
                        ?>
                        <BR><BR>
                        <?php
                            echo '<input type="file" name="monfichier" accept="image/jpeg">';
                        ?>
                        <BR><BR>
					</div>
					<div>
						<input type='submit' name='valider' value='Valider'/><BR><BR>
					</div>
				</fieldset>
			</form><BR><BR>
				<?php
				@$idBien=$_POST['idBien'];
				@$idType=$_POST['idType'];
				@$titreBien=$_POST['titreBien'];
				@$detailBien=$_POST['detailBien'];
				@$prixBien=$_POST['prixBien'];
				@$valider=$_POST["valider"];

				if(isset($valider) && isset($_POST['LD_Pièces'])){
                    require_once('./include/connect.inc.php');
                    $request = 'UPDATE Biens SET idType = :idType, titreBien = :titreBien, detailBien = :detailBien, prixBien = :prixBien WHERE idBien=:idBien';
                    $result = $con->prepare($request);
                    $result->execute(['idBien' => $idBien, 'idType' => $_POST['LD_Pièces'], 'titreBien' => $titreBien, 'detailBien' => $detailBien, 'prixBien' => $prixBien]);
                    if(!empty($_FILES['monfichier']) && $_FILES['monfichier']['error'] == 0){
                        $infosfichier = pathinfo($_FILES['monfichier']['name']);
                        if($infosfichier['extension'] == 'jpg'){
                            $nomImg = htmlentities($_POST['idBien']).".jpg";
                            unlink('images/'.$nomImg);
                            move_uploaded_file($_FILES['monfichier']['tmp_name'],'images/'.$nomImg);
                        }
                    }
                    header("location: ConsultPrix.php");
				}
				?>
            </div>
        </div>
    </div>
	<?php include("./include/footer.php"); ?>
</body>
</html>