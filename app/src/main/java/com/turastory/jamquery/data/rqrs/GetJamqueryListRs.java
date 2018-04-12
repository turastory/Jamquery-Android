package com.turastory.jamquery.data.rqrs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Jamquery List Response
 */
public class GetJamqueryListRs implements Response {
    
    @SerializedName("jamName")
    private String name;
    
    @SerializedName("jamUrl")
    private String url;
    
    @SerializedName("year")
    private String year;
    
    @SerializedName("month")
    private String month;
    
    @SerializedName("day")
    private String day;
    
    public GetJamqueryListRs() {
    
    }
    
    public GetJamqueryListRs(String name, String url, String year, String month, String day) {
        this.name = name;
        this.url = url;
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getYear() {
        return year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }
    
    public String getMonth() {
        return month;
    }
    
    public void setMonth(String month) {
        this.month = month;
    }
    
    public String getDay() {
        return day;
    }
    
    public void setDay(String day) {
        this.day = day;
    }
}
