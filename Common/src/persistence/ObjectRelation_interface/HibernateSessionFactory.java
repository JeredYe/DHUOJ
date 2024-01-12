package persistence.ObjectRelation_interface;

import common.DBConfig;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import persistence.oj_beans.ExamDetailBean;
import persistence.oj_beans.ExamInfoBean;
import persistence.oj_beans.ExamProblemsBean;
import persistence.oj_beans.ProblemBean;
import persistence.oj_beans.ProblemTestCaseBean;
import persistence.oj_beans.SolutionBean;
import persistence.oj_beans.UserBean;
import persistence.oj_beans.WrongCaseBean;

public class HibernateSessionFactory {

    private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    private static Configuration configuration = new Configuration();// new Configuration().configure(FileFinder.findFile("hibernate.cfg.xml"));
    private static org.hibernate.SessionFactory sessionFactory;
    private static String configFile = CONFIG_FILE_LOCATION;

    static {
        try {
           configuration.setProperties(DBConfig.getDBConfig());
           
            configuration.addClass(SolutionBean.class);
            configuration.addClass(ProblemBean.class);
            configuration.addClass(ProblemTestCaseBean.class);
            configuration.addClass(ExamDetailBean.class);
            configuration.addClass(WrongCaseBean.class);
            configuration.addClass(ExamProblemsBean.class);
            configuration.addClass(UserBean.class);
            configuration.addClass(ExamInfoBean.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("%%%% Error Creating SessionFactory %%%%");
            e.printStackTrace();
        }
    }

    private HibernateSessionFactory() {
    }

    /**
     * Returns the ThreadLocal Session instance. Lazy initialize the
     * <code>SessionFactory</code> if needed.
     *
     * @return Session
     * @throws HibernateException
     */
    public static Session getSession() throws HibernateException {
        Session session = (Session) threadLocal.get();

        if (session == null || !session.isOpen()) {
            if (sessionFactory == null) {
                rebuildSessionFactory();
            }
            session = (sessionFactory != null) ? sessionFactory.openSession()
                    : null;
            threadLocal.set(session);
        }

        return session;
    }

    /**
     * Rebuild hibernate session factory
     *
     */
    public static void rebuildSessionFactory() {
        try {
            configuration.configure(configFile);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err
                    .println("%%%% Error Creating SessionFactory %%%%");
            e.printStackTrace();
        }
    }

    /**
     * Close the single hibernate session instance.
     *
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }

    /**
     * return session factory
     *
     */
    public static org.hibernate.SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * return session factory
     *
     * session factory will be rebuilded in the next call
     */
    public static void setConfigFile(String configFile) {
        HibernateSessionFactory.configFile = configFile;
        sessionFactory = null;
    }

    /**
     * return hibernate configuration
     *
     */
    public static Configuration getConfiguration() {
        return configuration;
    }

}
