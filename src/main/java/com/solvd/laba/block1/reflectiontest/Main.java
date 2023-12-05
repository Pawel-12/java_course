package com.solvd.laba.block1.reflectiontest;

import com.solvd.laba.block1.task2.persons.Driver;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try {
            Class<Driver> c =
                    (Class<Driver>) Class.forName("com.solvd.laba.block1.task2.persons.Driver");
            try {
                Driver kevin = c
                        .getDeclaredConstructor(String.class, float.class, ArrayList.class)
                        .newInstance("Kevin", 200, new ArrayList<>(List.of("Boat")));

                System.out.println("\n" + c.getMethod("toString").invoke(kevin));
            } catch (NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

            System.out.format("Class:%n  %s%n%n", c.getCanonicalName());
            System.out.format("Modifiers:%n  %s%n%n",
                    Modifier.toString(c.getModifiers()));

            System.out.format("Type Parameters:%n");
            TypeVariable[] tv = c.getTypeParameters();
            if (tv.length != 0) {
                System.out.format("  ");
                for (TypeVariable t : tv)
                    System.out.format("%s ", t.getName());
                System.out.format("%n%n");
            } else
                System.out.format("  -- No Type Parameters --%n%n");

            System.out.format("Implemented Interfaces:%n");
            Type[] intfs = c.getGenericInterfaces();
            if (intfs.length != 0) {
                for (Type intf : intfs)
                    System.out.format("  %s%n", intf.toString());
                System.out.format("%n");
            } else
                System.out.format("  -- No Implemented Interfaces --%n%n");

            System.out.format("Inheritance Path:%n");
            List<Class> l = new ArrayList<>();
            printAncestor(c, l);
            if (!l.isEmpty()) {
                for (Class<?> cl : l)
                    System.out.format("  %s%n", cl.getCanonicalName());
                System.out.format("%n");
            } else
                System.out.format("  -- No Super Classes --%n%n");

            System.out.format("Annotations:%n");
            Annotation[] ann = c.getAnnotations();
            if (ann.length != 0) {
                for (Annotation a : ann)
                    System.out.format("  %s%n", a.toString());
                System.out.format("%n");
            } else
                System.out.format("  -- No Annotations --%n%n");

            printMembers(c.getDeclaredConstructors(), "Constuctors");

            printMembers(Stream.concat(Arrays.stream(c.getFields()), Arrays.stream(c.getDeclaredFields()))
                    .toArray(Field[]::new), "Fields");

            printMembers(Stream.concat(Arrays.stream(c.getMethods()), Arrays.stream(c.getDeclaredMethods()))
                    .toArray(Method[]::new), "Methods");

            printClasses(c);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printAncestor(Class<?> c, List<Class> l) {
        Class<?> ancestor = c.getSuperclass();
        if (ancestor != null) {
            l.add(ancestor);
            printAncestor(ancestor, l);
        }
    }

    private static void printClasses(Class<?> c) {
        System.out.format("Classes:%n");
        Class<?>[] clss = c.getClasses();
        for (Class<?> cls : clss)
            System.out.format("  %s%n", cls.getCanonicalName());
        if (clss.length == 0)
            System.out.format("  -- No member interfaces, classes, or enums --%n");
        System.out.format("%n");
    }

    private static void printMembers(Member[] mbrs, String s) {
        System.out.format("%s:%n", s);
        for (Member mbr : mbrs)
            if (mbr instanceof Field)
                System.out.format("  %s%n", ((Field) mbr).toGenericString());
            else if (mbr instanceof Constructor)
                System.out.format("  %s%n", ((Constructor) mbr).toGenericString());
            else if (mbr instanceof Method)
                System.out.format("  %s%n", ((Method) mbr).toGenericString());

        if (mbrs.length == 0)
            System.out.format("  -- No %s --%n", s);
        System.out.format("%n");
    }
}
