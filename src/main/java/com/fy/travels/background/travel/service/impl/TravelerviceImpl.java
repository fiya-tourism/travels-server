package com.fy.travels.background.travel.service.impl;

import com.fy.travels.background.travel.domain.Travel;
import com.fy.travels.background.travel.mapper.TravelMapper;
import com.fy.travels.background.travel.service.TravelService;
import com.fy.travels.commons.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
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
    private SolrTemplate solrTemplate;
    @Override
    public DataGridVo selTravel(Page page) {
        if (page == null){
            page= new Page();
        }
        PageHelper.startPage(page.getPage(),page.getRows());
        page.setSort(StringUtil.underscoreName(page.getSort()));
        List<Travel> travelList = travelMapper.queryList(page);
        if (travelList != null){
            Query query = new SimpleQuery("*:*");
            query.setOffset((long) 0);
            query.setRows(4);
            Criteria hoFormx = new Criteria("travelsYn").is(0);
            query.addCriteria(hoFormx);
            org.springframework.data.domain.Page<Travel> users = solrTemplate.query("century_core", query, Travel.class);
            List<Travel> content = users.getContent();
            if(content.size()!=0){
                return (DataGridVo) content;
            }
        }else{
            for (int i = 0; i <travelList.size() ; i++) {
                solrTemplate.saveBean("century_core",travelList.get(i));
                solrTemplate.commit("century_core");
            }
        }
        PageInfo<Travel> pageIn = new PageInfo(travelList);
        return new DataGridVo(pageIn.getTotal(),pageIn.getList());
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




}
