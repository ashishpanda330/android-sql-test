package app.example.com.sqldbtest;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class HomeActivity extends Activity {

    RecyclerView recyclerView;
    List<StudentsModel> studentsModelList;
    DBHelper dbHelper;
    StudentsRecyclerView studentRecyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        dbHelper = new DBHelper(HomeActivity.this);
        studentsModelList = dbHelper.getAllStudents();
        studentRecyAdapter = new StudentsRecyclerView(HomeActivity.this,studentsModelList);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(HomeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(studentRecyAdapter);



        FloatingActionButton addFab=findViewById(R.id.fab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

}
