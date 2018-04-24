package com.turastory.jamquery.data.datasource.local;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.turastory.jamquery.data.datasource.JamqueryDataSource;
import com.turastory.jamquery.presentation.vo.Jamquery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by tura on 2018-04-24.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class JamqueryLocalDataSourceTest {
    
    private JamqueryLocalDataSource dataSource;
    
    private JamqueryLocalDatabase database;
    
    @Before
    public void setUp() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            JamqueryLocalDatabase.class)
            .build();
        
        dataSource = new JamqueryLocalDataSource(database.jamqueryDao());
    }
    
    @After
    public void tearDown() {
        database.close();
    }
    
    @Test
    public void getJamqueryList() throws InterruptedException {
        readyDummyData();
        
        CountDownLatch latch = new CountDownLatch(1);
        
        testGetJamqueryListUsingKeyword(latch, "Tes");
        
        latch.await();
    }
    
    @Test
    public void getJamqueryList_caseInsensitive() throws InterruptedException {
        readyDummyData();
        
        CountDownLatch latch = new CountDownLatch(3);
        
        testGetJamqueryListUsingKeyword(latch, "Tes");
        testGetJamqueryListUsingKeyword(latch, "teS");
        testGetJamqueryListUsingKeyword(latch, "EsT");
        
        latch.await();
    }
    
    private void testGetJamqueryListUsingKeyword(CountDownLatch latch, String keyword) {
        dataSource.getJamqueryList(keyword, new JamqueryDataSource.DataSourceCallback() {
            @Override
            public void onLoad(List<Jamquery> jamqueries) {
                assertThat(jamqueries.size(), is(1));
                assertThat(jamqueries.get(0).getTitle(), is("Test"));
                latch.countDown();
            }
            
            @Override
            public void onError(Exception e) {
                fail();
                latch.countDown();
            }
        });
    }
    
    // 일종의 꼼수..
    private void readyDummyData() {
        database.jamqueryDao().insertNewJamquery(new Jamquery(new Date(), "Test", "https://www.google.com/"));
        database.jamqueryDao().insertNewJamquery(new Jamquery(new Date(), "te", "https://www.google.com/"));
    }
}