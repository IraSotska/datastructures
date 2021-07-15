package com.iryna.reflection;

import java.lang.reflect.*;

public class ReflectionTask {

    public Object createObjectByClass(Class clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Constructor currentConstructor: clazz.getDeclaredConstructors()) {
            if (currentConstructor.getParameterCount() == 0) {
                return currentConstructor.newInstance();
            }
        }
        return null;
    }

    public void callAllMethodsWithoutParametersForObject(Object object) throws InvocationTargetException, IllegalAccessException {
        for (Method method: object.getClass().getDeclaredMethods()) {
            if (method.getParameterCount() == 0) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    public void printFinalMethodsSignatureForObject(Object object) {
        for (Method method: object.getClass().getDeclaredMethods()) {
            if (Modifier.FINAL == method.getModifiers()) {
                System.out.println(method.getName());
            }
        }
    }

    public void printNotPublicMethodsForClass(Class c) {
        for (Method method: c.getDeclaredMethods()) {
            if (Modifier.PUBLIC != method.getModifiers()) {
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
                Class fieldType =  field.getType();

                if ((fieldType == int.class) || (fieldType == byte.class) || (fieldType == long.class)) {
                    field.set(object, 0);
                }
                else if ((fieldType == double.class) || fieldType == float.class) {
                    field.set(object, 0.0);
                }
                else {
                    field.set(object, null);
                }
            }
        }
    }
}
