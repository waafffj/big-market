package cn.good.domain.award.model.aggregate;

import cn.good.domain.award.model.entity.TaskEntity;
import cn.good.domain.award.model.entity.UserAwardRecordEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/4
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAwardRecordAggregate {
      private UserAwardRecordEntity userAwardRecordEntity ;
      private TaskEntity taskEntity;
}
