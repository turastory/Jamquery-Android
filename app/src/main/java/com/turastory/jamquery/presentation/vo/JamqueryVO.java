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
    
    public JamqueryVO() {
    
    }
    
    public JamqueryVO(Date date, String title, String url) {
        this.date = date;
        this.title = title;
        this.url = url;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
}
