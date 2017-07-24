/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: TestJob.java 
 * @Prject: ars
 * @Package: com.ars.common.quartz 
 * @Description: 
 * @author: zw
 * @date: 2017年4月5日 下午8:30:33 
 * @version: V1.0   
 */
package com.ars.common.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/** 
 * @ClassName: TestJob 
 * @Description: 创建一个job方法，创建一个任务。
 * @author: zw
 * @date: 2017年4月5日 下午8:30:33  
 */
public class TestJob   {
	  public void doSomething() {
	        System.err.println("****:" + System.currentTimeMillis());
	    }
}
