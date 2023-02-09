<?php
session_start();

if ($_SESSION['isLogged'] != true) {
    header('location:formLogin.php');
}

?>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BlueGym - dev</title>
    <link rel="stylesheet" href="includes/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/main.css">

</head>

<body>
    <?php include('includes/header.php'); ?>



    <?php include('includes/footer.php'); ?>
</body>

</html>