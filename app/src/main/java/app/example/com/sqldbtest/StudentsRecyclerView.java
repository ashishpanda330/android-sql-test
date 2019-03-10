package app.example.com.sqldbtest;

import android.graphics.ColorSpace;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class StudentsRecyclerView extends RecyclerView.Adapter<StudentsRecyclerView.MyViewHolder>
{

    HomeActivity homeActivity;
    List<StudentsModel> studentsModelList;

    public StudentsRecyclerView(HomeActivity homeActivity, List<StudentsModel> studentsModelList) {
        this.homeActivity=homeActivity;
        this.studentsModelList=studentsModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        StudentsModel studentsModel = studentsModelList.get(position);
        myViewHolder.nameTextView.setText(studentsModel.getName());
        myViewHolder.clgTextView.setText(studentsModel.getClg());
        myViewHolder.rollnoTextView.setText(studentsModel.getRollNo());
    }

    @Override
    public int getItemCount() {
        return studentsModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView;
        TextView rollnoTextView;
        TextView clgTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView=(TextView)itemView.findViewById(R.id.item_name);
            rollnoTextView=itemView.findViewById(R.id.item_rollno);
            clgTextView=itemView.findViewById(R.id.item_clg);
        }


    }

}
