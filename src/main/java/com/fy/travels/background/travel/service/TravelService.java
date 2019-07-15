package com.fy.travels.background.travel.service;

import com.fy.travels.background.travel.domain.Travel;
import com.fy.travels.commons.DataGridVo;
import com.fy.travels.commons.Page;
import com.fy.travels.commons.ResultMsg;
import com.fy.travels.commons.ResultVo;

import java.util.Date;

public interface TravelService {
    DataGridVo selTravel(Page page);

    ResultVo insert(Travel travel);

    String upTravelById(Integer travelsId);

    ResultMsg delTravel(Travel travel);

}
