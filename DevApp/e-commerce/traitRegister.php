<?php
if(!isset($_POST['isSubbed'])){
    header('location:formRegister.php');
}
else {
    require_once("includes/connect.inc.php");
    error_reporting(0);

    //on hash le mot de passe avant de le stocker
    $mdp_client_hashed = password_hash($_POST['mdpUtil'], PASSWORD_DEFAULT);
    $nomClient = $_POST['nomUtil'];
    $prenomClient = $_POST['prenomUtil'];
    $emailClient = $_POST['mailUtil'];
    $idC = 0;

    $reqInsert = "INSERT INTO Client(idClient, nomC, prenomC, mailC, mdpClient) VALUES(seqIdClient.nextval, :pNom, :pPrenom, :pEmail, :pPassword) RETURNING idClient INTO :idC";

    $insertClient = oci_parse($connect, $reqInsert);

    oci_bind_by_name($insertClient, ":pNom", $nomClient);
    oci_bind_by_name($insertClient, ":pPrenom", $prenomClient);
    oci_bind_by_name($insertClient, ":pEmail", $emailClient);
    oci_bind_by_name($insertClient, ":pPassword", $mdp_client_hashed);
    oci_bind_by_name($insertClient, ":idC", $idC, 999);

    $result = oci_execute($insertClient);

    if (!$result) {
        $e = oci_error($insertClient);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
        print htmlentities($e['message'].' pour cette requete : '.$e['sqltext']);       
    }

    oci_commit($connect);

    oci_free_statement($insertClient);

    session_start();

    //s'il avait un panier
    if(isset($_COOKIE['tempPanier'])){
        session_start();
        $_SESSION['idClientTransiting'] = $idC;
        // s'il était en train de faire sa commande, donc qu'il y avait un cart dans le paramètre de l'url, alors on le redirigera vers le panier
        if (isset($_GET['cartID'])) {
            header("location:cookieCart_to_BDCart.php?cartID=".$_GET['cartId']);
        } else{
            header('location:cookieCart_to_BDCart.php');
        }
    }
    else{
        session_start(); // démarre la session
        $_SESSION['idClientIdentifie'] = $idC; // on stocke l'id du client dans la session
        header('location:index.php'); // on redirige vers la page d'accueil
    }
}

?>