package fr.blagnac.race;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequeteHTTP {
    private String adresse_serveur;

    public RequeteHTTP(String adresse_serveur) {
        this.adresse_serveur = adresse_serveur;
    }

    public String doGET(String parametres) throws MalformedURLException, IOException {
        // Création de l'objet URL pour la requête
        URL url = new URL("http://" + adresse_serveur + "/?" + parametres);

        // Ouverture de la connection et paramétrage
        HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
        httpUrlConnection.setReadTimeout(10000 /* milliseconds */);
        httpUrlConnection.setConnectTimeout(15000 /* milliseconds */);
        httpUrlConnection.setRequestMethod("GET");
        httpUrlConnection.setDoInput(true);

        // Envoi de la requête au serveur
        httpUrlConnection.connect();

        // Si réponse est ok (pas erreur genre 404, 500, ...)
        if (httpUrlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            
            String reponse = "None";

            // Récupération de la réponse

            // La réponse http est un flux d'octets qu'on va lire.
            // Cela pourrait être une page web, une image, ...
            // Ici c'est un flux qui contient une chaine de caractères (unique) de réponse :
            // "None" ou une autre chaine ("OK", une liste de noms, ...)
            // La lecture ressemble à la lecture d'un fichier, le flux est récupéré sur la connexion.
            // une seule chaine de caractères est "lue" sur le flux.
            InputStream is;
            is = httpUrlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            reponse = reader.readLine();
            reader.close();

            // Déconnexion du serveur
            httpUrlConnection.disconnect();

            return reponse;
        } else {
            httpUrlConnection.disconnect();
            throw new MalformedURLException();
        }
    }

}

