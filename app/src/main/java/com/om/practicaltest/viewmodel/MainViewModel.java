package com.om.practicaltest.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.om.practicaltest.helperutils.EventType;
import com.om.practicaltest.model.Employee;
import com.om.practicaltest.repository.MainRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final MainRepository repository;

    @Inject
    MainViewModel(MainRepository repository)
    {
        this.repository = repository;
    }

    public void getAllEmployee()
    {
        repository.getAllEmployee();
    }

    public List<Employee> getAllEmployeeOffline()
    {
        return repository.getAllEmployeeOffline();
    }

    public MutableLiveData<EventType> getEvent()
    {
        return repository.event;
    }
}
