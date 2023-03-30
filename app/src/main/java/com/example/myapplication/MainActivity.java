package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ArrayAdapter<CharSequence> unitTypeAdapter = ArrayAdapter.createFromResource(this, R.array.unit_types, android.R.layout.simple_spinner_item);
        unitTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnUnitType.setAdapter(unitTypeAdapter);
        binding.spnUnitType.setOnItemSelectedListener(this);
    }

    private void setUnitSpinnerItems(int unitArrayId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, unitArrayId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnFromUnit.setAdapter(adapter);
        binding.spnToUnit.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (parent == binding.spnUnitType) {
            switch(pos) {
                case 0:
                    setUnitSpinnerItems(R.array.length_units); break;
                case 1:
                    setUnitSpinnerItems(R.array.weight_units); break;
                case 2:
                    setUnitSpinnerItems(R.array.temp_units); break;
            }
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        parent.setSelection(0);
    }
}