package org.devgen.dbconnection;

// interview question (how to create class which can be only created once and use multiple times )

public class SingleObject {

    static SingleObject instance = null;

    private SingleObject() {
        System.out.println(" This is constructor ");
    }

    static SingleObject getInstance() {
        if (instance == null)
            instance = new SingleObject();

        return instance;
    }
}


class CallerClass {
    public static void main(String[] args) {
        SingleObject o1 = SingleObject.getInstance();
        System.out.println(o1);

        SingleObject o2 = SingleObject.getInstance();
        System.out.println(o2);
    }
}