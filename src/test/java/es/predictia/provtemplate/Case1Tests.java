package es.predictia.provtemplate;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openprovenance.prov.interop.Formats.ProvFormat;
import org.openprovenance.prov.model.Document;
import org.springframework.boot.test.context.SpringBootTest;

import es.predictia.provtemplate.utils.Utils;

@SpringBootTest
class Case1Tests {

	// Reproduces https://lucmoreau.wordpress.com/2015/07/30/provtoolbox-tutorial-4-templates-for-provenance-part-1/
	private static final String BASE_PATH = "~/prov-template/prov-template/src/test/resources/case1/";
	private static final String TEMPLATE_INPUT = BASE_PATH + "template1.provn";
	private static final String BINDINGS_1_INPUT = BASE_PATH + "binding1.ttl";
	private static final String BINDINGS_2_INPUT = BASE_PATH + "binding2.ttl";
	
	@Test
	void generationTest() throws IOException {
		Document binded_1 = Utils.bind(TEMPLATE_INPUT, BINDINGS_1_INPUT);
		Utils.write(binded_1, new File(BASE_PATH, "output_1.provn"), ProvFormat.PROVN);
		Utils.write(binded_1, new File(BASE_PATH, "output_1.ttl"), ProvFormat.TURTLE);
		Utils.write(binded_1, new File(BASE_PATH, "output_1.xml"), ProvFormat.RDFXML);

		Document binded_2 = Utils.bind(TEMPLATE_INPUT, BINDINGS_2_INPUT);
		Utils.write(binded_2, new File(BASE_PATH, "output_2.provn"), ProvFormat.PROVN);
		Utils.write(binded_2, new File(BASE_PATH, "output_2.ttl"), ProvFormat.TURTLE);
		Utils.write(binded_2, new File(BASE_PATH, "output_2.xml"), ProvFormat.RDFXML);
	}

}