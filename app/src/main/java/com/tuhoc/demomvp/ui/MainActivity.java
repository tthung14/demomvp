package com.tuhoc.demomvp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuhoc.demomvp.R;
import com.tuhoc.demomvp.adapters.StudentAdapter;
import com.tuhoc.demomvp.contract.StudentContract;
import com.tuhoc.demomvp.models.dao.StudentDao;
import com.tuhoc.demomvp.models.db.StudentDatabase;
import com.tuhoc.demomvp.models.entity.Student;
import com.tuhoc.demomvp.presenters.StudentPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentContract.View {

    private StudentPresenter presenter;

    private Button btnAdd, btnUpdate, btnDelete;
    private EditText edtId, edtName, edtAge;

    private RecyclerView rlvStudent;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        loadStudents();
    }

    private void initView() {
        rlvStudent = findViewById(R.id.rlvStudent);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);

        adapter = new StudentAdapter(new ArrayList<>(), student -> {
            // Xử lý sự kiện khi item được nhấn
            edtId.setText(String.valueOf(student.getId()));
            edtName.setText(student.getName());
            edtAge.setText(String.valueOf(student.getAge()));
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rlvStudent.setLayoutManager(linearLayoutManager);
        rlvStudent.setAdapter(adapter);

        StudentDatabase database = StudentDatabase.getInstance(this);
        StudentDao studentDao = database.studentDao();

        presenter = new StudentPresenter(this, studentDao);
        presenter.loadStudents();

        btnAdd.setOnClickListener(view -> addStudent());
        btnUpdate.setOnClickListener(view -> updateStudent());
        btnDelete.setOnClickListener(view -> deleteStudent());
    }

    private void loadStudents() {
        presenter.loadStudents();
    }

    private void addStudent() {
        String name = edtName.getText().toString();
        int age = Integer.parseInt(edtAge.getText().toString());
        presenter.addStudent(name, age);
        loadStudents();
        clearEditTextFields();
    }

    private void updateStudent() {
        int id = Integer.parseInt(edtId.getText().toString());
        String name = edtName.getText().toString();
        int age = Integer.parseInt(edtAge.getText().toString());
        presenter.updateStudent(id, name, age);
        loadStudents();
        clearEditTextFields();
    }

    private void deleteStudent() {
        int id = Integer.parseInt(edtId.getText().toString());
        presenter.deleteStudent(id);
        loadStudents();
        clearEditTextFields();
    }

    @Override
    public void showStudents(List<Student> students) {
        adapter.setStudentList(students);
        adapter.notifyDataSetChanged();
    }

    private void clearEditTextFields() {
        edtId.setText("");
        edtName.setText("");
        edtAge.setText("");
    }
}