package hr.tomljanovic.matko.uhpproject;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.tomljanovic.matko.uhpproject.model.CurrencyModel;
import hr.tomljanovic.matko.uhpproject.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.fromSpinner)
    Spinner fromSpinner;

    @BindView(R.id.toSpinner)
    Spinner toSpinner;

    @BindView(R.id.tvResult)
    TextView tvResult;

    @BindView(R.id.etInputFrom)
    EditText etInputFrom;

    public static final String TAG = "WhatHappened?";
    private MainViewModel viewModel;

    private List<String> sellRate = new ArrayList<>();
    private List<String> buyRate = new ArrayList<>();
    private List<String> currencyName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        etInputFrom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        etInputFrom.setHintTextColor(Color.WHITE);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        logItems();
    }

    private void logItems() {
        viewModel.makeQuery().observe(this, new Observer<List<CurrencyModel>>() {
            @Override
            public void onChanged(@Nullable List<CurrencyModel> currencyModels) {
                if (currencyModels != null) {
                    for (CurrencyModel model : currencyModels) {
                        currencyName.add(model.getCurrencyCode());
                        sellRate.add(model.getSellingRate());
                        buyRate.add(model.getBuyingRate());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                        R.layout.spinner_item, currencyName);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                fromSpinner.setAdapter(adapter);
                toSpinner.setAdapter(adapter);
            }
        });
    }

    @OnClick(R.id.btnConvert)
    public void convertValue() {
        if (!(etInputFrom.length() == 0)) {
            String etOne = etInputFrom.getText().toString();

            int id = fromSpinner.getSelectedItemPosition();
            String value = sellRate.get(id);

            int id2 = toSpinner.getSelectedItemPosition();
            String value2 = buyRate.get(id2);

            if ((id == 4 || id == 5) && (id2 == 4 || id2 == 5)) {
                double result = (Double.parseDouble(etOne) * Double.parseDouble(value)) / Double.parseDouble(value2);
                tvResult.setText(String.format("%s %s", formatResult(result), toSpinner.getSelectedItem()));
            } else if (id2 == 4 || id2 == 5) {
                double result = (Double.parseDouble(etOne) * Double.parseDouble(value)) / Double.parseDouble(value2) * 100;
                tvResult.setText(String.format("%s %s", formatResult(result), toSpinner.getSelectedItem()));
            } else if (id == 4 || id == 5) {
                double result = (Double.parseDouble(etOne) * Double.parseDouble(value)) / Double.parseDouble(value2) / 100;
                tvResult.setText(String.format("%s %s", formatResult(result), toSpinner.getSelectedItem()));
            } else {
                double result = (Double.parseDouble(etOne) * Double.parseDouble(value)) / Double.parseDouble(value2);
                tvResult.setText(String.format("%s %s", formatResult(result), toSpinner.getSelectedItem()));
            }

        } else {
            Toast.makeText(this, "Please enter an amount to convert!", Toast.LENGTH_SHORT).show();
        }
    }

    private String formatResult(double result) {
        DecimalFormat format = new DecimalFormat("#.####");
        return format.format(result);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("myString", tvResult.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String result = savedInstanceState.getString("myString");
        tvResult.setText(result);
    }
}
