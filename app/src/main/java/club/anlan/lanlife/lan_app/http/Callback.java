package club.anlan.lanlife.lan_app.http;

import club.anlan.lanlife.lan_app.domain.ResultMessage;

/**
 * 类
 *
 * @author lan
 * @version 1.0
 * @date 2021/4/17 15:26
 */
public interface Callback {

    void onSuccess(ResultMessage res);

    void onFailure(Exception e);
}

