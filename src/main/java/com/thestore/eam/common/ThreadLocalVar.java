/**
 * Copyright (C), 2011-2016 The Store
 * File Name: BaseDao.java
 * Encoding: UTF-8
 * Date: Sep 7, 2011
 * History: 
 */
package com.thestore.eam.common;

/**
 * 
 * @author Wayne Wan (bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 7, 2011
 */
public class ThreadLocalVar
{
    private static ThreadLocal<ThreadLocalObject> threadVar = new ThreadLocal<ThreadLocalObject>();

    public static ThreadLocalObject get() 
    {
        return threadVar.get();
    }
    
    public static void set(ThreadLocalObject obj)
    {
        threadVar.set(obj);
    }
}
