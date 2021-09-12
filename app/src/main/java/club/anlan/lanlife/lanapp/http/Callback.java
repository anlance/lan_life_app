package club.anlan.lanlife.lanapp.http;

import club.anlan.lanlife.lanapp.domain.ResultMessage;

/**
 * 请求网络回调
 *
 * @author lan
 * @version 1.0
 * @date 2021/4/17 15:26
 */
public interface Callback {

    void onSuccess(ResultMessage res);

    void onFailure(Exception e);
}

