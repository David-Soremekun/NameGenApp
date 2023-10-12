package com.example.namegen;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class MainActivity extends AppCompatActivity{

    Spinner genderSpin;
    Spinner langSpin;
    Button button;
    TextView genText;

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

    TypeOfName var= TypeOfName.eBoth;
    Languages chosenLang= Languages.eNone;

    public String genName;
    public int nameCount=10;


    List<String> femFirstName;
    List<String> mascFirstName;
    List<String> LastName;


    String [] arrLastName;
    String [] arrMaleName;
    String [] arrFemName;

    public Vector<String> nameList = new Vector();
    void ClearArraylist(){
        mascFirstName.clear();
        femFirstName.clear();
        LastName.clear();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        genText=findViewById(R.id.name_container);

        genderSpin = findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.typesOfName,
                R.layout.my_selected_spinner
        );

        adapter.setDropDownViewResource(R.layout.my_dropdown_custom);
        genderSpin.setAdapter(adapter);
        genderSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                String value=parent.getItemAtPosition(pos).toString();
                if(value.equals("Masculine")){
                    var=TypeOfName.eMale;
                    Log.d("MainActivity", "Selected: "+var);
                }
                else if(value.equals("Feminine")){
                    var=TypeOfName.eFemale;
                    Log.d("MainActivity", "Selected: "+var);
                }
                else{
                    var=TypeOfName.eBoth;
                    Log.d("MainActivity", "Selected: "+var);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Resources res= getResources();


        langSpin=findViewById(R.id.language_spinner);
        ArrayAdapter<CharSequence> langAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.languages,
                R.layout.my_selected_spinner
        );
        langAdapter.setDropDownViewResource(R.layout.my_dropdown_custom);
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
                        arrMaleName = res.getStringArray(R.array.frenchMaleNames);
                        arrFemName = res.getStringArray(R.array.frenchFemaleNames);
                        arrLastName = res.getStringArray(R.array.frenchLastNames);
                        mascFirstName = Arrays.asList(arrMaleName);
                        femFirstName = Arrays.asList(arrFemName);
                        LastName = Arrays.asList(arrLastName);

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
                        arrMaleName = res.getStringArray(R.array.polishMaleNames);
                        arrFemName = res.getStringArray(R.array.polishFemaleNames);
                        arrLastName = res.getStringArray(R.array.polishLastNames);
                        mascFirstName = Arrays.asList(arrMaleName);
                        femFirstName = Arrays.asList(arrFemName);
                        LastName = Arrays.asList(arrLastName);

                        break;
                    case "Norwegian":
                        chosenLang=Languages.eNorwegian;
                        break;
                    case "Arabic":
                        chosenLang=Languages.eArabic;
                        arrMaleName = res.getStringArray(R.array.arabicMaleNames);
                        arrFemName = res.getStringArray(R.array.arabicFemaleNames);
                        arrLastName = res.getStringArray(R.array.arabicLastNames);
                        mascFirstName = Arrays.asList(arrMaleName);
                        femFirstName = Arrays.asList(arrFemName);
                        LastName = Arrays.asList(arrLastName);
                        break;

                        default:
                        System.out.printf("Oopsie Poopsie!!!");
                        System.out.println("");
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
                RemoveNamesFromDisplay();
                GenerateNames();
                DisplayNames();
                //ClearArraylist();
            }
        });


        /*try {
            ReadFile("assets\\French\\maleNames.txt",frFirstNameB);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
    public void DisplayNames()
    {

        genText.setText(nameList.toString());
        nameList.clear();

    }
    public void PrintNames(){
        for (int i=0; i<nameCount; i++) {
            System.out.println(nameList.get(i));
            System.out.println("");
        }
    }
    public void RemoveNamesFromDisplay(){
            genText.setText("");
    }

    void GenerateNames() {
        int n;
        int fn;
        int ln;
        int genderOption;
        Random randInt= new Random();

        for(int i=0; i < nameCount; i++)
        {
            Collections.shuffle(mascFirstName);
            Collections.shuffle(femFirstName);
            Collections.shuffle(LastName);

            n= randInt.nextInt(mascFirstName.size());
            fn = randInt.nextInt(femFirstName.size());
            ln= randInt.nextInt(LastName.size());

            genderOption= randInt.nextInt(2)+1;

            if(var.equals(TypeOfName.eMale)){
                genName=mascFirstName.get(n)+" "+ LastName.get(randInt.nextInt(LastName.size()))+" [M]";
                nameList.add(genName);
            }else if(var.equals(TypeOfName.eFemale)) {
                genName=femFirstName.get(fn)+" "+LastName.get(randInt.nextInt(LastName.size()))+" [F]";
                nameList.add(genName);
            }else {
                if(genderOption==2){
                    genName=mascFirstName.get(n)+" "+ LastName.get(randInt.nextInt(LastName.size()))+" [M]";
                    nameList.add(genName);
                }else{
                    genName=femFirstName.get(fn)+" "+LastName.get(ln)+" [F]";
                    nameList.add(genName);
                }
        }


        }
        PrintNames();
    }



}