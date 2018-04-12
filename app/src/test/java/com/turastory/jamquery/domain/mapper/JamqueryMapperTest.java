package com.turastory.jamquery.domain.mapper;

import com.annimon.stream.Stream;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;
import com.turastory.jamquery.presentation.util.JamqueryDateFormatter;
import com.turastory.jamquery.presentation.vo.JamqueryVO;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by tura on 2018-04-12.
 */
public class JamqueryMapperTest {
    
    private GetJamqueryListRs rs;
    private JamqueryMapper mapper;
    
    @Before
    public void setup() {
        rs = new GetJamqueryListRs();
        rs.setName("test");
        rs.setUrl("https://something/");
        rs.setYear("2014");
        rs.setMonth("07");
        rs.setDay("31");
        
        mapper = new JamqueryMapper();
    }
    
    @Test
    public void test_convertEmptyList() {
        List<GetJamqueryListRs> rsList = new ArrayList<>();
        
        assertThat(mapper.convert(rsList).size(), is(rsList.size()));
    }
    
    @Test
    public void test_convertListSingleItem() {
        List<GetJamqueryListRs> rsList = new ArrayList<>();
        rsList.add(rs);
        
        List<JamqueryVO> voList = mapper.convert(rsList);
        
        assertThat(voList.size(), is(rsList.size()));
        assertEqual(voList.get(0), rs);
    }
    
    @Test
    public void test_convertListMutipleItem() {
        List<GetJamqueryListRs> rsList = new ArrayList<>();
        rsList.add(rs);
        rsList.add(new GetJamqueryListRs("multiple", "http://sample/", "2018", "04", "11"));
        
        List<JamqueryVO> voList = mapper.convert(rsList);
        
        assertThat(voList.size(), is(rsList.size()));
        Stream.of(voList).forEachIndexed((index, vo) ->
            assertEqual(vo, rsList.get(index)));
    }
    
    private void assertEqual(JamqueryVO vo, GetJamqueryListRs rs) {
        assertThat(vo.getTitle(), is(rs.getName()));
        assertThat(vo.getUrl(), is(rs.getUrl()));
        assertThat(JamqueryDateFormatter.format(vo.getDate()),
            is(rs.getYear() + rs.getMonth() + rs.getDay()));
    }
}