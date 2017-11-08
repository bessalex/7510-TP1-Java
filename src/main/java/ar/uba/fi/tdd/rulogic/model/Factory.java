package ar.uba.fi.tdd.rulogic.model;

public class Factory implements DbElementMaker{
    private DbElementMaker next;

    Factory(){
        DbElementMaker ruleMaker = new RuleMaker();
        this.setNext(ruleMaker);

        DbElementMaker factMaker = new FactMaker();
        ruleMaker.setNext(factMaker);

        factMaker.setNext(null);
    }

    public void setNext(DbElementMaker nextMaker){
        this.next = nextMaker;
    }

    public DbElementMaker getNext(){
        return this.next;
    }

    public DbElement make(String element){
        return this.next.make(element);
    }


}

