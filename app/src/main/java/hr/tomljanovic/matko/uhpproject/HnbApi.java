package hr.tomljanovic.matko.uhpproject;

import java.util.List;

import hr.tomljanovic.matko.uhpproject.model.CurrencyModel;
import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface HnbApi {
    @GET("daily")
    Flowable<List<CurrencyModel>> getData();
}
