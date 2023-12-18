package com.tuhoc.demomvp.presenters;

import com.tuhoc.demomvp.contract.StudentContract;
import com.tuhoc.demomvp.models.dao.StudentDao;
import com.tuhoc.demomvp.models.entity.Student;

import java.util.List;

public class StudentPresenter implements StudentContract.Presenter{
    private StudentContract.View view;
    private StudentDao studentDao;

    public StudentPresenter(StudentContract.View view, StudentDao studentDao) {
        this.view = view;
        this.studentDao = studentDao;
    }

    @Override
    public void loadStudents() {
        List<Student> students = studentDao.getAll();
        view.showStudents(students);
    }

    @Override
    public void addStudent(String name, int age) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        studentDao.insert(student);
    }

    @Override
    public void updateStudent(int id, String name, int age) {
        Student student = studentDao.getStudentById(id);
        student.setName(name);
        student.setAge(age);
        studentDao.update(student);
    }

    @Override
    public void deleteStudent(int id) {
        Student student = studentDao.getStudentById(id);
        studentDao.delete(student);
    }
}
