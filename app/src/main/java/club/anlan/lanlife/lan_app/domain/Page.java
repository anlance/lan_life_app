package club.anlan.lanlife.lan_app.domain;

import java.util.List;

/**
 * page 对象
 *
 * @author lan
 * @version 1.0
 * @date 2021/4/17 20:24
 */
public class Page<T> {

    /**
     * 总数
     */
    private String total;

    /**
     * 每页条数
     */
    private String size;

    /**
     * 当前页数
     */
    private String current;

    /**
     * 总共页数
     */
    private String pages;

    /**
     * list 数据
     */
    private List<T> records;

    public Page() {
    }

    public Page(String total, String size, String current, String pages, List<T> records) {
        this.total = total;
        this.size = size;
        this.current = current;
        this.pages = pages;
        this.records = records;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
