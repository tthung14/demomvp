package com.tuhoc.demomvp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tuhoc.demomvp.R;
import com.tuhoc.demomvp.models.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<Student> mListStudents;
    private OnItemClickListener itemClickListener;

    public StudentAdapter(ArrayList<Student> mListStudents, OnItemClickListener itemClickListener) {
        this.mListStudents = mListStudents;
        this.itemClickListener = itemClickListener;
    }

    public void setStudentList(List<Student> students) {
        mListStudents.clear();
        mListStudents.addAll(students);
    }

    @NonNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, int position) {
        Student student = mListStudents.get(position);

        holder.tvId.setText(String.valueOf(student.getId()));
        holder.tvName.setText(student.getName());
        holder.tvAddress.setText(String.valueOf(student.getAge()));
    }

    @Override
    public int getItemCount() {
        return mListStudents.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName, tvAddress;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                    itemClickListener.onItemClick(mListStudents.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Student student);
    }
}
