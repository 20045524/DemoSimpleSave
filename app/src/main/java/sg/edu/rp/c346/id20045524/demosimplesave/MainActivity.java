package sg.edu.rp.c346.id20045524.demosimplesave;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSave, btnRestore;
    EditText etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnRestore = findViewById(R.id.btnRestore);
        etText = findViewById(R.id.etText);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedData = etText.getText().toString();
                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("value", savedData);
                prefEdit.commit();
            }
        });

        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                String loadedData = prefs.getString("value", "No greetings!");
                etText.setText(loadedData);
            }
        });

    }

    //save when app closes aka autosave
    @Override
    protected void onStop() {
        super.onStop();
        // Retrieve the data from the UI elements
        String savedData = etText.getText().toString();

        // Step 1: Obtain the SharedPreferences instance
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        // Step 2: Create a SharedPreferences Editor by calling edit()
        SharedPreferences.Editor prefEdit = prefs.edit();
        // step 3: Set a key-value pair in the editor
        prefEdit.putString("value", savedData);
        // Step 4: Call commit() to save the changes made to the SharedPreferences
        prefEdit.commit();

    }

    //app will autoload savedData
    @Override
    protected void onResume() {
        super.onResume();
        // Step 1: Obtain the SharedPreferences instance
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        // Step 2: Retrieve the saved data from the SharedPreferences
        // with a default value if no matching data
        String loadedData = prefs.getString("value", "No greetings!");
        // Step 3: Update the UI element with the retrieved data
        etText.setText(loadedData);
    }

}