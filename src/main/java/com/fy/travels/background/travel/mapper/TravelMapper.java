package com.fy.travels.background.travel.mapper;

import com.fy.travels.background.travel.domain.Travel;
import com.fy.travels.commons.Page;
import com.fy.travels.commons.ResultVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface TravelMapper {
    int deleteByPrimaryKey(Integer travelsId);

    ResultVo insert(Travel travel);

    int insertSelective(Travel record);

    Travel selectByPrimaryKey(Integer travelsId);

    int updateByPrimaryKeySelective(Travel record);

    int updateByPrimaryKey(Travel record);

    String upTravelById(Integer travelsId);



    void delTravel(@Param("travelsYn") Integer travelsYn,@Param("travelsUpdate") Date travelsUpdate, @Param("travelsId") Integer id);

    List<Travel> queryTravelList(Page page);
}