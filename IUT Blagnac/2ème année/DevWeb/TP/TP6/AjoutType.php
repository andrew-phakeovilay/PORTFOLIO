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
    <?php
        session_start();
        if(!empty($_SESSION['identifie'])){
        }
        else{
            header('location: FormConnexion.php?msgErreur=Tentative d\'acces interdite');
            exit();
        }
    ?>
    <div style="padding-top: 30px" id="main">
        <div style="text-align: center" class="col-md-12">
            <form method="POST" class="form-example">
                <fieldset>
                    <legend>Veuillez entrer les informations du nouveau type de biens :</legend><br>
                    Id Type : <input type="text" name="idTextType" value="FD" required/><BR><BR>
                    Nom du Type : <input type="text" name="nomTextType" value="Duplex" required/><BR><BR>
                <input type="submit" name="valider" value="Valider">
                </fieldset>
            </form>
        </div>
    </div>
    <?php
        if (isset($_POST['valider']) && isset($_POST['idTextType']) && isset($_POST['nomTextType'])) {
            $motifID = '#^F{1}[1-8A-Z]{1}$#';
            $motifNom = '#\w{6,25}#i';
            if (preg_match($motifID, htmlentities($_POST['idTextType'])) && preg_match($motifNom, htmlentities($_POST['nomTextType']))) {
                require_once("include/connect.inc.php");
                $request = "INSERT INTO Types VALUES (:id, :nom)";
                $result = $con->prepare($request);
                $result->execute(['id' => $_POST['idTextType'], 'nom' => $_POST['nomTextType']]);
                $con = null;
                header('location: ConsultType.php');
                die();
            }
        }
    ?>
	<?php include("./include/footer.php"); ?>
</body>
</html>
