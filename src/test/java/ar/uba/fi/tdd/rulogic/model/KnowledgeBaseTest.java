package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.io.IOException;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		this.knowledgeBase = new KnowledgeBase();
	}

	@Test
	public void test() {

		Assert.assertTrue(this.knowledgeBase.answer("varon (javier)."));

	}

	@Test
	public void test1() {

		Assert.assertTrue(this.knowledgeBase.answer("varon (roberto)."));

	}

	@Test
    public void loadFile_filename_OK_Test() {

        try {
			this.knowledgeBase.load("src/main/resources/rules.db");

        }catch(IOException e){
            Assert.fail("No debía cancelar");
        }
    }


}



