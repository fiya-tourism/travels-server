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


    int updateByPrimaryKeySelective(Travel record);

    int updateByPrimaryKey(Travel record);

    Travel selectByPrimaryKey(@Param("travelsId") Integer travelsId);




    List<Travel> queryTravelList(Page page);

    void delTravel(@Param("travelsYn") Integer travelsYn,@Param("travelsId") Integer travelsId,@Param("travelsUpdate") Date travelsUpdate);
}