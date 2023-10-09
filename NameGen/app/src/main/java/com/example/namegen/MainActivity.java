package com.example.namegen;

import android.content.res.Resources;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    public String[] languages={ "English","French","Spanish","Italian","German", "Portuguese",
            "Norwegian", "Swedish","Japanese", "Polish","Russian", "Arabic"};
    String chosenNameType;
    public String genName;
    public int nameCount=12;
    public Vector<String> nameList = new Vector();
    List<String> femFirstName;// = new ArrayList<String>();
    List<String> mascFirstName;// = new ArrayList<String>();
    List<String> LastName;// = new ArrayList<String>();

    String [] arrLastName;
    String [] arrMaleName;
    String [] arrFemName;

    /*List<String> frFirstNameB
            = new ArrayList<String>();*/
    public void ReadFile(String filePath,ArrayList<String> testArray) throws IOException {

        File file= new File(filePath);
        testArray = new ArrayList<String>();
        // load data from file
        BufferedReader bf = new BufferedReader(
                new FileReader(file));

        // read entire line as string
        String line = bf.readLine();
        // checking for end of file
        while (line != null) {
            line = bf.readLine();
            testArray.add(line);
        }
        // closing buffer reader object
        bf.close();
        // storing the data in arraylist to array
        String[] array
                = testArray.toArray(new String[0]);
        // printing each line of file
        // which is stored in array
        for (String str : array)
        {
            System.out.println(str);
        }
    }
    public void DisplayNames() { }
    public void PrintNames(){
        for (int i=0; i<nameCount; i++) {
            System.out.println(nameList.get(i));
            System.out.println("");
        }
    }
    public void RemoveNamesFromDisplay(){

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
            Collections.shuffle(LastName);

            n= randInt.nextInt(mascFirstName.size());
            fn = randInt.nextInt(femFirstName.size());
            ln= randInt.nextInt(LastName.size());
            genderOption= randInt.nextInt(2)+1;

            if(var.equals(TypeOfName.eMale)){
                genName=mascFirstName.get(n)+" "+ LastName.get(ln)+"M";
                nameList.add(genName);
            }else if(var.equals(TypeOfName.eFemale)) {
                genName=femFirstName.get(fn)+" "+LastName.get(ln)+"F";
                nameList.add(genName);
            }else {
                if(genderOption==2){
                    genName=mascFirstName.get(n)+" "+ LastName.get(ln)+"M";
                    nameList.add(genName);
                }else{
                    genName=femFirstName.get(fn)+" "+LastName.get(ln)+"F";
                    nameList.add(genName);
                }
        }


        }
        PrintNames();
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
                        arrMaleName = res.getStringArray(R.array.frenchMaleNames);
                        arrLastName = res.getStringArray(R.array.frenchLastNames);
                        mascFirstName = Arrays.asList(arrMaleName);
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
                        break;
                    case "Norwegian":
                        chosenLang=Languages.eNorwegian;
                        break;
                    case "Arabic":
                        chosenLang=Languages.eArabic;
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
                GenerateNames();
            }
        });


        /*try {
            ReadFile("assets\\French\\maleNames.txt",frFirstNameB);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

}