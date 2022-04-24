package developer.osmanforhad.androidsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //define variables
    EditText editText1, editText2;
    TextView textView1, textView2;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initiate the variables and connect with XML UI Id
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        button1 = (Button) findViewById(R.id.storeId);
        button2 = (Button) findViewById(R.id.loadId);

        //setup on click event in the button1
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the data store method
                SaveData();
            }
        });

        //setup on click event in the button2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the data display method
                loadData();
            }
        });

    }

    //Method for store data into shared preferences
    public void SaveData(){

        //User input to String Conversion
        String fname = editText1.getText().toString();
        String lname = editText2.getText().toString();

        //shared preferences instance
        SharedPreferences sharedPreferences = getSharedPreferences("myFile", Context.MODE_PRIVATE);
        //edit SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //put user input inside the SharedPreferences memory
        editor.putString("fnameKey", fname);
        editor.putString("lnameKey", lname);
        //save data into SharedPreferences memory
        editor.commit();
    }

    //Method for fetch data from shared preferences
    public void loadData(){
        //shared preferences instance
        SharedPreferences sharedPreferences = getSharedPreferences("myFile", Context.MODE_PRIVATE);
        //check SharedPreferences memory for data exists or not
        if(sharedPreferences.contains("fnameKey") && sharedPreferences.contains("lnameKey")){
            //load data from SharedPreferences memory
          String FirstName =  sharedPreferences.getString("fnameKey", "First Name Not Found");
            String LastName =  sharedPreferences.getString("lnameKey", "Last Name Not Found");

            //Display Data
            textView1.setText(FirstName);
            textView2.setText(LastName);
        }
        else {
            Toast.makeText(MainActivity.this, "There is no data inside your App Shared Memory", Toast.LENGTH_SHORT).show();
        }
    }


}