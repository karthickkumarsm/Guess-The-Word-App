package com.example.guesstheword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String[] General=new String[]{
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "India", "Chennai","Madurai",
    "Tamilnadu","Sivakasi","Tamil","English","Hindi","Maths","Java","Python"};
    String general;
    Random random;
    TextView txtCorrectAnswer,txtRightAnswer,txtQuestionContainer,yourscore;
    EditText etUserInput;
    Button btShow,btCheck,btNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCorrectAnswer=findViewById(R.id.txtCorrectAnswer);
        txtRightAnswer=findViewById(R.id.txtRightAnswer);
        txtQuestionContainer=findViewById(R.id.txtQuestionContainer);
        etUserInput=findViewById(R.id.etUserInput);
        btCheck=findViewById(R.id.btCheck);
        btShow=findViewById(R.id.btShow);
        btNext=findViewById(R.id.btNext);
        yourscore=findViewById(R.id.score);
        random=new Random();
        general=General[random.nextInt(General.length)];
        txtQuestionContainer.setText(mixWords(general));
        btCheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(etUserInput.getText().toString().equalsIgnoreCase(general)){
                    Dialog dialog=new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.correct_dialog);
                    Button bthide=dialog.findViewById(R.id.btConfirmDialog);
                    dialog.show();
                    bthide.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            etUserInput.setText("");
                            general=General[random.nextInt(General.length)];
                            txtQuestionContainer.setText(mixWords(general));
                            dialog.dismiss();
                        }
                    });
                    String s=yourscore.getText().toString();
                    int i=Integer.parseInt(s);
                    i++;
                    String a=new Integer(i).toString();
                    yourscore.setText(a);
                }
                else if(etUserInput.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "You Have Entered Nothing!!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "You Are Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                general=General[random.nextInt(General.length)];
                txtQuestionContainer.setText(mixWords(general));
                etUserInput.setText("");
                txtCorrectAnswer.setVisibility(View.INVISIBLE);
                txtRightAnswer.setVisibility(View.INVISIBLE);
            }
        });
        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCorrectAnswer.setVisibility(View.VISIBLE);
                txtRightAnswer.setVisibility(View.VISIBLE);
                txtRightAnswer.setText(general);
            }
        });
    }
    private String mixWords(String word){
        List<String> words= Arrays.asList(word.split(""));
        Collections.shuffle(words);
        String mixed="";
        for(String i:words){
            mixed+=i;
        }
        return mixed;
    }
}