package club.anlan.lanlife.app.model.domain

/**
 *
 * 请求结果
 *
 * @author lan
 * @date 2022/11/13 14:49
 * @version 1.0
 */
data class ResultMessage<T>(
    /**
     * 成功与否
     */
    val success: Boolean = false,

    /**
     * 结果编码
     */
    val code: String? = null,

    /**
     * 返回数据
     */
    val errMsg: String? = null,

    /**
     * 返回结果数据
     */
    val data: T? = null,
)