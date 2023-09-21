package es.predictia.provtemplate;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openprovenance.prov.interop.Formats.ProvFormat;
import org.openprovenance.prov.model.Document;
import org.springframework.boot.test.context.SpringBootTest;

import es.predictia.provtemplate.utils.Utils;

@SpringBootTest
class Case2Tests {

	// Resources:
	// https://github.com/deephdc/DEEP-OC-image-classification-tf/blob/master/metadata.json
	// https://github.com/stephank16/enes_graph_use_case/blob/master/prov_templates/esmval-prov.ipynb
	
	private static final String BASE_PATH = "~/prov-template/prov-template/src/test/resources/case2/";
	private static final String TEMPLATE_INPUT = BASE_PATH + "template.provn";
	private static final String BINDING_INPUT = BASE_PATH + "binding.provn";
	
	@Test
	void generationTest() throws IOException {
        Document binded = Utils.bind(TEMPLATE_INPUT, BINDING_INPUT);
        File outputFile = new File(BASE_PATH,"output.provn");
       	Utils.write(binded, outputFile, ProvFormat.PROVN);
       	//File plotFile = new File(BASE_PATH,"output.dot");
       	//Utils.dot(binded, plotFile, "Case2");
	}

}