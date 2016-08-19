package com.example.baiyuanwei.nicecopy.interfaces;

/**
 * Created by baiyuanwei on 16/8/19.
 * 通知context是否在滚动
 */

public interface IScrollable {

    //通知context是否在滚动
    void setIsScroll(boolean isScroll);

    //得到界面是否在滚动
    boolean isScroll();

}
