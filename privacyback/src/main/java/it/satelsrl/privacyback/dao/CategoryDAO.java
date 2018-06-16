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

	List<Category> list();
	Category get(int id);

}
