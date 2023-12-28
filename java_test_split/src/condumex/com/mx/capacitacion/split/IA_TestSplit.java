package condumex.com.mx.capacitacion.split;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sap.aii.mapping.api.AbstractTransformation;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;


public class IA_TestSplit extends AbstractTransformation{
	@Override
	public void transform(TransformationInput arg0, TransformationOutput arg1) throws StreamTransformationException {
		// TODO Auto-generated method stub
		InputStream is = (InputStream) arg0.getInputPayload().getInputStream();
		OutputStream out = (OutputStream) arg1.getOutputPayload().getOutputStream();
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			//CODIGO.
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			
			String XMLOut = "", Var1 = "", Var2 = "", Split = "";
			XMLOut += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			XMLOut += "<MT_pruebamulti>";
			
			NodeList childrenTest = doc.getElementsByTagName("MT_data");
			for (int Test = 0; Test < childrenTest.getLength(); Test++) {
				Node nodeTest = childrenTest.item(Test);
				getTrace().addInfo("Recorrido del nodo");
				if (nodeTest.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nodeTest;
					Var1 = eElement.getElementsByTagName("Var1").item(0).getTextContent();
					Var2 = eElement.getElementsByTagName("Var2").item(0).getTextContent();
					Split = eElement.getElementsByTagName("Split").item(0).getTextContent();
				}
			}
			//Tratar las variables
			
			
			//Llenar el XML.			
			getTrace().addInfo("Llenando XML");			
			XMLOut += "<Var1>" + Var1 + "</Var1>";
			XMLOut += "<Var2>" + Var2 + "</Var2>";
			XMLOut += "<Split>" + Split + "</Split>";
			XMLOut += "</MT_pruebamulti>";

			//Impresion.			
			getTrace().addInfo("Imprimir");
			byte[] b = XMLOut.getBytes();
			out.write(b);
			
		} catch (ParserConfigurationException e) {
			getTrace().addDebugMessage(e.getMessage());
			throw new StreamTransformationException(e.toString());
		} catch (SAXException e) {
			getTrace().addDebugMessage(e.getMessage());
			throw new StreamTransformationException(e.toString());
		} catch (IOException e) {
			getTrace().addDebugMessage(e.getMessage());
			throw new StreamTransformationException(e.toString());
		} 
}
	
	
}

