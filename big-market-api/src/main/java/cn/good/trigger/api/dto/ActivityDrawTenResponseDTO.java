package cn.good.trigger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author wkm
 * @Date 2025/5/4
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDrawTenResponseDTO {
    private List<String> awardTitleList;
}
