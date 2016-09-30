package com.example.nguyendhoang.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.nguyendhoang.simpletodo.customize.Schdule;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class EditItemActivity extends AppCompatActivity {
    EditText etTaskNameEdit;
    EditText etDescriptionEdit;
    String spinnerProrityEdit;
    String spinnerStatusEdit;
    int position;
    int code;

    String [] Prioritylist ={"LOW","HIGH","MEDIUM"};
    String [] StatusList = {"DONE", "TO-DO"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        ArrayAdapter<String> arrayAdapterPriority =
                new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Prioritylist);
        MaterialBetterSpinner betterSpinnerPrority = (MaterialBetterSpinner) findViewById(R.id.dwlPriorityLevelEdit);
        betterSpinnerPrority.setAdapter(arrayAdapterPriority);

        ArrayAdapter<String> arrayAdapterStatus =
                new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, StatusList);
        MaterialBetterSpinner betterSpinnerStatus = (MaterialBetterSpinner) findViewById(R.id.dwlStausEdit);
        betterSpinnerStatus.setAdapter(arrayAdapterStatus);

//        get editText, position, code from intent
        Schdule schedule= (Schdule) getIntent().getSerializableExtra("schedule");
        position= getIntent().getIntExtra("position", 0);
        code= getIntent().getIntExtra("code", 0);

//        set text to EditText View
        etTaskNameEdit = (EditText) findViewById(R.id.etTaskNameEdit);
        etTaskNameEdit.setText(schedule.getTaskName());

        etDescriptionEdit = (EditText) findViewById(R.id.etDescriptionEdit);
        etDescriptionEdit.setText(schedule.getDescription());

        spinnerProrityEdit=schedule.getPriorityLevel();
        spinnerStatusEdit=schedule.getStatus();

        int spinnerPositionStatus =arrayAdapterStatus.getPosition(schedule.getStatus());
        betterSpinnerStatus.setText(arrayAdapterStatus.getItem(spinnerPositionStatus).toString());

        int spinnerPositionPriority =arrayAdapterPriority.getPosition(schedule.getPriorityLevel());
        betterSpinnerPrority.setText(arrayAdapterPriority.getItem(spinnerPositionPriority).toString());



        onSelectedItemPrority();
        onSelectedItemStatus();
    }


    public void OnClickSaveEdit(View v){
        String txtTaskName = etTaskNameEdit.getText().toString().trim();
        String txtDesc = etDescriptionEdit.getText().toString().trim();
//        Prepare data intent
        Intent data = new Intent();

        Schdule updatedSchedule = new Schdule(txtTaskName,txtDesc,spinnerProrityEdit,spinnerStatusEdit);
        data.putExtra("position",position);
        data.putExtra("updated_schedule",updatedSchedule);
        data.putExtra("code",200);

        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        finish();
    }

    private void onSelectedItemStatus(){
        MaterialBetterSpinner betterSpinnerPrority = (MaterialBetterSpinner) findViewById(R.id.dwlStausEdit);
        betterSpinnerPrority.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spinnerStatusEdit=parent.getItemAtPosition(position).toString();
            }
        });
    }

    private void onSelectedItemPrority(){
        MaterialBetterSpinner betterSpinnerPrority = (MaterialBetterSpinner) findViewById(R.id.dwlPriorityLevelEdit);
        betterSpinnerPrority.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spinnerProrityEdit=parent.getItemAtPosition(position).toString();
            }
        });
    }
}
