package com.dahee8kim.monitoring.domain.openstack;

public class Resource {
    private int totalMemory;
    private int totalDisk;
    private int totalVCPU;
    private int usedMemory;
    private int usedDisk;
    private int usedVCPU;

    public int getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(int totalMemory) {
        this.totalMemory = totalMemory;
    }

    public int getTotalDisk() {
        return totalDisk;
    }

    public void setTotalDisk(int totalDisk) {
        this.totalDisk = totalDisk;
    }

    public int getTotalVCPU() {
        return totalVCPU;
    }

    public void setTotalVCPU(int totalVCPU) {
        this.totalVCPU = totalVCPU;
    }

    public int getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(int usedMemory) {
        this.usedMemory = usedMemory;
    }

    public int getUsedDisk() {
        return usedDisk;
    }

    public void setUsedDisk(int usedDisk) {
        this.usedDisk = usedDisk;
    }

    public int getUsedVCPU() {
        return usedVCPU;
    }

    public void setUsedVCPU(int usedVCPU) {
        this.usedVCPU = usedVCPU;
    }
}
