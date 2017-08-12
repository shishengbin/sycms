package com.sy.model.biz.vo;


/**
 * 数据各种状态枚举
 * 包含已删除和未删除状态
 *
 * @author William.Chen
 */
public enum RecordState {
    deleted("已删除", 1), normal("正常数据", 0);

    private String name;
    private int index;

    private RecordState(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 获取删除状态信息
    public static String getName(int index) {
        for (RecordState c : RecordState.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Byte getByteIndex() {
        return Byte.valueOf((byte) index);
    }
    
    public static void main(String[] args){
    	System.out.println(RecordState.normal.getByteIndex());
    }
    
}
