package com.iryna.reflection;

public class TestClass implements Cloneable{

    private float field1;
    private String field2;

    public float getField1() {
        return field1;
    }

    public void setField1(float field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public final void method1(String s) {
        System.out.println("method1");
    }
    protected void method2() {
        System.out.println("method2");
    }
    private final void method3(int i) {
        System.out.println("method3");
    }
    private void method4() {
        System.out.println("method4");
    }
    private void method5() {
        System.out.println("method5");
    }

}
