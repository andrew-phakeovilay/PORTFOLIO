<!DOCTYPE html>
<html lang="en">

<?php
    session_start();

    require_once("../includes/connect.inc.php");
    error_reporting(0);
    
    if (isset($_SESSION['idClientIdentifie'])) {
        $reqAdm = "SELECT * FROM CLIENT WHERE IDCLIENT= :pIdClie";
        $prepAdm = oci_parse($connect, $reqAdm);
        oci_bind_by_name($prepAdm, ":pIdClie", $_SESSION['idClientIdentifie']);
        $gotAdmin = oci_execute($prepAdm);

        while (($getInfos = oci_fetch_assoc($prepAdm)) != false) {
            $adm=$getInfos["ADMIN"];
        }

        if($adm == null){
            header('location:../index.php');
        }
    } 
    else {
        header('location:../index.php');
    }
    
?>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BlueGym - dev</title>

    <link rel="stylesheet" href="../includes/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/main.css">

    <link rel="shortcut icon" href="../assets/images/bluegym.ico" type="image/x-icon">

</head>

<body>

    <div class="header bg-dark text-white">
        </br>
        </br>
        </br>
        <div style="text-align:center">
            <h1> Menu Administrateur</h1>
        </div>
        </br>
        </br>
        </br>
    </div>

    </br>
    </br>
    </br>
    <p style="text-align:center; font-size: 20px;">
        <a href="../index.php">Retour à l'accueil</a></br></br>
        <a href="./formAjoutProduit.php">Ajouter un produit</a></br></br>
        <a href="./formModifProduit.php">Modifier un produit</a></br></br>
        <a href="./formRetireProduit.php">Supprimer un produit</a></br></br>
		<a href="./formGestionStockProduit.php">Gestion des stocks des produits</a></br></br>
        <a href="./formAjoutAdmin.php">Ajouter un administrateur</a></br></br>
    </p>
    </br></br>

</body>
</html>