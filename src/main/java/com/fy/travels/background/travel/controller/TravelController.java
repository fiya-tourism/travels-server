package com.fy.travels.background.travel.controller;

import com.fy.travels.background.travel.domain.Travel;
import com.fy.travels.background.travel.service.TravelService;
import com.fy.travels.commons.*;
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

    @RequestMapping("selTr")
    @ResponseBody
    @CrossOrigin(allowCredentials="true", allowedHeaders="*",origins="*")
    public DataGridVo<Travel> selTr(Page page) {
        return travelService.selTr(page);
    }


    @RequestMapping("upTravelById")
    @ResponseBody
    public String upTravelById(Integer travelsId) {

        return travelService.upTravelById(travelsId);
    }

    /**
     * 修改yn隐藏当前数据
     * */
    @RequestMapping(value = "delTravel",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据状态yn修改  隐藏数据")
    public ResultMsg delTravel(@RequestParam Travel travel){
        return travelService.delTravel(travel);
    }


    @RequestMapping("saveDemo")
    @ResponseBody
    public void saveDemo(Travel travel){
        travelService.saveDemo(travel);
    }


}
