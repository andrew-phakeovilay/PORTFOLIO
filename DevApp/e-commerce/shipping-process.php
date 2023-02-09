<?php 


// =================================
// FICHIER DE GESTION DE LA COMMANDE
// =================================

// Provenance : fichier order-shipping.php
// Fonctionnement : ce fichier est appelé lors de la soumission du formulaire avec les informations relatives à la livraison
// lorsque ce fichier est appelé, il récupère les valeurs des champs et les passe en paramètre à la
// seconde étape de la commande : les informations de paiement.

// les paramètres de livraison sont donc récupérés directement via l'URL

// Récupération des valeurs des champs
$shippingAdress = $_POST['shippingAdress'];
$shippingPostal = $_POST['shippingPostal'];
$shippingCity = $_POST['shippingCity'];
$shippingType = $_POST['radioShipping'];

// Construction de la chaîne de requête à partir des valeurs des champs
$query = http_build_query(array(
    'shippingAdress' => $shippingAdress,
    'shippingPostal' => $shippingPostal,
    'shippingCity' => $shippingCity,
    'shippingType' => $shippingType
));

// Construction de l'URL cible avec la chaîne de requête
$url = 'payment.php?tab=bill&cartID=' . $_GET['cartID'] . '&' . $query;

// echo 'url complète :' . $url;

// redirection vers l'url
header('location:' . $url);

?>