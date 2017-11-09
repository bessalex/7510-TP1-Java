package ar.uba.fi.tdd.rulogic.model;

import sun.awt.image.ImageWatched;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class KnowledgeBase {
	private Factory factory;
    private HashMap<String,LinkedList<DbElement>> database;
    private FactMaker queryMaker;

	public KnowledgeBase(){
	    this.database = new HashMap<String,LinkedList<DbElement>>();
        factory = new Factory();
        queryMaker = new FactMaker();
    }

    public void load(String filename) throws IOException, IllegalArgumentException  {

        FileReader file = new FileReader(filename);
        BufferedReader br = new BufferedReader(file);
        for (String line; (line = br.readLine()) != null; ) {
            DbElement element = this.factory.make(line, this);
            this.addElement(element);
        }
    }

    public void addElement(DbElement element){
        LinkedList<DbElement> arguments;
        String name = element.getName();

        if (this.database.containsKey(name)) {
            arguments = this.database.get(name);
        }else{
            arguments = new LinkedList<DbElement>();
        }

        arguments.add(element);
        this.database.put(name,arguments);
    }

	public boolean answer(String query) throws IllegalArgumentException{

        if (!queryMaker.isFact(query)) throw new IllegalArgumentException("Consulta mal formada");

        DbElement queryElement = queryMaker.make(query,this);

        if(!this.database.containsKey(queryElement.getName())) return false;

        LinkedList<DbElement> elementsFound = this.database.get(queryElement.getName());


        for (DbElement element: elementsFound){
            if (element.evaluate(queryElement.getName(),queryElement.getArguments(),this)) return true;
        }

        return false;
	}

	public LinkedList<DbElement> getElementList(String name){
        return this.database.get(name);
    }

    public int size(){
	    return this.database.size();
    }

    public boolean contains(String name){
        return this.database.containsKey(name);
    }
}
