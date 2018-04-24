package com.turastory.jamquery.data.datasource.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.turastory.jamquery.presentation.vo.JamqueryVO;

/**
 * Created by tura on 2018-04-24.
 * <p>
 * 로컬 데이터베이스 정의
 */
@Database(entities = {JamqueryVO.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class JamqueryLocalDatabase extends RoomDatabase {
    
    private static final Object lock = new Object();
    
    private static JamqueryLocalDatabase instance;
    
    public static JamqueryLocalDatabase getInstance(Context context) {
        synchronized (lock) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                    JamqueryLocalDatabase.class, "Jamquery.db")
                    .build();
            }
            
            return instance;
        }
    }
    
    public abstract JamqueryDao jamqueryDao();
}
