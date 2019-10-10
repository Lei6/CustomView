package com.demo.customview.logisticstraces;

/**
 * created by 姚明亮
 * Time：2019/9/17 09:29
 */
public class TracesBean {

    private String AcceptStation;
    private String AcceptTime;

    public TracesBean(String acceptStation, String acceptTime) {
        AcceptStation = acceptStation;
        AcceptTime = acceptTime;
    }

    public String getAcceptStation() {
        return AcceptStation;
    }

    public void setAcceptStation(String acceptStation) {
        AcceptStation = acceptStation;
    }

    public String getAcceptTime() {
        return AcceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        AcceptTime = acceptTime;
    }
}
