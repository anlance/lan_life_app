package club.anlan.lanlife.lanapp.domain;

/**
 * 定位结果
 *
 * @author lan
 * @version 1.0
 * @date 2021/9/12 19:34
 */

public class LocationRes {

    /**
     * 用户 id
     */
    private String userId;

    /**
     * 精度
     */
    private double longitude;

    /**
     * 纬度
     */
    private double latitude;

    /**
     * 精度
     */
    private float accuracy;

    /**
     * 提供者
     */
    private String provider;

    /**
     * 星数
     */
    private int satellites;

    /**
     * 错误信息
     */
    private String errorInfo;

    /**
     * 错误详情
     */
    private String errorDetail;

    public LocationRes() {
    }

    public LocationRes(String userId, double longitude, double latitude, float accuracy, String provider, int satellites) {
        this.userId = userId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.accuracy = accuracy;
        this.provider = provider;
        this.satellites = satellites;
    }

    public LocationRes(double longitude, double latitude, float accuracy, String provider, int satellites) {
        this("userId", longitude, latitude, accuracy, provider, satellites);
        this.userId = "faker";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getSatellites() {
        return satellites;
    }

    public void setSatellites(int satellites) {
        this.satellites = satellites;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
