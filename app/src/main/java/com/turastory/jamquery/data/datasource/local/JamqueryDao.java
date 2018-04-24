package com.turastory.jamquery.data.datasource.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.turastory.jamquery.presentation.vo.JamqueryVO;

import java.util.List;

/**
 * Created by tura on 2018-04-24.
 * <p>
 * Jamquery를 위한 DAO
 */
@Dao
public interface JamqueryDao {
    
    @Query("SELECT * FROM jamquery ORDER BY create_date DESC")
    List<JamqueryVO> getJamquries();
}
