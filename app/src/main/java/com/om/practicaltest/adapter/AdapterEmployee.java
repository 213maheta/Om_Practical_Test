package com.om.practicaltest.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.om.practicaltest.databinding.ItemEmployeeBinding;
import com.om.practicaltest.model.Employee;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class AdapterEmployee extends RecyclerView.Adapter<AdapterEmployee.ViewHolder> {

    public List<Employee> employeeList;
    public boolean editable = false;
    public boolean disable = false;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEmployeeBinding binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = employeeList.get(holder.getAdapterPosition());
        holder.itemRowBinding.setEmployee(employee);
        holder.itemRowBinding.etSalary.setTag(position);
    }

    @Override
    public int getItemCount() {
        return (employeeList != null) ? employeeList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemEmployeeBinding itemRowBinding;

        public ViewHolder(ItemEmployeeBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;

            if(disable)
            {
                this.itemRowBinding.etSalary.setFocusable(false);
                return;
            }

            this.itemRowBinding.etSalary.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void afterTextChanged(Editable edit) {
                    if (editable) {
                        if (edit.toString().equals("")) {
                            employeeList.get((Integer) itemRowBinding.etSalary.getTag()).setEmployeeSalary(0.0);
                        } else {
                            employeeList.get((Integer) itemRowBinding.etSalary.getTag()).setEmployeeSalary(Double.valueOf(edit.toString()));
                        }
                    }
                }
            });
        }
    }
}
