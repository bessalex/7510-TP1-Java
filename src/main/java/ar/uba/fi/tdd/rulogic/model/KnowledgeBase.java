package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class KnowledgeBase {
	private Factory factory;
    private HashMap<String,DbElement> database;
    private FactMaker queryMaker;

	public KnowledgeBase(){
	    this.database = new HashMap<String,DbElement>();
        factory = new Factory();
        queryMaker = new FactMaker();
    }

    public void load(String filename) throws IOException {
        FileReader file = new FileReader(filename);
        BufferedReader br = new BufferedReader(file);
        for (String line; (line = br.readLine()) != null; ) {
    //        System.out.println("Linea leída: " + line); //TODO: eliminar
            DbElement element = this.factory.make(line);
            this.addElement(element);
        }
    }

    public void addElement(DbElement element){
        String name = element.getName();
        this.database.put(name, element);
    }

	public boolean answer(String query) {

        if (!queryMaker.isFact(query)) return false;

        DbElement newQuery = queryMaker.make(query);



        return true;
	}




}
