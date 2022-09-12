package com.example.listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;
FloatingActionButton floatingActionButton;
ArrayList<String> namearralist;
ArrayAdapter arrayAdapter;
String birnarsa="toza narsa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview1);
        floatingActionButton=findViewById(R.id.buttonPuls);
        namearralist=new ArrayList<>();

        namearralist.add("Fourcade");
        namearralist.add("Mansur");
        namearralist.add("Nodir");

        arrayAdapter=new ArrayAdapter(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,namearralist);
        listView.setAdapter(arrayAdapter);



        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Yangi foydalanuvchi qo'shish");
                builder.setMessage("Foydalanuvchi ismini yoz");


                View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.mansur,null);
                builder.setView(view);

                EditText editText=view.findViewById(R.id.edittext1);
                Button button=view.findViewById(R.id.button1);
                Dialog dialog=builder.create();


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       if(editText.getText().toString().isEmpty()){
                           editText.setError("odingni yoz");
                       }else {
                           namearralist.add(editText.getText().toString());
                           arrayAdapter=new ArrayAdapter(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,namearralist);
                           listView.setAdapter(arrayAdapter);
                          dialog.dismiss();
                       }
                    }
                });

                dialog.show();

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete");
                builder.setMessage(namearralist.get(i)+"ni o'chirasizmi?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        namearralist.remove(i);
                        arrayAdapter=new ArrayAdapter(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,namearralist);
                        listView.setAdapter(arrayAdapter);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();

                return true;
            }
        });


    }
}