package com.sun.data.hotline.domain;

/**
 * 上下半年的枚举
 *
 * @author sunjian.
 */
public enum HalfYear
{
    first_half("上半年"),
    second_half("下半年");

    private String value;

    HalfYear(final String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }

    public void setValue(final String value)
    {
        this.value = value;
    }
}
