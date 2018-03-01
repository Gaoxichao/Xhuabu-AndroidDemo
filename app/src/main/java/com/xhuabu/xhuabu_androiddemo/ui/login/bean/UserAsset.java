package com.xhuabu.xhuabu_androiddemo.ui.login.bean;

/**
 * Created by UPC on 2018/3/1.
 */

public class UserAsset {

    /**
     * totalStock : 0
     * totalCash : 1000000
     */

    private int totalStock;
    private int totalCash;

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public int getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(int totalCash) {
        this.totalCash = totalCash;
    }

    @Override
    public String toString() {
        return "UserAsset{" +
                "totalStock=" + totalStock +
                ", totalCash=" + totalCash +
                '}';
    }
}
