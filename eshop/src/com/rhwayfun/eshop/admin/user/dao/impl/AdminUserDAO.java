package com.rhwayfun.eshop.admin.user.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rhwayfun.eshop.admin.user.dao.IAdminUserDAO;
import com.rhwayfun.eshop.admin.user.entity.Adminuser;
import com.rhwayfun.eshop.admin.user.entity.Seller;
import com.rhwayfun.eshop.user.entity.User;
import com.rhwayfun.eshop.utils.PageHibernateCallback;

public class AdminUserDAO extends HibernateDaoSupport implements IAdminUserDAO {

	@Override
	public Adminuser login(Adminuser adminuser) throws Exception {
		String hql = "from Adminuser where username = ? and password = ?";
		List list = this.getHibernateTemplate().find(hql,
				adminuser.getUsername(), adminuser.getPassword());
		if (list != null && list.size() > 0) {
			return (Adminuser) list.get(0);
		}
		return null;
	}

	@Override
	public List<User> findAllByPage(int startIndex, int pageSize) {
		String hql = "from User order by userid";
		List<User> list = this.getHibernateTemplate()
				.execute(
						new PageHibernateCallback<User>(hql, null, startIndex,
								pageSize));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public int findCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public User findByUid(Integer userid) {
		User user = this.getHibernateTemplate().get(User.class, userid);
		if(user != null){
			return user;
		}
		return null;
	}

	@Override
	public void delete(User user) {
		this.getHibernateTemplate().delete(user);
	}

	@Override
	public void update(User user) {
		this.getHibernateTemplate().update(user);
	}

	@Override
	public Seller loginSeller(Seller seller) {
		String hql = "from Seller where username = ? and password = ?";
		List list = this.getHibernateTemplate().find(hql,
				seller.getUsername(), seller.getPassword());
		if (list != null && list.size() > 0) {
			return (Seller) list.get(0);
		}
		return null;
	}

}
