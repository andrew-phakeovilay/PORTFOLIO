<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BlueGym - dev</title>

    <link rel="stylesheet" href="includes/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/main.css">

    <link rel="shortcut icon" href="./assets/images/bluegym.ico" type="image/x-icon">

</head>

<body>

    <!-- event banner -->
    <div class="container-fluid bg-secondary">
        <div class="row">
            <div class="col-12 text-dark fw-bold text-center">
                <small>⏳ Grosse promotion de noel ! ⏳</small>
            </div>
        </div>
    </div>

    <?php include('includes/header.php'); ?>

    <!-- Bannière principale -->
    <div class="main-wrap">
        <img class="main-bg" src="https://contents.mediadecathlon.com/s862295/k$7f1de21aeee82c19ac7c164ad2894872/dbi_b91bb9b8+c037+4c16+a722+b9789d13adb9.jpg" alt="">
        <div class="main-content">
            <div class="container h-100">
                <div class="row h-100 d-flex align-content-center">
                    <div class="col-7">
                        <h1 class="text-white fw-bold main-title">Atteignez vos objectifs avec Bluegym </h1>
                        <h3 class="text-info">Découvrez nos nouveaux équipements de sport à prix réduit pour les fêtes. </h3>
                        <a href="" class="btn btn-white">Découvrir</a>
                    </div>
                    <div class="col-5">
                        <!-- Section vierge -->
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Product card displayer section -->
    <div class="card-displayer">
    <?php
        require_once("./includes/connect.inc.php");
        $reqCards = 'SELECT * FROM (SELECT * FROM PRODUIT ORDER BY dbms_random.value) WHERE rownum <= 4';
        $cardsProduct = oci_parse($connect, $reqCards);
        $result = oci_execute($cardsProduct);

        while(($cardProduct = oci_fetch_assoc($cardsProduct)) != false){
            echo'
        <div class="product-card"><a href="product.php?idProduit='.$cardProduct["IDPRODUIT"].'" style="color: inherit; text-decoration:none">
            <div class="product-card-img">
                <img src="./assets/images/product-img/'.$cardProduct["IDPRODUIT"].'.png" alt="'.$cardProduct["NOMP"].'">
            </div>
            <div class="product-card-content">
                <h5 class="title">'.$cardProduct["NOMP"].'</h5>
                <h5 class="price">'.$cardProduct['PRIXPRODUIT'].' €</h5>
            </div></a>
        </div>
            ';
        }
        oci_free_statement($cardsProduct);
    ?>
    </div>

    <?php include('includes/footer.php'); ?>

</body>

</html>