package serviceimpl;

import bean.SubsidizeSchool;
import com.sun.javafx.binding.StringFormatter;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.SubsidizeSchoolService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by yongjie on 14-5-21.
 */

@Transactional
public class SubsidizeSchoolServiceBean implements SubsidizeSchoolService {

	@Resource
	SessionFactory sessionFactory;

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public SubsidizeSchool getSubsidizeSchool(Integer id) {
		SubsidizeSchool school;
		try {
			school = (SubsidizeSchool) sessionFactory.getCurrentSession().get(SubsidizeSchool.class,id);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return school;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List getSubsidizeSchools() {
		Query query;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from SubsidizeSchool ");
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return query.list();
	}

	@Override
	public boolean addSubsidizeSchool(SubsidizeSchool school) {
		try {
			sessionFactory.getCurrentSession().persist(school);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateSubsidizeSchool(SubsidizeSchool school) {
		try {
			sessionFactory.getCurrentSession().update(school);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteSubsidizeSchool(SubsidizeSchool school) {
		try {
			sessionFactory.getCurrentSession().delete(school);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List queryByDate(Date start, Date end) {
		Query query;
		try {
			query = sessionFactory.getCurrentSession().createQuery("from SubsidizeSchool where recordDate>=? and recordDate<=?");
			query.setDate(0,start);
			query.setDate(1,end);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
	public List queryDifferintArea(String area) {
		Query query;
		try {
			String sql = String.format("select distinct(%s) from SubsidizeSchool",area);
			query = sessionFactory.getCurrentSession().createQuery(sql);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
}













