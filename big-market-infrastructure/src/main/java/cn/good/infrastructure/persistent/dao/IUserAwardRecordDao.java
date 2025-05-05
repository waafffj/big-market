package cn.good.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import cn.good.infrastructure.persistent.po.UserAwardRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/1
 **/
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserAwardRecordDao {
    void insert(UserAwardRecord userAwardRecord);
    int updateAwardRecordCompletedState(UserAwardRecord userAwardRecordReq);
    @DBRouter
    List<UserAwardRecord> queryUserAwardRecordByUserId(String userId);
}
