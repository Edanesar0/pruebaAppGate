package com.prueba.appgate.models;

import com.google.gson.annotations.SerializedName;

public class TimezoneJsonModel {

    @SerializedName("sunrise")
    private String sunrise;

    @SerializedName("lng")
    private double lng;

    @SerializedName("countryCode")
    private String countryCode;

    @SerializedName("gmtOffset")
    private int gmtOffset;

    @SerializedName("rawOffset")
    private int rawOffset;

    @SerializedName("sunset")
    private String sunset;

    @SerializedName("timezoneId")
    private String timezoneId;

    @SerializedName("dstOffset")
    private int dstOffset;

    @SerializedName("countryName")
    private String countryName;

    @SerializedName("time")
    private String time;

    @SerializedName("lat")
    private double lat;

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setGmtOffset(int gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public int getGmtOffset() {
        return gmtOffset;
    }

    public void setRawOffset(int rawOffset) {
        this.rawOffset = rawOffset;
    }

    public int getRawOffset() {
        return rawOffset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunset() {
        return sunset;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setDstOffset(int dstOffset) {
        this.dstOffset = dstOffset;
    }

    public int getDstOffset() {
        return dstOffset;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }
}