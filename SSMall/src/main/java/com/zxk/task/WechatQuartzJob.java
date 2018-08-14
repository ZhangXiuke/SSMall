package com.zxk.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class WechatQuartzJob extends QuartzJobBean{ 

	private WechatTask wechatTask;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		wechatTask.wechatAccessToken();
	}

	public void setWechatTask(WechatTask wechatTask){
		this.wechatTask = wechatTask;
	}
}
