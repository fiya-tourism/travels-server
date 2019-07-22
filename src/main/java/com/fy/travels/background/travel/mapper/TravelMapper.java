package com.fy.travels.background.travel.mapper;

import com.fy.travels.background.travel.domain.Travel;
import com.fy.travels.commons.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface TravelMapper {
    int deleteByPrimaryKey(Integer travelsId);

    int insert(Travel record);


    Travel selectByPrimaryKey(Integer travelsId);

    int updateByPrimaryKeySelective(Travel record);


    List<Travel> queryTravelList(Page page);

    List<Travel> queryTravel();

    void delTravel(@Param("travelsYn") Integer travelsYn,@Param("travelsId") Integer travelsId,@Param("travelsUpdate") Date travelsUpdate);
}