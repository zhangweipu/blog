package com.wp.weipu.test;

/**
 * @author zwp
 */

public enum EnumTest {
    TEXT("AA", "AA");

    private String name;
    private String code;

    EnumTest(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * java中参数的
     * @param code
     * @return
     */
    public static EnumTest getName(String... code) {
        for(EnumTest em:values()){
            if(code.equals(em.getCode())){
               return em;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
}
