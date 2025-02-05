package com.extract.lidar.common;

import java.util.Arrays;

public class BaseMessage {

    private byte[] checkSum;
    private Integer frameVersion;
    private Integer frameId;
    private Integer packageCounter;

    private Integer packageSeqNum;

    private Integer phaseLockNum;
    private Integer dataBlockNum;
    private Long timestamps;
    private Long timestampus;
    private Integer echoMode;
    private Integer luIndex;
    private Integer packageFrameFlag;

    private Integer azimuthIndexA;

    private Integer azimuthIndexB;

    private Integer highTemperatureDegradationFlag;
    private Integer dataInvalidFlag;

    private Integer runtimeFrameRateFlag;

    private Integer reservedBit;

    private Integer reservedA;

    private byte[] reservedB;

    private String dataBlockFlag;
    private Double azimuth;

    private byte[] dataBlockBytes;

    private byte[] tail;

    public BaseMessage() {
    }

    public BaseMessage(byte[] checkSum, Integer frameVersion, Integer frameId, Integer packageCounter, Integer packageSeqNum, Integer phaseLockNum, Integer dataBlockNum, Long timestamps, Long timestampus, Integer echoMode, Integer luIndex, Integer packageFrameFlag, Integer azimuthIndexA, Integer azimuthIndexB, Integer highTemperatureDegradationFlag, Integer dataInvalidFlag, Integer runtimeFrameRateFlag, Integer reservedBit, Integer reservedA, byte[] reservedB, String dataBlockFlag, Double azimuth, byte[] dataBlockBytes, byte[] tail) {
        this.checkSum = checkSum;
        this.frameVersion = frameVersion;
        this.frameId = frameId;
        this.packageCounter = packageCounter;
        this.packageSeqNum = packageSeqNum;
        this.phaseLockNum = phaseLockNum;
        this.dataBlockNum = dataBlockNum;
        this.timestamps = timestamps;
        this.timestampus = timestampus;
        this.echoMode = echoMode;
        this.luIndex = luIndex;
        this.packageFrameFlag = packageFrameFlag;
        this.azimuthIndexA = azimuthIndexA;
        this.azimuthIndexB = azimuthIndexB;
        this.highTemperatureDegradationFlag = highTemperatureDegradationFlag;
        this.dataInvalidFlag = dataInvalidFlag;
        this.runtimeFrameRateFlag = runtimeFrameRateFlag;
        this.reservedBit = reservedBit;
        this.reservedA = reservedA;
        this.reservedB = reservedB;
        this.dataBlockFlag = dataBlockFlag;
        this.azimuth = azimuth;
        this.dataBlockBytes = dataBlockBytes;
        this.tail = tail;
    }

    public String getDataBlockFlag() {
        return dataBlockFlag;
    }

    public void setDataBlockFlag(String dataBlockFlag) {
        this.dataBlockFlag = dataBlockFlag;
    }

    public Double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(Double azimuth) {
        this.azimuth = azimuth;
    }

    public byte[] getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(byte[] checkSum) {
        this.checkSum = checkSum;
    }

    public Integer getFrameVersion() {
        return frameVersion;
    }

    public void setFrameVersion(Integer frameVersion) {
        this.frameVersion = frameVersion;
    }

    public Integer getFrameId() {
        return frameId;
    }

    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    public Integer getPackageCounter() {
        return packageCounter;
    }

    public void setPackageCounter(Integer packageCounter) {
        this.packageCounter = packageCounter;
    }

    public Integer getPackageSeqNum() {
        return packageSeqNum;
    }

    public void setPackageSeqNum(Integer packageSeqNum) {
        this.packageSeqNum = packageSeqNum;
    }

    public Integer getPhaseLockNum() {
        return phaseLockNum;
    }

    public void setPhaseLockNum(Integer phaseLockNum) {
        phaseLockNum = phaseLockNum;
    }

    public Integer getDataBlockNum() {
        return dataBlockNum;
    }

    public void setDataBlockNum(Integer dataBlockNum) {
        this.dataBlockNum = dataBlockNum;
    }

    public Long getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Long timestamps) {
        this.timestamps = timestamps;
    }

    public Long getTimestampus() {
        return timestampus;
    }

    public void setTimestampus(Long timestampus) {
        this.timestampus = timestampus;
    }

    public Integer getEchoMode() {
        return echoMode;
    }

    public void setEchoMode(Integer echoMode) {
        this.echoMode = echoMode;
    }

    public Integer getLuIndex() {
        return luIndex;
    }

    public void setLuIndex(Integer luIndex) {
        this.luIndex = luIndex;
    }

    public Integer getPackageFrameFlag() {
        return packageFrameFlag;
    }

    public void setPackageFrameFlag(Integer packageFrameFlag) {
        this.packageFrameFlag = packageFrameFlag;
    }

    public Integer getAzimuthIndexA() {
        return azimuthIndexA;
    }

    public void setAzimuthIndexA(Integer azimuthIndexA) {
        this.azimuthIndexA = azimuthIndexA;
    }

    public Integer getAzimuthIndexB() {
        return azimuthIndexB;
    }

    public void setAzimuthIndexB(Integer azimuthIndexB) {
        this.azimuthIndexB = azimuthIndexB;
    }

    public Integer getHighTemperatureDegradationFlag() {
        return highTemperatureDegradationFlag;
    }

    public void setHighTemperatureDegradationFlag(Integer highTemperatureDegradationFlag) {
        this.highTemperatureDegradationFlag = highTemperatureDegradationFlag;
    }

    public Integer getDataInvalidFlag() {
        return dataInvalidFlag;
    }

    public void setDataInvalidFlag(Integer dataInvalidFlag) {
        this.dataInvalidFlag = dataInvalidFlag;
    }

    public Integer getRuntimeFrameRateFlag() {
        return runtimeFrameRateFlag;
    }

    public void setRuntimeFrameRateFlag(Integer runtimeFrameRateFlag) {
        this.runtimeFrameRateFlag = runtimeFrameRateFlag;
    }

    public Integer getReservedBit() {
        return reservedBit;
    }

    public void setReservedBit(Integer reservedBit) {
        this.reservedBit = reservedBit;
    }

    public Integer getReservedA() {
        return reservedA;
    }

    public void setReservedA(Integer reservedA) {
        this.reservedA = reservedA;
    }

    public byte[] getReservedB() {
        return reservedB;
    }

    public void setReservedB(byte[] reservedB) {
        this.reservedB = reservedB;
    }

    public byte[] getDataBlockBytes() {
        return dataBlockBytes;
    }

    public void setDataBlockBytes(byte[] dataBlockBytes) {
        this.dataBlockBytes = dataBlockBytes;
    }

    public byte[] getTail() {
        return tail;
    }

    public void setTail(byte[] tail) {
        this.tail = tail;
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "checkSum=" + Arrays.toString(checkSum) +
                ",\n frameVersion=" + frameVersion +
                ", \nframeId=" + frameId +
                ", \npackageCounter=" + packageCounter +
                ", \npackageSeqNum=" + packageSeqNum +
                ", \nPhaseLockNum=" + phaseLockNum +
                ", \ndataBlockNum=" + dataBlockNum +
                ", \ntimestamps=" + timestamps +
                ", \ntimestampus=" + timestampus +
                ", \nechoMode=" + echoMode +
                ", \nluIndex=" + luIndex +
                ", \npackageFrameFlag=" + packageFrameFlag +
                ", \nazimuthIndexA=" + azimuthIndexA +
                ", \nazimuthIndexB=" + azimuthIndexB +
                ", \nhighTemperatureDegradationFlag=" + highTemperatureDegradationFlag +
                ", \ndataInvalidFlag=" + dataInvalidFlag +
                ", \nruntimeFrameRateFlag=" + runtimeFrameRateFlag +
                ", \nreservedBit=" + reservedBit +
                ", \nreservedA=" + reservedA +
                ", \nreservedB=" + Arrays.toString(reservedB) +
                ", \ndataBlockBytes=" + Arrays.toString(dataBlockBytes) +
                ", \ntail=" + Arrays.toString(tail) +
                '}';
    }
}