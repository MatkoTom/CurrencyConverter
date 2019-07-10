package hr.tomljanovic.matko.uhpproject.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;

import java.util.List;

import hr.tomljanovic.matko.uhpproject.adapter.RetrofitAdapter;
import hr.tomljanovic.matko.uhpproject.model.CurrencyModel;
import io.reactivex.schedulers.Schedulers;

public class CurrencyRepository {
    private static CurrencyRepository instance;

    public static synchronized CurrencyRepository getInstance() {
        if (instance == null) {
            instance = new CurrencyRepository();
        }
        return instance;
    }

    private CurrencyRepository() {
    }

    public LiveData<List<CurrencyModel>> makeQuery() {
        return LiveDataReactiveStreams.fromPublisher(RetrofitAdapter.getRequestApi()
                .getData()
                .subscribeOn(Schedulers.io()));
    }
}
