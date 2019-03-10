package app.example.com.sqldbtest;

import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String name;
    String rollNo;
    String clg;
    DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nameEditText = findViewById(R.id.name_edittext);
        final EditText rollNoEditText = findViewById(R.id.rollno_edittext);
        final EditText clgEditText = findViewById(R.id.clg_edittext);
        Button addButton = findViewById(R.id.add_button);
        mDBHelper = new DBHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=nameEditText.getText().toString().trim();
                rollNo=rollNoEditText.getText().toString();
                clg=clgEditText.getText().toString();

                if(name==null || name.equals(""))
                    nameEditText.setError("Enter a proper name");
                else if(rollNo==null || rollNo.equals(""))
                    rollNoEditText.setError("Enter a proper roll no");
                else if(clg==null || clg.equals(""))
                    clgEditText.setError("enter a proper clg");
                else {

                    try {
                        StudentsModel model = new StudentsModel();
                        Random rand = new Random();
                        model.setId(rand.nextInt(100000));
                        model.setName(name);
                        model.setRollNo(rollNo);
                        model.setClg(clg);
                        mDBHelper.addStudent(model);
                    }
                    catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Exception: " + e, Toast.LENGTH_LONG).show();
                    }
                    //Toast.makeText(MainActivity.this, "Details saved sucessfully!", Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}
