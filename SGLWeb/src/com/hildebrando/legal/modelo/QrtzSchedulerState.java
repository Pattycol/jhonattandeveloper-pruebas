package com.hildebrando.legal.modelo;
// Generated 13-jun-2012 10:20:15 by Hibernate Tools 3.4.0.CR1



/**
 * QrtzSchedulerState generated by hbm2java
 */
public class QrtzSchedulerState  implements java.io.Serializable {


     private String instanceName;
     private long lastCheckinTime;
     private long checkinInterval;

    public QrtzSchedulerState() {
    }

    public QrtzSchedulerState(String instanceName, long lastCheckinTime, long checkinInterval) {
       this.instanceName = instanceName;
       this.lastCheckinTime = lastCheckinTime;
       this.checkinInterval = checkinInterval;
    }
   
    public String getInstanceName() {
        return this.instanceName;
    }
    
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
    public long getLastCheckinTime() {
        return this.lastCheckinTime;
    }
    
    public void setLastCheckinTime(long lastCheckinTime) {
        this.lastCheckinTime = lastCheckinTime;
    }
    public long getCheckinInterval() {
        return this.checkinInterval;
    }
    
    public void setCheckinInterval(long checkinInterval) {
        this.checkinInterval = checkinInterval;
    }




}

