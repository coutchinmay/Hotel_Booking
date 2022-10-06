package com.example.hotel_booking;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.sql.Array;
import java.util.ArrayList;

public class HomeFragmnet extends Fragment implements AdapterView.OnItemSelectedListener {

    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;
    ListView listView;
    ListAdapter listAdapter;
    Spinner spinner;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState){
        View v =inflater.inflate(R.layout.fragment_home, null);
        spinner= (Spinner) v.findViewById(R.id.cityspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.cities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

//        textInputLayout = (TextInputLayout) v.findViewById(R.id.menu_dropdown);
//        autoCompleteTextView = (AutoCompleteTextView) v.findViewById(R.id.drop_items);
//        String [] items={"Pune", "Mumbai", "Nashik"};
//
//        listAdapter = new ArrayAdapter<String>(getActivity() , R.layout.items_list, items );
//        autoCompleteTextView.setAdapter( listAdapter );


        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
