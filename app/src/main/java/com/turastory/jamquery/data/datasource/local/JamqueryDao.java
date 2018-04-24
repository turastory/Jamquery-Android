package com.turastory.jamquery.data.datasource.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
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
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewJamquery(JamqueryVO jamqueryVO);
    
    @Query("SELECT * FROM jamquery ORDER BY create_date DESC")
    List<JamqueryVO> getJamquries();
}
