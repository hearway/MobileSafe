package com.example.mobilesafe.model;

import android.graphics.drawable.Drawable;

public class AppInfo implements Comparable<AppInfo>{

    private String packname;    
    private String appname;     
    private Drawable icon;      
    private long tx;            
    private long rx;            
    private int uid;            
    private String flag;      
    
    public AppInfo(){}
   
    public AppInfo(String m_packname, long m_tx, long m_rx, String m_flag) {
    	packname = m_packname;
    	tx = m_tx;
    	rx = m_rx;
        flag = m_flag;
    }
    
    public AppInfo(AppInfo appInfo) {
		packname = appInfo.getPackname();
		appname = appInfo.getAppname();
		icon = appInfo.getIcon();
		tx = appInfo.getTx();
		rx = appInfo.getRx();
		uid = appInfo.getUid();
        flag = appInfo.getFlag();
	}

	public String getPackname() {  
        return packname;  
    }
    
    public void setPackname(String packname) {  
        this.packname = packname;  
    }
    
    public String getAppname() {  
        return appname;  
    }
    
    public void setAppname(String appname) {  
        this.appname = appname;  
    }
    
    public Long getTx() {
        return tx;  
    } 
    
    public void setTx(Long tx) {  
        this.tx = tx;  
    }
    
    public Long getRx() {
        return rx;  
    }
    
    public void setRx(Long rx) {  
        this.rx = rx;  
    } 
    
    public Drawable getIcon() {  
        return icon;  
    } 
    
    public void setIcon(Drawable icon) {  
        this.icon = icon;  
    }
    
    public int getUid() {  
        return uid;  
    }
    
    public void setUid(int uid) {  
        this.uid = uid;  
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

	@Override
	public int compareTo(AppInfo another) {
		return this.getRx().compareTo(another.getRx());
	}

}
