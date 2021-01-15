package ýnterfaces;

import java.util.List;

import contracts.KategoriContract;

public interface DALInterfaces<T> {
	
	void Insert (T entity);
	List<T> GetAll();
	T Delete (T entity);
	void Update (T entity);
	List<T> GetById(int id);

}
