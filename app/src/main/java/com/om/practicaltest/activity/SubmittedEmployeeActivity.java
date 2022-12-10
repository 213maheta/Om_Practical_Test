package com.om.practicaltest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.om.practicaltest.R;
import com.om.practicaltest.adapter.AdapterEmployee;
import com.om.practicaltest.databinding.ActivityMainBinding;
import com.om.practicaltest.databinding.ActivitySubmittedEmployeeBinding;
import com.om.practicaltest.model.Employee;

import java.util.ArrayList;

public class SubmittedEmployeeActivity extends AppCompatActivity {

    private ActivitySubmittedEmployeeBinding binding;
    AdapterEmployee adapterEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_submitted_employee);


        ArrayList<Employee> list = (ArrayList<Employee>) getIntent().getSerializableExtra("mylist");
        setRCV(list);
    }

    private void setRCV(ArrayList<Employee> list) {
        adapterEmployee = new AdapterEmployee();
        adapterEmployee.disable = true;
        adapterEmployee.employeeList = list;

        binding.rcvSalary.setAdapter(adapterEmployee);
    }
}