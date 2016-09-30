package com.example.nguyendhoang.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nguyendhoang.simpletodo.customize.Schdule;
import com.example.nguyendhoang.simpletodo.customize.ScheduleAdapter;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Schdule> items;
    ArrayAdapter<Schdule> itemAdapter;
    ListView lvItems;

    private final int REQUEST_CODE = 20;
    private final int CREATE_CODE = 21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readItems();
//        Schdule schedule1 = new Schdule("Di hoc", "Den troung", "MEDIUM", "DONE");
//        Schdule schedule2 = new Schdule("LAM BAI", "1.2 -2-2-", "HIGH", "TO-DO");


        lvItems = (ListView) findViewById(R.id.lvItems);
        itemAdapter = new ScheduleAdapter(this, items);
        lvItems.setAdapter(itemAdapter);
        setupListViewListener();
        setupListViewEditListener();
    }

    public void onAddItem(View v) {
        Intent i = new Intent(MainActivity.this, create_schedule.class);

        startActivityForResult(i, CREATE_CODE);
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        items.remove(position);
                        itemAdapter.notifyDataSetChanged();
                        writeItems();
                        Toast.makeText(MainActivity.this, "Item - position : " + (position + 1) + " has been removed.", Toast.LENGTH_LONG).show();

                        return true;
                    }
                });
    }

    private void setupListViewEditListener() {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                create intent between MainActivity and  EditItemActivity
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);

                Schdule schedule = items.get(position);
//                pass text and position of that item to EditItemActivity
                i.putExtra("position", position);
                i.putExtra("schedule", schedule);
                i.putExtra("code", 400);

                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            Schdule updatedSchedule = (Schdule) data.getSerializableExtra("updated_schedule");

            int code = data.getExtras().getInt("code", 0);
            int position = data.getExtras().getInt("position", 0);

            items.set(position,updatedSchedule);
            itemAdapter.notifyDataSetChanged();
            writeItems();
        }

        if (resultCode == RESULT_OK && requestCode == CREATE_CODE) {
            Schdule newschedule = (Schdule) data.getSerializableExtra("NEW_SCHEDULE");

            items.add(newschedule);
            itemAdapter.notifyDataSetChanged();
            writeItems();
        }

    }

    private void readItems(){
        File fileDir = getFilesDir();
        File todoFile = new File(fileDir, "todo.txt");
        try {
            items = new ArrayList<>();
            List<String> listOutput = new ArrayList<String>(FileUtils.readLines(todoFile));
            for (int i=0; i< listOutput.size(); i++){
                String inputString = listOutput.get(i);

                String[] scheduleString = inputString.split(";");

                String taskName = scheduleString[0];
                String desc = scheduleString[1];
                String priorityLevel = scheduleString[2];
                String status = scheduleString[3];

                Schdule scheduleItem = new Schdule(taskName,desc,priorityLevel,status);
                items.add(scheduleItem);
            }
        }catch (IOException e){
            items = new ArrayList<Schdule>();
        }
    }

    private void writeItems(){
        File fileDir = getFilesDir();
        File todoFile = new File(fileDir, "todo.txt");
        try {
            List<String> listOutput = new ArrayList<>();
            for(int i=0; i<items.size(); i++) {
                Schdule scheduleItem = items.get(i);

                String outputString = scheduleItem.getTaskName() +';'+ scheduleItem.getDescription()
                        +';'+ scheduleItem.getPriorityLevel() +';'+ scheduleItem.getStatus();
                listOutput.add(outputString);
            }
            FileUtils.writeLines(todoFile, listOutput);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
