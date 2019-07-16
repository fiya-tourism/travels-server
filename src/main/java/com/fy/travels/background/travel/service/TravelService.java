package com.fy.travels.background.travel.service;

import com.fy.travels.background.travel.domain.Travel;
import com.fy.travels.commons.DataGridVo;
import com.fy.travels.commons.Page;
import com.fy.travels.commons.ResultMsg;
import com.fy.travels.commons.ResultVo;

import java.util.Date;
import java.util.List;

public interface TravelService {

     ResultMsg delTravel(Travel travel);


    ResultVo insert(Travel travel);

    Travel upTravelById(Integer travelsId);


    void saveDemo(Travel travel);

    DataGridVo<Travel> selTr(Page page);
}
