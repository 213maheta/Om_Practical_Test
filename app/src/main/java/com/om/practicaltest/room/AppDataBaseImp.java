package com.om.practicaltest.room;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class AppDataBaseImp {


    @Provides
    public EmployeeDao provideChannelDao(AppDataBase dataBase) {
        return dataBase.employeeDao();
    }

    @Provides
    @Singleton
    AppDataBase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context,
                AppDataBase.class,
                "AppDataBase"
        ).allowMainThreadQueries()
                .build();
    }
}
