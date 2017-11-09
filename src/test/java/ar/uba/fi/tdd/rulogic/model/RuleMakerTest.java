package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class RuleMakerTest {
    @InjectMocks
    private Factory factory;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.factory = new Factory();
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void crearRule_OK_Test() {
//        DbElement elem = this.factory.make("Hijo(X,Y):-Padre(Y,X), Varom(X).");
//        Assert.assertEquals(elem.getName(), "Hijo");
//    }
//
//    @Test
//    public void crearRule_NullPointerException_Test(){
//        try {
//            DbElement elem = this.factory.make("Fact((error)):-River(x).");
//            Assert.fail("Debe dar NullPointerException");
//        }catch(Exception e){
//            Assert.assertEquals(NullPointerException.class,e.getClass());
//        }
//    }
//
//
//    @Test
//    public void crearRule_MultiplesArgumentos_Test(){
//        DbElement elem = this.factory.make("HijoM(X,Y):-Padre(Y,  X), \t   Varom(X),  Varon(Y).");
//        Assert.assertEquals(elem.getName(), "HijoM");
//    }
//
//
//    @Test
//    public void isFact_OK_Test(){
//        RuleMaker ruleMaker = new RuleMaker();
//        Assert.assertTrue(ruleMaker.isRule("Elemento(X, Y, U, I) :- Test(X, U, I), \t\t Test(T)."));
//    }
//
//
//    @Test
//    public void isFact_False_Test(){
//        RuleMaker ruleMaker = new RuleMaker();
//        Assert.assertFalse(ruleMaker.isRule("Elemento(X, Y, U, I) :- Test(X U, I), \t\t Test(T)."));
//    }

}
