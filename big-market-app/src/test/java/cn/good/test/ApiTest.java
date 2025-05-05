package cn.good.test;

import cn.good.domain.activity.model.entity.UserAwardRecordEntity;
import cn.good.domain.activity.service.IRaffleActivityAccountQuotaService;
import cn.good.infrastructure.persistent.dao.IUserAwardRecordDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {
    @Autowired
    private IUserAwardRecordDao userAwardRecordDao;
    @Autowired
    private IRaffleActivityAccountQuotaService activityAccountQuotaService;
    @Test
    public void test() {
        List<UserAwardRecordEntity> userAwardRecordEntities = activityAccountQuotaService.queryUserRecordEntity("wkm");
        log.info(userAwardRecordEntities.toString());
    }


}
