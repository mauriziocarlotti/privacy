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
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
	
	
	

}
