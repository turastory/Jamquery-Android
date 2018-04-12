package com.turastory.jamquery.data.rqrs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Jamquery List Request
 */
public class GetJamqueryListRq implements Request {
    
    @SerializedName("keyWord")
    private String keyword;
    
    public GetJamqueryListRq(String keyword) {
        this.keyword = keyword;
    }
    
    public String getKeyword() {
        return keyword;
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
