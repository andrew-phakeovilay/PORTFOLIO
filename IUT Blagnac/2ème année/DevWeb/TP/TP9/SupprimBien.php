<?php
    session_start();
    if(!empty($_SESSION['identifie'])){
    }
    else{
        header('location: FormConnexion.php?msgErreur=Tentative d\'acces interdite');
        exit();
    }
?>
<?php
    require_once('./include/connect.inc.php');
    $nomImg = htmlentities($_GET['pIdBien']).".jpg";
    unlink('./images/'.$nomImg);
    $result = $con->prepare('DELETE FROM Biens WHERE idBien = :idBien');
    $result->execute(['idBien' => $_GET['pIdBien']]);
    echo '<script language="JavaScript" type="text/javascript"> alert("Suppression effectuée !"); window.location="ConsultPrix.php"; </script>';
?>