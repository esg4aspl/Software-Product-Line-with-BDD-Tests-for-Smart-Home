package testrunners;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * TODO description
 */
public class FeatureExtractor {
	
	private String root; //Example: SmartHome
	DocumentBuilder builder;
	Document doc;
	
	public FeatureExtractor(String root, String sourceConfigPath) {
		this.root = root;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = builder.parse(sourceConfigPath);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public List<String> getFeatures() {
		List<String> result = new ArrayList<String>();
		NodeList configs = doc.getElementsByTagName("configuration");
		Node config = configs.item(0);
		NodeList allFeatures = config.getChildNodes();
		int len = allFeatures.getLength();
		for (int i = 0; i < len; i++) {
			Node feature = allFeatures.item(i);
			if (feature.getNodeType() != Node.ELEMENT_NODE)
				continue;
			NamedNodeMap map = feature.getAttributes();
			String featureName = map.getNamedItem("name").getNodeValue();
			if (featureName.equals(root))
				continue;
			Node automatic = map.getNamedItem("automatic");
			Node manual = map.getNamedItem("manual");
			if ( (automatic != null && automatic.getNodeValue().equals("selected"))
					|| (manual != null && manual.getNodeValue().equals("selected")) ) {
				result.add(featureName);
			}
		}
		
		return result;
	}
	

}