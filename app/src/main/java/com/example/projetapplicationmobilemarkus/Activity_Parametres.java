package com.example.projetapplicationmobilemarkus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;

import java.lang.reflect.Method;


public class Activity_Parametres extends AppCompatActivity {

    //DÉCLARATIONS
    SurfaceView SVScanner;
    ImageButton imgBtnScanner, imgBtnInspiration, imgBtnExit, imgBtnDarkMode;
    RadioButton btnRadioLight, btnRadioDark;
    RadioGroup groupRadioTheme;

    private static final String NIGHT_MODE = "night_mode";

    int theme;


    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        // Trouvez nos champs dans le XML
        SVScanner = findViewById(R.id.SVCameraLecteur);

        //IMAGEBUTTON
        imgBtnDarkMode = findViewById(R.id.imgBtnDarkMode);
        imgBtnExit = findViewById(R.id.imgBtnExit);
        imgBtnInspiration = findViewById(R.id.imgBtnIdeeURL);
        imgBtnScanner = findViewById(R.id.imgBtnScanner);

//        //Set les boutons radio à nul par précaution
//        btnRadioDark.setChecked(false);
//        btnRadioLight.setChecked(true);
//
//        theme = 0;

        //BOUTONSCANNER
        imgBtnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Appel de la fonction pour démarrer le scanner
                startQRScanner();
            }
        });

        //BOUTONEXIT
        imgBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finir l'activité
                finish();
            }
        });


        //BOUTONDARKMODE
        imgBtnDarkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Parametres.this);
                builder.setCancelable(false);

                view = View.inflate(Activity_Parametres.this, R.layout.menu_dialog_darkmode, null);
                builder.setView(view);

                AlertDialog alertDialog1 = builder.create();

                //Attributs selon le contexte -
                ImageButton btClose= view.findViewById(R.id.btFermerDialogCatalogue);
                groupRadioTheme = view.findViewById(R.id.radioGroupTheme);
                btnRadioLight = view.findViewById(R.id.radioBtnLight);
                btnRadioDark = view.findViewById(R.id.radioBtnDark);

                //Bouton CLose pour FERMER l'alertDialog --------------------------------------------------
                btClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        alertDialog1.dismiss();
                    }
                });

//                // Un bouton est coché?
//                boolean checked = ((RadioButton) view).isChecked();

                //Gestion Bouton Radio --------------------------------------------------
//                switch(view.getId()) {
//                    case R.id.radioBtnLight:
//                        if (checked && btnRadioLight.getText().toString().equals("Thème clair"))
//                        {
//                            btnRadioLight.isChecked();
//                            //Valeur du bouton radio "THÈME CLAIR"
//                            theme = 1;
//                        }
//                        break;
//                    case R.id.radioBtnDark:
//                        if (checked && btnRadioDark.getText().toString().equals("Thème sombre"))
//                        {
//                            btnRadioDark.isChecked();
//                            //Valeur du bouton radio "THÈME SOMBRE"
//                            theme = 2;
//                        }
//                        break;
//                }
                    alertDialog1.show();



            }
        });

        //BOUTONINSPIRATION
        imgBtnInspiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Nous diriges vers une page url avec des motifs préfait
                String url = "https://ruwix.com/the-rubiks-cube/rubiks-cube-patterns-algorithms/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }


    //--------------------------------------------------------------------------------------
    //------------------ STARTQRSCANNER() ------------------
    private void startQRScanner() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.setPrompt("Scannez un code QR");
        integrator.setBeepEnabled(true);
        integrator.initiateScan();
    }

    //--------------------------------------------------------------------------------------
    //------------------ ONACTIVITYRESULT() ------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //VÉRIFIER LE CODE
        if (requestCode == IntentIntegrator.REQUEST_CODE)
        {
            //SI LE CODE QR EST VALIDE
            if (resultCode == RESULT_OK)
            {
                String contents = data.getStringExtra(Intents.Scan.RESULT);
                // Utilisez le contenu du code QR ici.

                String url = contents.toString();

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                finish();
            }
            //SI LE CODE QR N'EST PAS VALIDE
            else if (resultCode == RESULT_CANCELED) {
                // Le scanner a été annulé.

                System.out.println("ERREUR --------------------------------------------");

                finish();

            }
        }
    }

    //--------------------------------------------------------------------------------------
    //------------------ onCreateOptionsMenu() ------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);

        //Icône qui devienne enable dans la page active
        // Catalogue (0)
        menu.getItem(0).setEnabled(true);
        menu.getItem(0).getIcon().setAlpha(255);

//        //Ajouter (1)
//        menu.getItem(1).setEnabled(true);
//        menu.getItem(1).getIcon().setAlpha(255);

        //Paramètre (2)
        menu.getItem(2).setEnabled(false);
        menu.getItem(2).getIcon().setAlpha(125);
        return true;
    }

    //--------------------------------------------------------------------------------------
    //------------------ onOptionsItemSelected() ------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            //BOUTON ITEM ROBOT (PARAMÈTRES)
            case R.id.ItmRobot:
            {
                //Appeler une activité
//                Intent intent = new Intent(this, Activity_Parametres.class);
//                startActivity(intent);
                finish();
                return true;
            }

            //BOUTON CATALOGUE (VISUALISER CATALOGUE)
            case R.id.ItmCatalogue:
            {
                //Appeler une activité
                Intent intent = new Intent(this, Activity_Catalogue.class);
                startActivity(intent);
                finish();
                return true;
            }

            //BOUTON AJOUTER (AJOUTER UN MOTIF)
            case R.id.ItmAjouterMotif:
            {
                //Appeler une activité
                Intent intent = new Intent(this, Activity_AjoutMotif.class);
                startActivity(intent);
                finish();
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //--------------------------------------------------------------------------------------
    // ONBACKPRESSED() ---------------------------------------------
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, Activity_Catalogue.class);
        startActivity(intent);
        finish();
    }




}

