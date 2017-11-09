package ar.uba.fi.tdd.rulogic.model;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.io.IOException;
import java.util.NoSuchElementException;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		this.knowledgeBase = new KnowledgeBase();
	}



    @Test
    public void loadFile_parameto_de_rule_inexistente_Test() {
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        try {
            knowledgeBase.load("src/main/resources/Error1-rules.db");
            Assert.fail("Debía cancelar");
        }catch(java.util.NoSuchElementException e){
            Assert.assertTrue(e.getMessage().equals("Parámetro no encontrado en argumentos de Rule"));
        }catch(IOException e){
            Assert.fail("No debía cancelar por esto");
        }
    }

    @Test
    public void loadFile_Fact_contenida_en_rule_inexistente_Test() {
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        try {
            knowledgeBase.load("src/main/resources/Error2-rules.db");
            Assert.fail("Debía cancelar");
            Assert.assertEquals(this.knowledgeBase.size(),8);
        }catch(IllegalArgumentException e){
     //       Assert.assertThat(e, "Fact inexistente en Base: ERROR");
            Assert.assertTrue(e.getMessage().equals("Rule con Fact inexistente en Rule : ERROR"));
        }catch(IOException e){
            Assert.fail("No debía cancelar por esto");
        }
    }

    @Test
    public void loadFile_filename_OK_Test() {
        this.knowledgeBase = new KnowledgeBase();
        try {
            knowledgeBase.load("src/main/resources/rules.db");
            Assert.assertEquals(8, knowledgeBase.size());
        }catch(IOException e){
            Assert.fail("No debía cancelar");
        }
    }

}



