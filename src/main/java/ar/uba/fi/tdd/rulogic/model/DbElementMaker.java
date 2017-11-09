package ar.uba.fi.tdd.rulogic.model;

public interface DbElementMaker {
    void setNext(DbElementMaker nextMaker);
    DbElementMaker getNext();
    DbElement make(String element, KnowledgeBase db);
}
