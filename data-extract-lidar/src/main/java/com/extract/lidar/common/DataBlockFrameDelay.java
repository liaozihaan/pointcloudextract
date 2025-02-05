package com.extract.lidar.common;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DataBlockFrameDelay implements Delayed {

    private final long RESERVE_MILLISECONDS = 3000;

    private Integer framdId;

    private long expireMilliseconds;

    public DataBlockFrameDelay(Integer framdId) {
        this.framdId = framdId;
        this.expireMilliseconds = RESERVE_MILLISECONDS + System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.expireMilliseconds - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed target) {
        long targetExpireMilliseconds = ((DataBlockFrameDelay) target).getExpireMilliseconds();
        if (this.expireMilliseconds < targetExpireMilliseconds) {
            return -1;
        } else if (this.expireMilliseconds > targetExpireMilliseconds) {
            return 1;
        }
        return 0;
    }

    public Integer getFramdId() {
        return framdId;
    }

    public void setFramdId(Integer framdId) {
        this.framdId = framdId;
    }

    public long getExpireMilliseconds() {
        return expireMilliseconds;
    }

    public void setExpireMilliseconds(long expireMilliseconds) {
        this.expireMilliseconds = expireMilliseconds;
    }

    @Override
    public String toString() {
        return "DataBlockFrameDelay{" +
                "framdId=" + framdId +
                ", expireMilliseconds=" + expireMilliseconds +
                '}';
    }
}
