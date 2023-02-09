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

                <h1 class="fw-semibold text-center">Inscription</h1>
                <p class="text-muted">Vous n'avez pas de compte ? Inscrivez-vous pour accéder à vos commandes, favoris et bien
                    d'autres fonctionnalités. </p>

                <form name="formRegister" action="
                <?php
                // si il y a un panier dans les paramètres d'URL, alors l'utilisateur était dans le processus de commande, on le redirigera dans le panier qu'il veut commander pour la suite
                if (isset($_GET['cartID'])) {
                    echo './traitRegister.php?cartID=' . $_GET['cartID'];
                } else {
                    echo './traitRegister.php';
                }
                ?>" method="POST">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Prénom</label>
                        <input autocomplete="off" type="text" name="prenomUtil" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Nom</label>
                        <input autocomplete="off" type="text" name="nomUtil" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Adresse mail</label>
                        <input autocomplete="off" type="email" name="mailUtil" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label"> Mot de passe</label>
                        <input autocomplete="off" type="password" name="mdpUtil" id="mdpUtil" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Confirmez le mot de passe</label>
                        <input autocomplete="off" type="password" name="mdpUtilAconfirmer" id="mdpUtilAconfirmer" class="form-control" required>
                    </div>

                    <div class="row d-flex justify-content-center">
                        <small class="text-muted mb-2">Tous les champs sont obligatoires</small>
                        <div class="col-12 d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary btn-lg" name="isSubbed" onclick="validateForm()">
                                Inscription
                            </button>
                        </div>
                    </div>

                    <div class="row mt-3">
                        <div class="col-12 text-center">
                            <p>Vous avez déjà un compte ?</p>
                            <a href="./formLogin.php"> Connectez-vous</a>
                        </div>
                    </div>
                </form>

                <div class="row d-flex justify-content-center">
                    <img src="./assets/images/logo.png" alt="" style="width: 50%;">
                </div>

            </div>
        </div>
    </div>

    <script type="text/javascript">
        /*
        dans cette fonction on vérifie que les deux mots de passe
        saisies par l'utilisateur sont les mêmes puis on
        soumet le formulaire.
        */
        function validateForm() {
            let pwd1 = document.querySelector('input[name="mdpUtil"]').value;
            let pwd2 = document.querySelector('input[name="mdpUtilAconfirmer"]').value;
            let formReg = document.forms['formRegister'];

            if (pwd1 == pwd2) {
                formReg.submit();
            } else {
                alert('Les mots de passe ne correspondent pas');
            }
        }
    </script>

    <?php include('includes/footer.php'); ?>
</body>

</html>