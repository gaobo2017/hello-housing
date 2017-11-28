package com.ejavashop.vo.seller;

public class StoreBannerJson {
    private String path1;
    private String path2;
    private String path3;
    private String path4;

    public String getPath1() {
        return path1;
    }

    public void setPath1(String path1) {
        this.path1 = path1;
    }

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public String getPath3() {
        return path3;
    }

    public void setPath3(String path3) {
        this.path3 = path3;
    }

    public String getPath4() {
        return path4;
    }

    public void setPath4(String path4) {
        this.path4 = path4;
    }

    public boolean isNull() {
        return (this.path1 == null || "".equals(this.path1))
               && (this.path2 == null || "".equals(this.path2))
               && (this.path3 == null || "".equals(this.path3))
               && (this.path4 == null || "".equals(this.path4));
    }
}
