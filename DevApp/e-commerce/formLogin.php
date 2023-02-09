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

    <div class="container mt-5">
        <div class="row d-flex justify-content-center">
            <div class="col-4 text-left">

                <h1 class="fw-semibold text-center">Connexion</h1>
                <p class="text-muted">Si vous possédez déjà un compte sur notre site vous pouvez vous connecter avec vos identifiants. </p>


                <?php
                // if GET msgErreur is set, display the error message
                if (isset($_GET['msgErreur'])) {
                    if ($_GET['msgErreur'] == "Deconnexion effectuée !") {
                        echo '
                        <div class="alert alert-success" role="alert">
                            ' . $_GET['msgErreur'] . '
                        </div>
                        ';
                    } else {
                        echo '
                        <div class="alert alert-warning" role="alert">
                            ' . $_GET['msgErreur'] . '
                        </div>';
                    }
                }

                ?>


                <form method="POST" action="traitLogin.php">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Adresse mail</label>
                        <input autocomplete="off" type="email" class="form-control" name="mailUtil" placeholder="client4@gmail.com">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label"> Mot de passe</label>
                        <input autocomplete="off" type="password" class="form-control" name="mdpUtil" placeholder="5Qxyh260">
                    </div>

                    <div class="row d-flex justify-content-center">
                        <div class="col-12 d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary btn-lg" name="isSubbed">
                                Connexion
                            </button>
                        </div>
                    </div>

                    <div class="row mt-3">
                        <div class="col-12 text-center">
                            <p>Vous n'avez pas de compte ?</p>

                            <a href="
                            <?php
                            if (isset($_GET['cartID'])) {
                                echo './formRegister.php?cartID=' . $_GET['cartID'];
                            } else {
                                echo './formRegister.php';
                            }
                            ?>
                            "> Inscrivez-vous</a>
                        </div>
                    </div>
                </form>

                <div class="row d-flex justify-content-center">
                    <img src="./assets/images/logo.png" alt="" style="width: 50%;">
                </div>
            </div>
        </div>
    </div>


    <?php include('includes/footer.php'); ?>


</body>

</html>