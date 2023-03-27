<!DOCTYPE html>
<html>
    <head>
	    <meta charset="utf-8">
    </head>
    <body>
        <h2>Tentative d'accès interdite</h2>
        <br>
        <form action="TraitConnexion.php" method="POST" class="form-example">
            <fieldset>
                <legend>Identification</legend><br>
                Login : <input type="login" name="login"  required <?php 
            if(isset($_COOKIE['cookIdent'])) {
                echo "value='".$_COOKIE['cookIdent']."' ";
            }
            ?>/><BR><BR>
                Mot de passe : <input type="password" name="password" required/><BR><BR>
                Se souvenir de moi : <input type="checkbox" name="souvenir"/></BR><BR>
            <input type="submit" name="valider" value="Valider">
            </fieldset>
        </form>
    </body>
</html>
