package com.iryna.reflection;

import com.iryna.datastructures.list.LinkedList;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;

class ReflectionTaskTest {

    private ReflectionTask reflectionTask = new ReflectionTask();
    private TestClass testClass = new TestClass();

    @Test
    void createObjectByClass() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        assertEquals(TestClass.class, reflectionTask.createObjectByClass(TestClass.class).getClass());;
    }

    @Test
    void callAllMethodsWithoutParametersForObject() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        reflectionTask.callAllMethodsWithoutParametersForObject(new LinkedList());
    }

    @Test
    void printFinalMethodsSignatureForObject() {
        reflectionTask.printFinalMethodsSignatureForObject(testClass);
    }

    @Test
    void printNotPublicMethodsForClass() {
        reflectionTask.printNotPublicMethodsForClass(TestClass.class);
    }

    @Test
    void printSuperClassesAndInterfacesForClass() {
        reflectionTask.printSuperClassesAndInterfacesForClass(TestClass.class);
    }

    @Test
    void changePrivateFieldsAtDefaultValuesForObject() throws IllegalAccessException {
        testClass.setField1(1.2F);
        testClass.setField2("Str");
        reflectionTask.changePrivateFieldsAtDefaultValuesForObject(testClass);
        assertEquals(0.0D, testClass.getField1());
        assertNull(testClass.getField2());
    }
}