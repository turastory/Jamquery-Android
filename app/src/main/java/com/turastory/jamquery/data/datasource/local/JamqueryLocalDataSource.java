package com.turastory.jamquery.data.datasource.local;

import com.annimon.stream.Stream;
import com.turastory.jamquery.data.datasource.JamqueryDataSource;
import com.turastory.jamquery.data.exception.JamqueryNotFoundException;
import com.turastory.jamquery.presentation.vo.JamqueryVO;

import java.util.List;

/**
 * Created by tura on 2018-04-24.
 * <p>
 * 로컬 저장소를 나타냄.
 */
public class JamqueryLocalDataSource implements JamqueryDataSource {
    
    private JamqueryDao jamqueryDao;
    
    public JamqueryLocalDataSource(JamqueryDao jamqueryDao) {
        this.jamqueryDao = jamqueryDao;
    }
    
    @Override
    public void getJamqueryList(String keyword, DataSourceCallback callback) {
        List<JamqueryVO> jamqueries = jamqueryDao.getJamquries();
    
        if (jamqueries != null) {
            String key = keyword.toLowerCase();
            callback.onLoad(Stream.of(jamqueries)
                .filter(jamquery -> jamquery.getTitle().toLowerCase().contains(key))
                .toList());
        }
        else
            callback.onError(new JamqueryNotFoundException());
    }
}
