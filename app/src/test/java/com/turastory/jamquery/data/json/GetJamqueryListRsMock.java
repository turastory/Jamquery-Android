package com.turastory.jamquery.data.json;

import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tura on 2018-04-12.
 * <p>
 * Mock data for test..
 */
public final class GetJamqueryListRsMock implements RsMock<GetJamqueryListRs> {
    
    @Override
    public String getJson() {
        return "[\n" +
            "  {\n" +
            "    \"day\": 10,\n" +
            "    \"month\": 7,\n" +
            "    \"year\": 2018,\n" +
            "    \"url\": \"http://test0/\",\n" +
            "    \"jamName\": \"test0\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"day\": 10,\n" +
            "    \"month\": 7,\n" +
            "    \"year\": 2018,\n" +
            "    \"url\": \"http://test1/\",\n" +
            "    \"jamName\": \"test1\"\n" +
            "  }\n" +
            "]";
    }
    
    @Override
    public GetJamqueryListRs get() {
        return new GetJamqueryListRs("test0", "http://test0/", "2018", "7", "10");
    }
    
    @Override
    public List<GetJamqueryListRs> getList() {
        List<GetJamqueryListRs> list = new ArrayList<>();
        
        list.add(new GetJamqueryListRs("test0", "http://test0/", "2018", "7", "10"));
        list.add(new GetJamqueryListRs("test1", "http://test1/", "2018", "7", "10"));
        
        return list;
    }
}
