package dao;

import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DaoFactory {

	private static DaoFactory df;
	private ProductDao pDao = null;
	static Log log = LogFactory.getLog(DaoFactory.class);
	String classname = this.getClass().getName();

	private DaoFactory() {
	}

	public static DaoFactory getInstance() {
		if (df == null)
			df = new DaoFactory();
		return df;
	}

	public ProductDao getProductDao() {
		if (pDao == null) {
			Properties properties = new Properties();
			try {
				properties.load(this.getClass().getResourceAsStream(
						"/shoppingcart.properties"));
				String className = properties.getProperty("dao.ProductDaoName");
				if (className != null) {
					pDao = (ProductDao) Class.forName(className).newInstance();
					log.info("Using " + className + " to get ProductInfo...");
				} else {
					log.info("property not found, using default implementation");
					pDao = new ProductDaoMemImpl();
				}
			} catch (Exception e) {
				log.info(e.getMessage());
				pDao = new ProductDaoMemImpl();
				return pDao;
			}
		}
		return pDao;
	}

}
