package com.turastory.jamquery.domain.mapper;

import com.annimon.stream.Stream;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;
import com.turastory.jamquery.presentation.util.JamqueryDateFormatter;
import com.turastory.jamquery.presentation.vo.Jamquery;

import java.util.Date;
import java.util.List;

/**
 * Created by tura on 2018-04-12.
 * <p>
 * Mapping Jamquery - GetJamqueryListRs
 */
public class JamqueryMapper {
    
    public JamqueryMapper() {
    
    }
    
    public List<Jamquery> convert(List<GetJamqueryListRs> jamqueryRsList) {
        return Stream.of(jamqueryRsList)
            .map(this::convert)
            .toList();
    }
    
    private Jamquery convert(GetJamqueryListRs rs) {
        Date date = JamqueryDateFormatter.parse(constructFormattedDate(rs));
        return new Jamquery(date, rs.getName(), rs.getUrl());
    }
    
    private String constructFormattedDate(GetJamqueryListRs rs) {
        return rs.getYear() + rs.getMonth() + rs.getDay();
    }
}
