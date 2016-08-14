package com.example.baiyuanwei.nicecopy.thread;

import java.util.concurrent.ThreadFactory;

/**
 * Created by baiyuanwei on 16/8/14.
 */
public class NiceCopyThreadFactory implements ThreadFactory {


    private String prefix = "NiceCopy_Pool";
    private int counter = 0;

    public NiceCopyThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, prefix + "-" + counter++);
    }
}
