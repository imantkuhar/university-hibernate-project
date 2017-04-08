package hibernateDB;

import com.mysql.cj.api.Session;
import hibernateModel.Group;
import model.Subject;
import utils.EntityManagerFactoryService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imant on 30.11.16.
 */
public class GroupDao {

    private static GroupDao instance;

    public static GroupDao getInstance() {
        if (instance == null)
            instance = new GroupDao();
        return instance;
    }

    public boolean addNewGroup(Group group) {
        EntityManager manager = EntityManagerFactoryService.getInstance().getManagerFactory().createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            manager.persist(group);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            manager.close();
        }
        return false;
    }

    public boolean deleteGroup(Group group) {
        EntityManager manager = EntityManagerFactoryService.getInstance().getManagerFactory().createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        Group removedGroup = manager.find(Group.class, group.getId());
        if (removedGroup == null) {
            manager.close();
            return false;
        }

        try {
            transaction.begin();
            manager.remove(removedGroup);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            manager.close();
        }
        return false;
    }

    public Group getGroup(Group group) {
        EntityManager manager = EntityManagerFactoryService.getInstance().getManagerFactory().createEntityManager();

        Group foundedGroup = manager.find(Group.class, group.getId());
        if (foundedGroup != null) {
            manager.close();
        } else {
            manager.close();
        }
        return foundedGroup;
    }
}
