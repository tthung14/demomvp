package com.tuhoc.demomvp.contract;

import com.tuhoc.demomvp.models.entity.Student;

import java.util.List;

public interface StudentContract {
    interface View {
        void showStudents(List<Student> students);
    }

    interface Presenter {
        void loadStudents();
        void addStudent(String name, int age);
        void updateStudent(int id, String name, int age);
        void deleteStudent(int id);
    }
}
