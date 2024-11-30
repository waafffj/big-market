package cn.good.infrastructure.persistent.dao;

import cn.good.infrastructure.persistent.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAwardDao {
    List<Award> queryAwardList();
    String queryAwardConfigByAwardId(Integer awardId);
    String queryAwardKeyByAwardId(Integer awardId);
}
