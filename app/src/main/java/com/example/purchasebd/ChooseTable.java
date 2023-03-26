package com.example.purchasebd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ChooseTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_table);

        ListView listTable = findViewById(R.id.listView);
        listTable.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.name_table, android.R.layout.simple_list_item_single_choice);
        listTable.setAdapter(adapter);

        String[] nameTable = getResources().getStringArray(R.array.name_table);

        Button chooseButton = findViewById(R.id.buttonChoose);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AdapterView.INVALID_POSITION == listTable.getCheckedItemPosition()) {
                    Toast.makeText(ChooseTable.this, "Please, choose table!", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(ChooseTable.this, MainActivity.class);
                    intent.putExtra("name_table", nameTable[listTable.getCheckedItemPosition()]);
                    startActivity(intent);
                }
            }
        });
    }
}