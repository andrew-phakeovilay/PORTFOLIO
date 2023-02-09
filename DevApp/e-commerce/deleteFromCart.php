<?php
session_start();
require_once('./includes/connect.inc.php');
error_reporting(0);
// Supprimer 1 quantité produit
if (isset($_POST['del'])) {
    if (isset($_SESSION['idClientIdentifie'])) {
        $requeteGetNum = "SELECT NBPRODUIT FROM QUANTITEPANIER WHERE idProduit = :pIdProduit AND idPanier = :pIdPanier";
        $prepGetNum = oci_parse($connect, $requeteGetNum);
        oci_bind_by_name($prepGetNum, ':pIdProduit', $_POST['idProduit']);
        oci_bind_by_name($prepGetNum, ':pIdPanier', $_POST['idPanier']);
        $resultExists = oci_execute($prepGetNum);

        while (($getNb = oci_fetch_assoc($prepGetNum)) != false) {
            $nb=$getNb["NBPRODUIT"];
        }
        echo $nb;
        if ($nb>1) {
            echo "test0";
            $requeteRemoveOne = 'UPDATE QUANTITEPANIER SET NBPRODUIT = NBPRODUIT - 1 WHERE idProduit = :pIdProduit AND idPanier = :pIdPanier';
            $removeOneFromCart = oci_parse($connect, $requeteRemoveOne);
            oci_bind_by_name($removeOneFromCart, ':pIdProduit', $_POST['idProduit']);
            oci_bind_by_name($removeOneFromCart, ':pIdPanier', $_POST['idPanier']);
            $resultRemoveOne = oci_execute($removeOneFromCart);
            
            oci_commit($connect);
            oci_free_statement($removeOneFromCart);
        }
        else {
            echo "test1";
            $requeteRemoveAll = 'DELETE FROM QUANTITEPANIER WHERE IDPRODUIT = :pIdProduit AND IDPANIER = :pIdPanier';
            $removeAllFromCart = oci_parse($connect, $requeteRemoveAll);
            oci_bind_by_name($removeAllFromCart, ':pIdProduit', $_POST['idProduit']);
            oci_bind_by_name($removeAllFromCart, ':pIdPanier', $_POST['idPanier']);
            $resultRemoveAll = oci_execute($removeAllFromCart);

            oci_commit($connect);
            oci_free_statement($removeAllFromCart);
        }
        oci_free_statement($prepGetNum);
    } else {
        $result = array();
        foreach (json_decode($_COOKIE['tempPanier'], true) as $articleCookie) {
            if (json_decode($_COOKIE['tempPanier'], true)[$_POST['index']] == $articleCookie) {
                $articleCookie['qtePrixProduit'] = $articleCookie['qtePrixProduit'] - $articleCookie['qtePrixProduit'] / $articleCookie['qteProduit'];
                $articleCookie['qteProduit'] = $articleCookie['qteProduit'] - 1;
            }
            if ($articleCookie['qteProduit'] > 0) {
                array_push($result, $articleCookie);
            }
        }
        if ($result == array()) {
            $newCookieCart = "";
        } else {
            $newCookieCart = json_encode($result);
        }
        setcookie("tempPanier", $newCookieCart, time() + 7200);
    }
    header("location:cart.php");
}
// Supprimer tous du produit
else if (isset($_POST['delAll'])) {
    if (isset($_SESSION['idClientIdentifie'])) {
        $requeteRemoveAll = 'DELETE FROM QUANTITEPANIER WHERE IDPRODUIT = :pIdProduit AND IDPANIER = :pIdPanier';
        $removeAllFromCart = oci_parse($connect, $requeteRemoveAll);
        oci_bind_by_name($removeAllFromCart, ':pIdProduit', $_POST['idProduit']);
        oci_bind_by_name($removeAllFromCart, ':pIdPanier', $_POST['idPanier']);
        $resultRemoveAll = oci_execute($removeAllFromCart);

        oci_commit($connect);
        oci_free_statement($removeAllFromCart);
    } else {
        $result = array();
        foreach (json_decode($_COOKIE['tempPanier'], true) as $articleCookie) {
            if (json_decode($_COOKIE['tempPanier'], true)[$_POST['index']] != $articleCookie) {
                array_push($result, $articleCookie);
            }
        }
        if ($result == array()) {
            $newCookieCart = "";
        } else {
            $newCookieCart = json_encode($result);
        }
        setcookie("tempPanier", $newCookieCart, time() + 7200);
    }
    header("location:cart.php");
} else {
    header('location:index.php');
}
