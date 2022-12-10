package com.om.practicaltest.repository;


import androidx.lifecycle.MutableLiveData;

import com.om.practicaltest.helperutils.EventType;
import com.om.practicaltest.model.DummyData;
import com.om.practicaltest.model.Employee;
import com.om.practicaltest.retrofit.RetrofitClient;
import com.om.practicaltest.room.EmployeeDao;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private final RetrofitClient client;
    private final EmployeeDao dao;

    public MutableLiveData<EventType> event = new MutableLiveData<EventType>(EventType.EMPTY);

    @Inject
    public MainRepository(RetrofitClient client, EmployeeDao dao) {
        this.client = client;
        this.dao = dao;
    }

    public void getAllEmployee()
    {
        client.getAPI().getEmployeeList().enqueue(new Callback<DummyData>() {
            @Override
            public void onResponse(Call<DummyData> call, Response<DummyData> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    dao.insertAll(response.body().getEmployee());
                    event.postValue(EventType.DATABASE_UPDATED);
                }
            }

            @Override
            public void onFailure(Call<DummyData> call, Throwable t) {
                event.postValue(EventType.FAIL);
            }
        });
    }

    public List<Employee> getAllEmployeeOffline()
    {
        return dao.getAll();
    }
}


