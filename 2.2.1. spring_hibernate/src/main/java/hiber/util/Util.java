//package hiber.util;
//
//import hiber.model.User;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.cfg.Environment;
//import org.hibernate.service.ServiceRegistry;
//
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.util.Properties;
//
//public class Util {
//
//    private static SessionFactory sessionFactory;
//    // Статические переменные для хранения настроек
//    public static String DRIVER;
//    public static String DB_URL;
//    public static String USERNAME;
//    public static String PASSWORD;
//
//    static {
//        // Загружаем параметры из файла при инициализации класса
//        try (InputStream input = Util.class.getClassLoader().getResourceAsStream("db.properties")) {
//            if (input == null) {
//                throw new RuntimeException("Файл db.properties не найден");
//            }
//            Properties settings = new Properties();
//            settings.load(input);
//
//            DRIVER = settings.getProperty("db.driver");
//            DB_URL = settings.getProperty("db.url");
//            USERNAME = settings.getProperty("db.username");
//            PASSWORD = settings.getProperty("db.password");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration = new Configuration();
//
//
//                Properties settings = new Properties();
//                String DRIVER = settings.getProperty("db.driver");
//                String DB_URL = settings.getProperty("db.url");
//                String USER = settings.getProperty("db.username");
//                String PASSWORD = settings.getProperty("db.password");
//
//                settings.put(Environment.DRIVER, DRIVER);
//                settings.put(Environment.URL, DB_URL);
//                settings.put(Environment.USER, USER);
//                settings.put(Environment.PASS, PASSWORD);
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
//                settings.put(Environment.SHOW_SQL, "true");
//
//                configuration.setProperties(settings);
//                configuration.addAnnotatedClass(User.class);
//
//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties()).build();
//
//                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return sessionFactory;
//    }
//
//    public static void main(String[] args) {
//        try {
//            // Используем уже загруженные параметры
//            Class.forName(DRIVER);
//            try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
//                System.out.println("Соединение успешно установлено!");
//            } catch (Exception e) {
//                System.out.println("Не удалось установить соединение: " + e.getMessage());
//            }
//        } catch (ClassNotFoundException e) {
//            System.out.println("Драйвер не найден: " + e.getMessage());
//        }
//    }
//}