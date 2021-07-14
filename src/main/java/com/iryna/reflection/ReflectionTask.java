package com.iryna.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTask {

    public Object createObjectByClass(Class c) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Constructor currentConstructor: c.getDeclaredConstructors()) {
            if (currentConstructor.getParameterCount() == 0) {
                return currentConstructor.newInstance();
            }
        }
        return null;
    }

    public void callAllMethodsWithoutParametersForObject(Object object) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        for (Method method: object.getClass().getDeclaredMethods()) {
            if (method.getParameterCount() == 0) {
                method.setAccessible(true);
                method.invoke(object.getClass().newInstance());
            }
        }
    }

    public void printFinalMethodsSignatureForObject(Object object) {
        for (Method method: object.getClass().getDeclaredMethods()) {
            if (method.getModifiers() == 18) {
                System.out.println(method.getName());
            }
        }
    }

    public void printNotPublicMethodsForClass(Class c) {
        for (Method method: c.getDeclaredMethods()) {
            if (method.getModifiers() != 1) {
                System.out.println(method.getName() + " " + method.getModifiers());
            }
        }
    }

    public void printSuperClassesAndInterfacesForClass(Class c) {
        System.out.println(c.getSuperclass() + " superclass for " + c.getName() + " class");
        for (Class currentInterface: c.getInterfaces()) {
            System.out.println(c.getName() + " implements " + currentInterface.getName());
        }
    }

    public void changePrivateFieldsAtDefaultValuesForObject(Object object) throws IllegalAccessException {
        for (Field field: object.getClass().getDeclaredFields()) {
            if (field.getModifiers() == 2) {
                field.setAccessible(true);
                String fieldType =  field.getType().getName();

                if ((fieldType.equals("int")) || (fieldType.equals("byte")) || (fieldType.equals("long"))) {
                    field.set(object, 0);
                }
                else if (fieldType.equals("double")) {
                    field.setDouble(object, 0.0d);
                }
                else if (fieldType.equals("float")) {
                    field.setFloat(object, 0.0f);
                }
                else {
                    field.set(object, null);
                }
            }
        }
    }
}
