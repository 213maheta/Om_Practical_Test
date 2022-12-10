package com.om.practicaltest.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.om.practicaltest.model.Employee;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Database(entities = {Employee.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();
    public static AppDataBase dataBase;


    public static synchronized AppDataBase getDatabase(Context context)
    {
        if(dataBase == null)
        {
            dataBase = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,"database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dataBase;
    }
}

