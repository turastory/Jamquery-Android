package com.turastory.jamquery.data.datasource.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.turastory.jamquery.presentation.vo.Jamquery;

import java.util.List;

/**
 * Created by tura on 2018-04-24.
 * <p>
 * Jamquery를 위한 DAO
 */
@Dao
public interface JamqueryDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNew(Jamquery jamquery);
    
    @Query("SELECT * FROM Jamquery ORDER BY create_date DESC")
    List<Jamquery> getList();
}
