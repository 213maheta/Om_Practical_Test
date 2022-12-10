package com.om.practicaltest.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.om.practicaltest.model.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM employee")
    List<Employee> getAll();

    @Query("SELECT * FROM employee WHERE id IN (:userIds)")
    List<Employee> loadAllByIds(int[] userIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Employee> employees);

    @Delete
    void delete(Employee employee);

}
