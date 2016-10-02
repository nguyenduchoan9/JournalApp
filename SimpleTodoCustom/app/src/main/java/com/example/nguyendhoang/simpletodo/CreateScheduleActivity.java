package com.example.nguyendhoang.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.nguyendhoang.simpletodo.customize.Schedule;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class CreateScheduleActivity extends AppCompatActivity {
    String [] Prioritylist ={"LOW","HIGH","MEDIUM"};
    String [] StatusList = {"DONE", "TO-DO"};

    String priority="";
    String status="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);

        ArrayAdapter<String> arrayAdapterPriority =
                new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Prioritylist);
        MaterialBetterSpinner betterSpinnerPrority = (MaterialBetterSpinner) findViewById(R.id.dwlPriorityLevel);
        betterSpinnerPrority.setAdapter(arrayAdapterPriority);

        ArrayAdapter<String> arrayAdapterStatus =
                new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, StatusList);
        MaterialBetterSpinner betterSpinnerStatus = (MaterialBetterSpinner) findViewById(R.id.dwlStaus);
        betterSpinnerStatus.setAdapter(arrayAdapterStatus);

        onSelectedItemPrority();
        onSelectedItemStatus();
    }

    public void onClickCreateChedule(View v){
        EditText taskName= (EditText) findViewById(R.id.etTaskName);
        String txtTaskName = taskName.getText().toString().trim();

        EditText description= (EditText) findViewById(R.id.etDescription);
        String txtDescription = description.getText().toString().trim();

        String txtPriority = priority;
        String txtStatus = status;

        Intent i = new Intent();

        Schedule schedule = new Schedule(txtTaskName,txtDescription,txtPriority,txtStatus);
        i.putExtra("NEW_SCHEDULE",schedule);

        setResult(RESULT_OK, i);
        finish();
    }


    private void onSelectedItemStatus(){
        MaterialBetterSpinner betterSpinnerPrority = (MaterialBetterSpinner) findViewById(R.id.dwlStaus);
        betterSpinnerPrority.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                status=parent.getItemAtPosition(position).toString();
            }
        });
//        betterSpinnerPrority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                ;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    private void onSelectedItemPrority(){
        MaterialBetterSpinner betterSpinnerPrority = (MaterialBetterSpinner) findViewById(R.id.dwlPriorityLevel);
        betterSpinnerPrority.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                priority=parent.getItemAtPosition(position).toString();
            }
        });
//        betterSpinnerPrority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }
}


