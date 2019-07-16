package com.fy.travels.background.travel.service.impl;

import com.fy.travels.background.travel.domain.Travel;
import com.fy.travels.background.travel.mapper.TravelMapper;
import com.fy.travels.background.travel.service.TravelService;
import com.fy.travels.commons.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TravelerviceImpl implements TravelService {
    @Autowired
    private TravelMapper travelMapper;
    @Autowired
    private MongoTemplate mongoTemplate;



    @Override
    public DataGridVo selTr(Page page) {
        if (page == null){
            page= new Page();
        }
        PageHelper.startPage(page.getPage(),page.getRows());
        page.setSort(StringUtil.underscoreName(page.getSort()));
        List<Travel> pmList = travelMapper.queryTravelList(page);
        PageInfo<Travel> pageIn = new PageInfo(pmList);
        return new DataGridVo(pageIn.getTotal(),pageIn.getList());
    }


    @Override
    public ResultMsg delTravel(Travel travel) {
        travel.setTravelsUpdate(new Date());
        travel.setTravelsYn(1);
        ResultMsg rs = new ResultMsg();
        try{
            travelMapper.delTravel(travel.getTravelsYn(),travel.getTravelsId(),travel.getTravelsUpdate());
            rs.setMsg("删除成功");
            rs.setCode(200);
        }catch(Exception e){
            rs.setMsg("删除失败  请重试");
            rs.setCode(500);
            rs.setSuccessFalse();
        }
        return rs;
    }

    @Override
    public ResultVo insert(Travel travel) {
        if (travel != null && travel.getTravelsId() ==null){
            travelMapper.insert(travel);

            return ResultVo.success("成功");
        }
        travelMapper.updateByPrimaryKeySelective(travel);
        return ResultVo.success("修改成功");
    }







    @Override
    public void saveDemo(Travel travel) {
        mongoTemplate.save(travel);
    }






    @Override
    public Travel upTravelById(Integer travelsId) {
        Travel travel = travelMapper.selectByPrimaryKey(travelsId);
        System.out.println(travel);
        return travel;
    }
}
