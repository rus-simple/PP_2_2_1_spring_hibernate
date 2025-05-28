package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

import static com.mysql.cj.conf.PropertyKey.logger;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Autowired
   private LocalSessionFactoryBean sessionFactory;

   @Transactional
   @Override
   public void add(User user, Car car) {
      // Связываем объекты
      user.setCar(car);
      // Сохраняем пользователя (и каскадно машину)
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   public User findUserByCar(String model, int series) {
      Session session = sessionFactory.getObject().getCurrentSession();
      String hql = "FROM User u WHERE u.car.model = :model AND u.car.series = :series";
      Query<User> query = session.createQuery(hql, User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.uniqueResult(); // возвращает User или null, если не найден
   }
}
