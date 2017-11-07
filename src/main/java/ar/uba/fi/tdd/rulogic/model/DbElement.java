package ar.uba.fi.tdd.rulogic.model;

import java.util.HashMap;

public interface DbElement {
//    protected String name;

//    DbElement(String name);
//    {
//        System.out.println("Nombre: " + name); //TODO: eliminar
//        this.name = name;
//    }

    public String getName();

    public boolean evaluate(String name, HashMap<String, Integer> values, KnowledgeBase db);
//    {
//        System.out.println(this.name);
//        return this.name;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (o instanceof DbElement) {
//            DbElement p = (DbElement)o;
//            return this.name.equals(p.name);
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public abstract int hashCode();

}
