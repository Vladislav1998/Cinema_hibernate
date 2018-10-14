/**
 * Created by valeria on 25.03.2018.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(Cinema.class.getPackage());

        // SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //Session session=sessionFactory.openSession();

        //Cinema cinema=new Cinema("Multiplex Dафи","Харьков, ул. Героев труда, 9, ТРЦ «Дафи»","multiplex.ua");
      /*  System.out.println(session.get(Film.class,1).getTitle());
        //session.save(cinema);
        SQLQuery query=session.createSQLQuery("SELECT * FROM cinema");
        query.addEntity(Cinema.class);
        List<Cinema> c=query.getResultList();
        System.out.println(c.get(0).getCinemaID());
        session.close();
        sessionFactory.close();*/
    }
}
