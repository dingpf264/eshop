package com.rhwayfun.eshop.user.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rhwayfun.eshop.user.dao.IUserDAO;
import com.rhwayfun.eshop.user.entity.User;
import com.rhwayfun.eshop.user.entity.Userdetail;

public class UserDAO extends HibernateDaoSupport implements IUserDAO {

	@Override
	public void save(User user) throws Exception {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public User validateUsername(String username) {
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findUserByCode(String code) {
		List list = getHibernateTemplate().find(
				"from Userdetail where code = ?", code);
		if (list != null && list.size() > 0) {
			Userdetail userdetail = (Userdetail) list.get(0);
			// 查询用户
			List list2 = getHibernateTemplate().find(
					"from User where userid = ?", userdetail.getUserid());
			return (User) list2.get(0);
		}
		return null;
	}

	@Override
	public void update(User user) {
		getHibernateTemplate().update(user);
	}

	@Override
	public User login(User user) {
		List list = getHibernateTemplate().find(
				"from User where username = ? and password = ?",
				user.getUsername(), user.getPassword());
		if(list != null && list.size() > 0){
			return (User) list.get(0);
		}
		return null;
	}

	@Override
	public User findUserById(Integer userid) {
		User user = this.getHibernateTemplate().get(User.class, userid);
		if(user != null){
			return user;
		}
		return null;
	}

}
