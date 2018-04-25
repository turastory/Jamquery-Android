package com.turastory.jamquery.presentation.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

/**
 * Created by tura on 2018-04-11.
 *
 * Jamquery VO (Presentation 레이어의 모델)
 */
@Entity(tableName = "jamquery")
public class Jamquery {
    
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "jamquery_id")
    private String id;
    @NonNull
    @ColumnInfo(name = "create_date")
    private Date date;
    @NonNull
    @ColumnInfo(name = "title")
    private String title;
    @NonNull
    @ColumnInfo(name = "url")
    private String url;
    
    @Ignore
    public Jamquery(@NonNull Date date,
                    @NonNull String title, @NonNull String url) {
        this(UUID.randomUUID().toString(), date, title, url);
    }
    
    public Jamquery(@NonNull String id, @NonNull Date date,
                    @NonNull String title, @NonNull String url) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.url = url;
    }
    
    @NonNull
    public String getId() {
        return id;
    }
    
    public void setId(@NonNull String id) {
        this.id = id;
    }
    
    @NonNull
    public Date getDate() {
        return date;
    }
    
    public void setDate(@NonNull Date date) {
        this.date = date;
    }
    
    @NonNull
    public String getTitle() {
        return title;
    }
    
    public void setTitle(@NonNull String title) {
        this.title = title;
    }
    
    @NonNull
    public String getUrl() {
        return url;
    }
    
    public void setUrl(@NonNull String url) {
        this.url = url;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
    
        Jamquery that = (Jamquery) o;
    
        return id.equals(that.id) &&
            date.equals(that.date) &&
            title.equals(that.title) &&
            url.equals(that.url);
    }
    
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
