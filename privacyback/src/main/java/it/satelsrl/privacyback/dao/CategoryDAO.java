/**
 * 
 */
package it.satelsrl.privacyback.dao;

import java.util.List;

import it.satelsrl.privacyback.dto.Category;

/**
 * @author Maurizio Carlotti
 *
 */
public interface CategoryDAO {

	Category get(int id);
	List<Category> list();
	boolean addCategory(Category category);
	boolean updateCategory(Category category);
	boolean deleteCategory(Category category);

}
