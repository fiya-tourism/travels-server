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
        String s = StrTool.humpToLine2(page.getSort());
        page.setSort(s);
        PageHelper.startPage(page.getPage(),page.getRows());
        List<Travel> list =travelMapper.queryTravelList(page);
        PageInfo pageInfo = new PageInfo<>(list);
        DataGridVo<Travel> dg = new DataGridVo<>();
        dg.setRows(list);
        dg.setTotal(pageInfo.getTotal());
        return dg;
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
    public String upTravelById(Integer travelsId) {
        return null;
    }

    @Override
    public ResultMsg delTravel(Travel travel) {
        travel.setTravelsYn(1);
        travel.setTravelsUpdate(new Date());
        ResultMsg rs = new ResultMsg();
        if (travel.getTravelsId() != null) {
            travelMapper.delTravel(travel.getTravelsYn(), travel.getTravelsUpdate(), travel.getTravelsId());
        }
        return rs;
    }

    @Override
    public void saveDemo(Travel travel) {
        mongoTemplate.save(travel);
    }


}
