package cn.good.infrastructure.persistent.dao;

import cn.good.infrastructure.persistent.po.UserCreditAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @Description 用户积分账户
 * @Author wkm
 * @Date 2024/11/29
 **/
@Mapper
public interface IUserCreditAccountDao {
    void insert(UserCreditAccount userCreditAccountReq);
    int updateAddAmount(UserCreditAccount userCreditAccountReq);
    UserCreditAccount queryUserCreditAccount(UserCreditAccount userCreditAccountReq);
    int updateSubtractionAmount(UserCreditAccount userCreditAccountReq);
}
