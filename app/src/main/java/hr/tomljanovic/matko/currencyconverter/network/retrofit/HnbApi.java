package hr.tomljanovic.matko.currencyconverter.network.retrofit;

import java.util.List;

import hr.tomljanovic.matko.currencyconverter.model.CurrencyModel;
import hr.tomljanovic.matko.currencyconverter.utils.Const;
import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface HnbApi {
    @GET(Const.Network.DAILY_URL)
    Flowable<List<CurrencyModel>> getData();
}
