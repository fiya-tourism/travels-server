package com.fy.travels.background.travel.service.impl;

import com.fy.travels.background.travel.domain.Travel;
import com.fy.travels.background.travel.mapper.TravelMapper;
import com.fy.travels.background.travel.service.TravelService;
import com.fy.travels.commons.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TravelerviceImpl implements TravelService {
    @Autowired
    private TravelMapper travelMapper;
    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String MDB_NAME= "Travels";
    private static final String WH_NAME= "游记";




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
    public List<Travel> queryTravel() {
        //做分页查询 list接收
        List<Travel> newsList = travelMapper.queryTravel();
        //实例化一个 分页工具对象
        //返回控制层
        return newsList;
    }



    @Override
    public ResultMsg delTravel(Travel travel) {
        travel.setTravelsUpdate(new Date());
        travel.setTravelsYn(1);
        ResultMsg rs = new ResultMsg();
        try{
            travelMapper.delTravel(travel.getTravelsYn(),travel.getTravelsId(),travel.getTravelsUpdate());
            rs.setMsg("审核成功");
            rs.setCode(200);
        }catch(Exception e){
            rs.setMsg("审核失败  请重试");
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

        return travel;
    }





    @Override
    public Integer insertTravels(Travel travels) {
        travels.setTravelsCreate(new Date());
        travels.setTravelsYn(0);
        travelMapper.insert(travels);
        Integer insert = travels.getTravelsId();

        /*//多图片上传
        travels.setTravelsId(insert);
        String[] split = travels.getTravelsPicture().split(",");
        for (int i=1;i<=split.length;i++){
            travels.setTravelsPicture(split[i]);
            travelMapper.insert(travels);
        }*/

        if (insert!=null){
            travels.setTravelsContentId(insert+WH_NAME);
            travels.setTravelsText(travels.getTravelsText());
            Integer insertcontent = insertcontent(travels);
            if (insertcontent==1){
                return 1;
            }
        }
        return 2;
    }
    public Integer insertcontent(Travel travels){
        Travel insert = this.mongoTemplate.insert(travels);
        if (insert !=null){
            return 1;
        }
        return 2;
    }
}
