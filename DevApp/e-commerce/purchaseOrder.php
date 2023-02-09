<?php

session_start();

require_once("./includes/connect.inc.php");

// =================================================================
// FICHIER QUI PASSE LA COMMANDE, QUI AJOUTE LA COMMANDE DANS LA BDD
// =================================================================

$shippingFees = 0; // frais de livraison

// Récupération des valeurs des champs LIVRAISON
$shippingAdress = $_POST['shippingAdress']; // adresse de livraison
$shippingPostal = $_POST['shippingPostal']; // code postal
$shippingCity = $_POST['shippingCity']; // ville
$shippingType = $_POST['shippingType']; // type de livraison

// Récupération des valeurs des champs MODE DE PAIEMENT
$cardName = $_POST['cardName'];
$cardSurname = $_POST['cardSurname'];
$cardNumber = $_POST['cardNumber'];
$cardCVV = $_POST['cardCVV'];
$cardExpiration = $_POST['cardExpiration'];

// Calcul des frais de livraison
if ($shippingType == "domicile") {
    $shippingFees = 12.99;
} else if ($shippingType == "Relais") {
    $shippingFees = 9.99;
} else {
    $shippingFees = 12.99;
}

// Zone debeuguage
// echo $shippingAdress;
// echo $shippingPostal;
// echo $shippingCity;
// echo $shippingType;

// echo $_SESSION['idClientIdentifie'];

// echo $cardName;
// echo $cardSurname;
// echo $cardNumber;
// echo $cardCVV;
// echo $cardExpiration;

$date = date('Y-m-d');

// =================================
// PASSAGE DE LA COMMANDE VIA LA BDD
// =================================

$createCommand = oci_parse($connect, "INSERT INTO COMMANDE VALUES (seqidCommande.NEXTVAL, :idPanier, :idClient, TO_DATE(:dateC,'YYYY-MM-DD'), :fraisL, :adresseL, :codePostalL, :villeL)");

oci_bind_by_name($createCommand, ':idPanier', $_POST['cartID']);
oci_bind_by_name($createCommand, ':idClient', $_SESSION['idClientIdentifie']);
oci_bind_by_name($createCommand, ':dateC', $date);
oci_bind_by_name($createCommand, ':fraisL', $shippingFees);
oci_bind_by_name($createCommand, ':adresseL', $shippingAdress);
oci_bind_by_name($createCommand, ':codePostalL', $shippingPostal);
oci_bind_by_name($createCommand, ':villeL', $shippingCity);

oci_execute($createCommand);

oci_free_statement($createCommand);

// ======================================
// ENVOI DU NUMERO DE COMMANDE DANS L'URL
// ======================================

$getCommandNumber = oci_parse($connect, "SELECT IDCOMMANDE FROM COMMANDE WHERE IDPANIER = :idPanier");

oci_bind_by_name($getCommandNumber, ':idPanier', $_POST['cartID']);
oci_execute($getCommandNumber);

// Récupération des résultats
$row = oci_fetch_array($getCommandNumber, OCI_ASSOC);
$command_id = $row['IDCOMMANDE'];

oci_free_statement($getCommandNumber);

// ===============================================
// ON MET LE PANIER DE LA COMMANDE COMME 'TERMINE'
// ===============================================

$validationPanier = oci_parse($connect, "UPDATE PANIER SET ENCOURS = 'finie' WHERE IDPANIER = :idPanier");

oci_bind_by_name($validationPanier, ':idPanier', $_POST['cartID']);
oci_execute($validationPanier);

oci_free_statement($validationPanier);



header('location:payment.php?tab=checkout&cartID=12&message=success&commandID='.$command_id);