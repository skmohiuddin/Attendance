package com.turningpoint.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter4 extends RecyclerView.Adapter<CourseAdapter4.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<CourseModel3> courseModelArrayList3;
    private Context context;
    private View.OnClickListener onClickListener;
    // creating a constructor for our variables.
    public CourseAdapter4(ArrayList<CourseModel3> courseModelArrayList3, Context context) {
        this.courseModelArrayList3 = courseModelArrayList3;
        this.context = context;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<CourseModel3> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        courseModelArrayList3 = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // setting data to our views of recycler view.

        CourseModel3 model = courseModelArrayList3.get(position);
        holder.courseNameTV.setText(model.getCourseName());


        holder.courseDescTV3.setText(model.getSendornort());

        holder.courseDescTV2.setText(model.getCourseDescription());

}



    @Override
    public int getItemCount() {
        // returning the size of array list.
        return courseModelArrayList3.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private final TextView courseNameTV;

        private final TextView courseDescTV2;
        private final TextView courseDescTV3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);

            courseDescTV2 = itemView.findViewById(R.id.textView43);
            courseDescTV3 = itemView.findViewById(R.id.idTVCourseDescription);

        }
    }
}
