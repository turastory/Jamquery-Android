package com.turastory.jamquery.data.datasource.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.annimon.stream.function.Function;
import com.turastory.jamquery.presentation.vo.Jamquery;

/**
 * Created by tura on 2018-04-24.
 * <p>
 * 로컬 데이터베이스 정의
 */
@Database(entities = {Jamquery.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class JamqueryLocalDatabase extends RoomDatabase {
    
    private static final Object lock = new Object();
    
    private static final Function<Context, JamqueryLocalDatabase> defaultProvider = context ->
        Room.databaseBuilder(context.getApplicationContext(),
            JamqueryLocalDatabase.class, "Jamquery.db")
            .build();
    
    private static JamqueryLocalDatabase instance;
    private static Function<Context, JamqueryLocalDatabase> databaseProvider = defaultProvider;
    
    public static JamqueryLocalDatabase getInstance(Context context) {
        synchronized (lock) {
            if (instance == null) {
                instance = databaseProvider.apply(context);
            }
            
            return instance;
        }
    }
    
    public static void setDatabaseProvider(Function<Context, JamqueryLocalDatabase> databaseProvider) {
        JamqueryLocalDatabase.databaseProvider = databaseProvider;
    }
    
    public static void reset() {
        JamqueryLocalDatabase.databaseProvider = defaultProvider;
        instance = null;
    }
    
    public abstract JamqueryDao jamqueryDao();
}
