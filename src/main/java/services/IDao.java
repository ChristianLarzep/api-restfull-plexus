/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author christian larzep
 * @param <T>
 */
public interface IDao<T> {
    public int insert(T object) throws SQLException;

    public int update(T object) throws SQLException;
    
    public int delete(T object) throws SQLException;
    
    public List<T> select(int id, String by) throws SQLException;
}