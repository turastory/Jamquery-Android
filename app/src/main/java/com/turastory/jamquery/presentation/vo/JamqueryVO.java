package com.turastory.jamquery.presentation.vo;

import java.util.Date;

/**
 * Created by tura on 2018-04-11.
 *
 * Jamquery VO (Presentation 레이어의 모델)
 */
public class JamqueryVO {
    
    private Date date;
    private String title;
    private String url;
    
    public JamqueryVO(Date date, String title, String url) {
        this.date = date;
        this.title = title;
        this.url = url;
    }
    
    public Date Date() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String Title() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String Url() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
}
