package com.example.tp1_devmob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    /**
     * 1. Quels sont les états possibles d’une application android ?
     * Avec la figure du cycle de vie d'une activité android, nous pouvons voir 6 états
     * état crée, état démarré, état reprise, état en pause, état arrêté et état déruit
     *
     * 2. Quelles sont les méthodes principales (appelées callback) d’une activité android ?
     * Les méthodes sont onCreate(), onStart(), onResume(), onPause(), onStop(), onDestroy()
     *
     * 4. Tests :
     *
     *      1. Démarrez votre application. Quelles sont les méthodes successivement appelées ?
     *      Nous voyons dans les logs onCreate(), onStart() et onResume() qui sont appélées
     *
     *      2. Pour cacher votre application, cliquez sur l’icône de l’accueil. Quelles méthodes sont
     *      appelées ?
     *      Nous voyons dans les logs onPause(), onStop() qui sont appélées
     *
     *      3. Est-il possible de détruire manuellement l’application ?
     *      Oui, en allant sur l'historique des applications actives de son téléphones et retirer l'application, alors la méthode onDestroy() est appelée
     *
     *      4. Modifiez un peu votre application, par exemple en changeant les messages loggués des méthodes onXXX().
     *          Que se passe-t-il si on démarre une nouvelle instance de votre application via Android Studio ?
     *      ??? Je ne comprends pas
     *      Le message de logue, change dans les logs, avoir un tag différent change le tag donc le filtre de tag:TAG n'affiche pas tous les message de log.i que nous avons effectués
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("TAG", "Méthode onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG", "Méthode onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG", "Méthode onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TAG", "Méthode onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG", "Méthode onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Restart", "Méthode onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Destroy", "Méthode onDestruite");
    }
}