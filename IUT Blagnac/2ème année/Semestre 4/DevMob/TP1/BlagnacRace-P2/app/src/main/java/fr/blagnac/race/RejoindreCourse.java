package fr.blagnac.race;

import java.io.IOException;
import java.net.MalformedURLException;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.content.Context;
import android.location.Location;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RejoindreCourse extends Dialog{
	
	private final MapsActivity mainActivity;

	public RejoindreCourse(MapsActivity mainAct) {
		super(mainAct);
		this.mainActivity=mainAct;
		setContentView(R.layout.connexion_serveur);
		setTitle("Connexion à un serveur");
		Button ok = (Button) findViewById(R.id.button_ok);
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String adresse_serveur = ((EditText) findViewById(R.id.ed_serveur)).getText().toString();
				String nom = ((EditText) findViewById(R.id.ed_nom)).getText().toString();
				mainAct.setAdresse_serveur(adresse_serveur);
				mainAct.setMonNom(nom);
				mainAct.getMyMarker().setTitle(nom);
				try {
					RequeteHTTP requeteServeur = new RequeteHTTP(adresse_serveur);
					String isEnCours = requeteServeur.doGET("cmd=isRaceOnProgress");
					if ( isEnCours.equals("None") ) {
						mainAct.desabonnementGPS();
						mainAct.showAlert("Problème", "Le serveur n'est pas actif");
						dismiss();
					}
					else if ( isEnCours.equals("False") ) {
						Toast.makeText(mainAct, "Premier participant connecté \n  Initialisation de la course", Toast.LENGTH_LONG).show();
						requeteServeur.doGET("cmd=reinitRace");
					}

						// To Do
					requeteServeur.doGET("cmd=addParticipant&name=" + nom);
					String desc = requeteServeur.doGET("cmd=getGoal&name=" + nom);

					String[] listDesc = desc.split(",");
					LatLng ltlg = new LatLng(Double.parseDouble(listDesc[1]), Double.parseDouble(listDesc[2]));

					mainAct.getCibleTv().setText(listDesc[0]);
					mainAct.getCibleMarker().setTitle(listDesc[0]);
					mainAct.getCibleMarker().setPosition(ltlg);
					mainAct.getCibleLoc().setLatitude(Double.parseDouble(listDesc[1]));
					mainAct.getCibleLoc().setLatitude(Double.parseDouble(listDesc[2]));

					mainAct.abonnementGPS();
					dismiss();
				} catch (MalformedURLException e) {
					mainAct.showAlert("Problème", "URL invalide");
					dismiss();
				} catch (IOException e) {
					mainAct.showAlert("Problème", "Pas d'accès au réseau");
					dismiss();
				}
			}
		});
	}

}
