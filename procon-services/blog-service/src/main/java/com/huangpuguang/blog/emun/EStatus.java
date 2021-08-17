package com.huangpuguang.blog.emun;

/**
 * <p>状态枚举类</p>
 * @author procon
 * Created on 2020/10/29.
 *
 */
public enum EStatus {
    /**
     *枚举值
     */
    DISABLED(0,"删除"),
    ENABLE(1,"激活"),
    FREEZE(2,"冻结"),
    STICK(3,"置顶");
    private Integer state;
    private String description;

    EStatus(int state, String description) {
    }

    public Integer getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }
}
