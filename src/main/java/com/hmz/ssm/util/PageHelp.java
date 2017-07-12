package com.hmz.ssm.util;

import java.util.List;

/**
 * @Author Hu mingzhi
 * Created by ThinKPad on 2017/7/11.
 */
public class PageHelp {
    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
