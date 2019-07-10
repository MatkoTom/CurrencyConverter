package hr.tomljanovic.matko.uhpproject.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import hr.tomljanovic.matko.uhpproject.model.CurrencyModel;
import hr.tomljanovic.matko.uhpproject.repository.CurrencyRepository;

public class MainViewModel extends ViewModel {
    private CurrencyRepository repository;

    public MainViewModel() {
        repository = CurrencyRepository.getInstance();
    }

    public LiveData<List<CurrencyModel>> makeQuery() {
        return repository.makeQuery();
    }
}
