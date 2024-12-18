package cn.good.trigger.api;

import cn.good.trigger.api.response.Response;

/**
 * TODO
 *
 * @Description 动态配置中心
 * @Author wkm
 * @Date 2024/12/15
 **/
public interface IDCCService {
    Response<Boolean> updateConfig(String key, String value);
}
