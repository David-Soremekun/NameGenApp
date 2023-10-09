package com.example.namegen;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class MainActivity extends AppCompatActivity{



    enum TypeOfName{

        eMale,
        eFemale,
        eBoth
    }
    enum Languages{
        eEnglish,
        eFrench,
        eGerman,
        eSpanish,
        eItalian,
        eArabic,
        ePortuguese,
        eJapanese,
        eNorwegian,
        ePolish,
        eSwedish,
        eRussian,
        eDutch,
        eNone

    }
    Spinner genderSpin;
    Spinner langSpin;
    Button button;
    TypeOfName var= TypeOfName.eBoth;
    Languages chosenLang= Languages.eNone;
    Random randInt = new Random();
    public String[] languages={ "English","French","Spanish","Italian","German", "Portuguese",
            "Norwegian", "Swedish","Japanese", "Polish","Russian", "Arabic"};
    String chosenNameType;
    public String genName;
    public int nameCount=12;
    public Vector<String> listName = new Vector();
    ArrayList<String> femFirstName;// = new ArrayList<String>();
    ArrayList<String> mascFirstName;// = new ArrayList<String>();
    ArrayList<String> LastName;// = new ArrayList<String>();

    /*List<String> frFirstNameB
            = new ArrayList<String>();*/
    public void ReadFile(String filePath,ArrayList<String> testArray) throws IOException {

        try {
            String text="";

            InputStream is = getAssets().open("maleNames.txt");
            int size = is.available();
            byte[] buffer= new byte[size];

            is.read(buffer);
            is.close();

            text= new String(buffer);
            System.out.println(text);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void DisplayNames() { }
    public void PrintNames(){
        for (int i=0; i<nameCount; i++) {
            System.out.println(listName.get(i));
            System.out.println("");
        }
    }
    public void RemoveNamesFromDisplay(){

    }

    void GenerateNames()
    {



    }
    public void CreateToasty(){
        CharSequence message;
        
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genderSpin = findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.typesOfName,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpin.setAdapter(adapter);
        genderSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                String value=parent.getItemAtPosition(pos).toString();
                if(value.equals("Masculine")){
                    var=TypeOfName.eMale;
                    //Log.d("MainActivity", "Selected: "+var);
                }
                else if(value.equals("Feminine")){
                    var=TypeOfName.eFemale;
                    //Log.d("MainActivity", "Selected: "+var);
                }
                else{
                    var=TypeOfName.eBoth;
                    //Log.d("MainActivity", "Selected: "+var);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        langSpin=findViewById(R.id.language_spinner);
        ArrayAdapter<CharSequence> langAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.languages,
                android.R.layout.simple_spinner_dropdown_item
        );
        //langSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        langSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                String lang=parent.getItemAtPosition(pos).toString();

                switch (lang){
                    case "English":
                        chosenLang=Languages.eEnglish;


                    case "Spanish":
                        chosenLang=Languages.eSpanish;
                        break;
                    case "French":
                        chosenLang=Languages.eFrench;
                        break;
                    case "Portuguese":
                        chosenLang=Languages.ePortuguese;
                        break;
                    case "Italian":
                        chosenLang=Languages.eItalian;
                        break;
                    case "German":
                        chosenLang=Languages.eGerman;
                        break;
                    case "Japanese":
                        chosenLang=Languages.eJapanese;
                        break;
                    case "Swedish":
                        chosenLang=Languages.eSwedish;
                        break;
                    case "Russian":
                        chosenLang=Languages.eRussian;
                        break;
                    case "Polish":
                        chosenLang=Languages.ePolish;
                        break;
                    case "Norwegian":
                        chosenLang=Languages.eNorwegian;
                        break;
                    case "Arabic":
                        chosenLang=Languages.eArabic;
                        break;
                    default:
                        System.out.printf("Oopsie Poopsie!!!");
                        break;

                }
                Log.d("MainActivity", "Selected: "+chosenLang);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        langSpin.setAdapter(langAdapter);


        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity","Generated Names: ");
            }
        });


        /*try {
            ReadFile("assets\\French\\maleNames.txt",frFirstNameB);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

}