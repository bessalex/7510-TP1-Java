package ar.uba.fi.tdd.rulogic.model;

import java.util.HashMap;
import java.util.LinkedList;

public interface DbElement {

    String getName();

    boolean evaluate(String name, LinkedList<String> arguments, KnowledgeBase db);

}
