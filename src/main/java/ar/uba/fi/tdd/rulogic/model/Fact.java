package ar.uba.fi.tdd.rulogic.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Fact implements DbElement {
    protected LinkedList<String> arguments; //establece el valor y el orden que debería estar en la evaluación
    protected String name;


    public Fact(String name, LinkedList<String> arguments) {
        this.name = name;
        this.arguments = arguments;
    }


    public String getName() {
        return name;
    }

    public LinkedList<String> getArguments() {
        return arguments;
    }

    public boolean evaluate(String name, LinkedList<String> arguments, KnowledgeBase db){
        int position = 0;

        if (!this.name.equals(name)) return false;

        if (this.arguments.size() != arguments.size()) return false;

        for (String argument : arguments){
            position = arguments.indexOf(argument);
            if (argument.equals(this.arguments.get(position))) return false ;
        }

        return true;
    }




//    @Override
//    public int hashCode() {
//        return this.arguments.size() * this.name.length();
//    }
}
