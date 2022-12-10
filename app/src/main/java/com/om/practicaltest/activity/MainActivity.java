package com.om.practicaltest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.om.practicaltest.R;
import com.om.practicaltest.adapter.AdapterEmployee;
import com.om.practicaltest.databinding.ActivityMainBinding;
import com.om.practicaltest.helperutils.EventType;
import com.om.practicaltest.viewmodel.MainViewModel;

import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    AdapterEmployee adapterEmployee;
    private MainViewModel viewModel;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel =  new ViewModelProvider(this).get(MainViewModel.class);

        setRCV();
        setObserver();

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this, SubmittedEmployeeActivity.class);
                intent.putExtra("mylist", (Serializable) adapterEmployee.employeeList);
                startActivity(intent);
            }
        });
    }

    private void setObserver() {
        viewModel.getEvent().observe(this, new Observer<EventType>() {
            @Override
            public void onChanged(EventType eventType) {
                if(eventType == EventType.DATABASE_UPDATED)
                {
                    adapterEmployee.employeeList.clear();
                    adapterEmployee.editable = false;
                    adapterEmployee.employeeList = viewModel.getAllEmployeeOffline();
                    adapterEmployee.notifyDataSetChanged();
                    binding.progressbar.setVisibility(View.GONE);
                    adapterEmployee.editable = true;
                }
                else if(eventType == EventType.FAIL)
                {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    binding.progressbar.setVisibility(View.GONE);
                }

            }
        });
    }

    private void setRCV() {
        adapterEmployee = new AdapterEmployee();
        adapterEmployee.employeeList = viewModel.getAllEmployeeOffline();
        binding.rcvSalary.setAdapter(adapterEmployee);
        adapterEmployee.editable = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.progressbar.setVisibility(View.VISIBLE);
        viewModel.getAllEmployee();
    }
}