package com.tuhoc.demomvp.models.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tuhoc.demomvp.models.dao.StudentDao;
import com.tuhoc.demomvp.models.entity.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class StudentDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();
    private static StudentDatabase instance;

    public static StudentDatabase getInstance(final Context context){
        if (instance == null){
            synchronized (StudentDatabase.class){
                instance = Room.databaseBuilder(context,StudentDatabase.class,"student").allowMainThreadQueries().build();
            }
        }
        return instance;
    }
}
