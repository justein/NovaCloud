package com.nova.sbnetty.utils;

import com.sohu.idcenter.IdWorker;

public class IdGenerator {


    public static long getNextId() {

        final long idepo = System.currentTimeMillis() - 3600 * 1000L;
        IdWorker iw = new IdWorker(1, 1, 0, idepo);
       return iw.getId();
    }
}
