package cn.good.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2024/11/6
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDrawResponseDTO implements Serializable {
    private Integer awardId;
    private String awardTitle;
    /* 排序编号  */
    private Integer awardIndex;
}
