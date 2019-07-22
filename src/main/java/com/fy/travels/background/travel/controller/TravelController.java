package com.fy.travels.background.travel.controller;

import com.fy.travels.background.travel.domain.Travel;
import com.fy.travels.background.travel.service.TravelService;
import com.fy.travels.commons.*;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("travels")
public class TravelController {


    @Autowired
    private TravelService travelService;

    @RequestMapping("insertTravel")
    @ResponseBody
    public ResultVo insertTravel(Travel travel) {
        travel.setTravelsCreate(new Date());
        travel.setTravelsYn(0);
        return travelService.insert(travel);
    }

    @RequestMapping(value = "selTr" ,method = RequestMethod.POST)
    @ResponseBody
    public DataGridVo<Travel> selTr(@RequestBody Page page) {
        return travelService.selTr(page);
    }


    @RequestMapping("upTravelById")
    @ResponseBody
    public Travel upTravelById(@RequestParam("travelsId") Integer travelsId) {

        return travelService.upTravelById(travelsId);
    }

    @RequestMapping("delTravel")
    @ApiOperation(value = "根据状态yn修改  隐藏数据 接口")
    @ResponseBody
    public ResultMsg delTravel(@RequestBody Travel travel){
        return travelService.delTravel(travel);
    }

    @RequestMapping("saveDemo")
    @ResponseBody
    public void saveDemo(Travel travel){
        travelService.saveDemo(travel);
    }



    @RequestMapping("queryTravel")
    @ResponseBody
    public List<Travel> queryTravel(){
        List<Travel> travelsLet= travelService.queryTravel();
        return travelsLet;
    }

}
