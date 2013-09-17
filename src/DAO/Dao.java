package DAO;

import JavaBean.Sensor;

public interface Dao {
	public void insert(Sensor sensor);
	public void query();
	public void	show();
	public void delete();
	public void modify();
}
