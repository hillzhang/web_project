package dao;

import java.util.List;
import model.Product;

public interface ProductDao {
	public List<Product> ProductDaoMemImpl(String panda);

	public Product getProductById(String id);
}
