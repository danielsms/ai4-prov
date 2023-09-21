package es.predictia.provtemplate.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openprovenance.prov.dot.ProvToDot;
import org.openprovenance.prov.interop.Formats.ProvFormat;
import org.openprovenance.prov.interop.InteropException;
import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.ProvFactory;
import org.openprovenance.prov.notation.Utility;
import org.openprovenance.prov.template.expander.Expand;

public class Utils {
	
	public static Document bind(String template,String bindings) throws IOException {
		return bind(Utils.read(template),bindings);
	}
	
	public static Document bind(Document template,String bindings) throws IOException {
		Document mybindings = bindings(bindings);
		Document expanded = new Expand(PROV_FACTORY,false,true).expander(template, mybindings);
        return expanded;
	}
	
	public static void write(Document doc, File output, ProvFormat format) throws IOException {
		File tmpFile = File.createTempFile("prov", ".provn");
		new Utility().writeDocument(doc, tmpFile.getAbsolutePath(), PROV_FACTORY);
		convert(tmpFile, output, format);
		tmpFile.delete();
	}
	
	protected static Document read(String input) {
		ProvFormat format = IO_FRAMEWORK.getTypeForFile(input);
		try {
            return IO_FRAMEWORK.deserialiseDocument(new FileInputStream(input), format);
		} catch(IOException e) {
			throw new InteropException(e);
		}
	}

	protected static void convert(File input, File output, ProvFormat outputFormat) {
		Document doc = IO_FRAMEWORK.readDocumentFromFile(input.getAbsolutePath());
		IO_FRAMEWORK.writeDocument(output.getAbsolutePath(), outputFormat, doc);
	}
	
	private static Document bindings(String input) throws IOException {
		return read(input);
	}

	public static void dot(Document input, File output, String title) throws FileNotFoundException {
       	ProvToDot provToDot = new ProvToDot(PROV_FACTORY);
       	provToDot.convert(input, output, title);
	}
	
	private static final ProvFactory PROV_FACTORY = new org.openprovenance.prov.xml.ProvFactory();
	private static final InteropFramework IO_FRAMEWORK = new InteropFramework();
}
