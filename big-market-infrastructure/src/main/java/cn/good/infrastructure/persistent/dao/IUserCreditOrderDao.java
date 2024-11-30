package cn.good.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import cn.good.infrastructure.persistent.po.UserCreditOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/30
 **/
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserCreditOrderDao {
    void insert(UserCreditOrder userCreditOrderReq);
}
