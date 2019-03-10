package app.example.com.sqldbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VER = 1;
    private static final String DB_NAME = "Students.db";
    private static final String TABLE_NAME = "StudentTable";
    //Column names
    private static final String ID_COL = "Id";
    private static final String NAME_COL = "Name";
    private static final String ROLLNO_COL = "RollNo";
    private static final String CLG_COL = "College";

    public DBHelper(Context context) {
        super(context,DB_NAME,null,DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE
                ="CREATE TABLE "+TABLE_NAME+
                "("+ID_COL+" INTEGER PRIMARY KEY,"+NAME_COL+" VARCHAR(20) NOT NULL,"+ROLLNO_COL+" VARCHAR(5) NOT NULL,"+CLG_COL+" VARCHAR(30))";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addStudent(StudentsModel model){
        SQLiteDatabase db = this.getWritableDatabase();

        //to assign values for database
        ContentValues values = new ContentValues();
        values.put(ID_COL,model.getId());
        values.put(NAME_COL,model.getName());
        values.put(ROLLNO_COL,model.getRollNo());
        values.put(CLG_COL,model.getClg());
        db.insert(TABLE_NAME,null,values);
    }

    public int updateStudent(StudentsModel model){
        SQLiteDatabase db = this.getWritableDatabase();

        //to assign values for database
        ContentValues values = new ContentValues();
        values.put(NAME_COL,model.getName());
        values.put(ROLLNO_COL,model.getRollNo());
        values.put(CLG_COL,model.getClg());
        return db.update(TABLE_NAME,values,ID_COL+"=?",new String[]{String.valueOf(model.getId())});
    }

    public List<StudentsModel> getAllStudents(){
        List<StudentsModel> studentsList = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                StudentsModel model = new StudentsModel();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setName(cursor.getString(1));
                model.setRollNo(cursor.getString(2));
                model.setClg(cursor.getString(3));

                studentsList.add(model);
            }while (cursor.moveToNext());
        }
        return studentsList;
    }
}
