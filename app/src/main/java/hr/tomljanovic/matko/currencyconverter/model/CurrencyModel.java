package hr.tomljanovic.matko.currencyconverter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyModel {

    @SerializedName("unit_value")
    @Expose
    private Integer unitValue;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("median_rate")
    @Expose
    private String medianRate;
    @SerializedName("buying_rate")
    @Expose
    private String buyingRate;
    @SerializedName("selling_rate")
    @Expose
    private String sellingRate;

    public Integer getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Integer unitValue) {
        this.unitValue = unitValue;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getMedianRate() {
        return medianRate;
    }

    public void setMedianRate(String medianRate) {
        this.medianRate = medianRate;
    }

    public String getBuyingRate() {
        return buyingRate;
    }

    public void setBuyingRate(String buyingRate) {
        this.buyingRate = buyingRate;
    }

    public String getSellingRate() {
        return sellingRate;
    }

    public void setSellingRate(String sellingRate) {
        this.sellingRate = sellingRate;
    }

    @Override
    public String toString() {
        return "CurrencyModel{" +
                "unitValue=" + unitValue +
                ", currencyCode='" + currencyCode + '\'' +
                ", medianRate='" + medianRate + '\'' +
                ", buyingRate='" + buyingRate + '\'' +
                ", sellingRate='" + sellingRate + '\'' +
                '}';
    }
}