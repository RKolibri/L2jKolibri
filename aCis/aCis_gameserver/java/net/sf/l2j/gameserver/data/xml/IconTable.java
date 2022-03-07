package net.sf.l2j.gameserver.data.xml;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import net.sf.l2j.commons.data.xml.IXmlReader;

/**
 * 
 * @author Sarada
 *
 */
public class IconTable implements IXmlReader {
	private static final Logger _log = Logger.getLogger(IconTable.class.getName());

	private static Map<Integer, String> _icons = new ConcurrentHashMap<>();

	public static final IconTable getInstance() {
		return SingletonHolder._instance;
	}

	public IconTable() {
		load();
	}

	@Override
	public void load() {
		parseFile("./data/xml/icons.xml");
		LOGGER.info("Loaded " + _icons.size() + " icons.");
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		try {
			Node n = doc.getFirstChild();
			for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
				if (d.getNodeName().equalsIgnoreCase("icon")) {
					NamedNodeMap attrs = d.getAttributes();

					int itemId = Integer.valueOf(attrs.getNamedItem("itemId").getNodeValue());
					String iconName = attrs.getNamedItem("iconName").getNodeValue();

					if (itemId == 0 && itemId < 0) {
						_log.log(Level.WARNING, "Icon Table: itemId=\"" + itemId + "\" is not item ID, Ignoring it!");
						continue;
					}
					_icons.put(itemId, iconName);
				}
			}
		} catch (Exception e) {
			_log.log(Level.WARNING, "Icon Table: Error loading from database: " + e.getMessage(), e);
		}

		_log.info("Icon Table: Loaded " + _icons.size() + " icons.");
	}

	public static String getIcon(int id) {
		if (_icons.get(id) == null)
			return "icon.NOIMAGE";

		return _icons.get(id);
	}

	private static class SingletonHolder {
		protected static final IconTable _instance = new IconTable();
	}

}