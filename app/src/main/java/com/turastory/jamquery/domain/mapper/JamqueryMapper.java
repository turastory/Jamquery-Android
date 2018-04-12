package com.turastory.jamquery.domain.mapper;

import com.annimon.stream.Stream;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;
import com.turastory.jamquery.presentation.util.JamqueryDateFormatter;
import com.turastory.jamquery.presentation.vo.JamqueryVO;

import java.util.Date;
import java.util.List;

/**
 * Created by tura on 2018-04-12.
 * <p>
 * Mapping JamqueryVO - GetJamqueryListRs
 */
public class JamqueryMapper {
    
    public JamqueryMapper() {
    
    }
    
    public List<JamqueryVO> convert(List<GetJamqueryListRs> jamqueryRsList) {
        return Stream.of(jamqueryRsList)
            .map(this::convert)
            .toList();
    }
    
    private JamqueryVO convert(GetJamqueryListRs rs) {
        Date date = JamqueryDateFormatter.parse(constructFormattedDate(rs));
        return new JamqueryVO(date, rs.getName(), rs.getUrl());
    }
    
    private String constructFormattedDate(GetJamqueryListRs rs) {
        return rs.getYear() + rs.getMonth() + rs.getDay();
    }
}
