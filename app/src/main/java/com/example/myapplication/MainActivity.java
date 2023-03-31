package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivityMainBinding binding;
    private String unitType;
    private int fromUnitPos;
    private int toUnitPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ArrayAdapter<CharSequence> unitTypeAdapter = ArrayAdapter.createFromResource(this, R.array.unit_types, R.layout.spinner_item);
        unitTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnUnitType.setAdapter(unitTypeAdapter);
        binding.spnUnitType.setOnItemSelectedListener(this);

        binding.btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double input = Double.parseDouble(binding.edtInput.getText().toString());
                double result;
                if (unitType.equals("Temperature")) result = UnitConverter.convertTemp(fromUnitPos, toUnitPos, input);
                else result = UnitConverter.convert(unitType, fromUnitPos, toUnitPos, input);
                binding.txtResult.setText(String.format("%.2f", result));
            }
        });
    }

    private void setUnitSpinnerItems(int unitArrayId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, unitArrayId, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnFromUnit.setAdapter(adapter);
        binding.spnFromUnit.setOnItemSelectedListener(this);
        binding.spnToUnit.setAdapter(adapter);
        binding.spnToUnit.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (parent == binding.spnUnitType) {
            unitType = parent.getItemAtPosition(pos).toString();
            switch(pos) {
                case 0:
                    setUnitSpinnerItems(R.array.length_units);
                    break;
                case 1:
                    setUnitSpinnerItems(R.array.weight_units);
                    break;
                case 2:
                    setUnitSpinnerItems(R.array.temp_units);
                    break;
            }
        }
        if (parent == binding.spnFromUnit) fromUnitPos = pos;
        if (parent == binding.spnToUnit) toUnitPos = pos;
    }

    public void onNothingSelected(AdapterView<?> parent) {
        parent.setSelection(0);
    }
}