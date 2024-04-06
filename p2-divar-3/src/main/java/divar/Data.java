package divar;
import java.util.ArrayList;

public class Data {
    //Seller seller= new Seller();
    public static ArrayList<Comment> comments = new ArrayList<>();

    public static MainAdmin mainadmin = new MainAdmin("pma" , "a" , "09304508112" , "a@a.com" , 60000);
    public static ArrayList<MainAdmin> mainadmins = new ArrayList<MainAdmin>(){{add(mainadmin);}};

    public static Seller seller1 = new Seller("ps1" , "a" , "09304508112" , "a@a.com",1,2,4);
    public static Seller seller2 = new Seller("ps2" , "a" , "09127662844" , "b@b.com",0,2,5);


    public static ArrayList<Seller> sellers = new ArrayList<Seller>(){{add(seller1);add(seller2);}};

    public static Deliver deliver1 = new Deliver ("pd1" , "a" , "09304508112" , "a@a.com" , 0 ,StatusDelivery.Active, 5 ,8);
    public static Deliver deliver2 = new Deliver ("pd2" , "a" , "09304508112" , "a@a.com" , 0 ,StatusDelivery.Active, 10 ,3);

    public static ArrayList<Deliver> delivers=new ArrayList<Deliver>(){{add(deliver1);add(deliver2);}};
    public static Admin admin1 = new Admin("pa1","a","09304508112","a@a.com" ,0 );
    public static Admin admin2 = new Admin("pa2","a","09304508112","a@a.com" ,0 );
    public static ArrayList<Admin> admins = new ArrayList<Admin>(){{add(admin1);add(admin2);}};


    public  static Advertizing ads1 = new Advertizing(30_000 , "Galaxy a50" , seller1 , Categories.Phone ,Status.Ready_To_Sell );
    public static Advertizing ads2 = new Advertizing(850_000 , "BMW i8" ,seller2, Categories.Car , Status.Ready_To_Sell);
    public static  Advertizing ads3 = new Advertizing( 35_000, "pants" ,seller2 , Categories.Clothes , Status.Ready_To_Sell);

    public static  Advertizing ads4 = new Advertizing( 350_000, "Fridge" ,seller2 , Categories.HomeAppliances ,Status.Ready_To_Sell);

    public static  Advertizing ads5 = new Advertizing( 8_000, "pen" ,seller1 , Categories.Stationery,Status.Bought );

    public static ArrayList<Advertizing> advertizingArrayList = new ArrayList<Advertizing>(){{add(ads1);add(ads2);add(ads3);add(ads4);add(ads5);}};

    public static ArrayList<Advertizing> requesedAdvertizing = new ArrayList<>();

    public static ArrayList<Advertizing> boughtAdvertizing = new ArrayList<>();


    public static Customer customer1 = new Customer("pc1","a","09304508112","a@a.com" ,100000,2,2);
    public static Customer customer2 = new Customer("pc2","a","09304508112","a@a.com",1000,1,2 );


    public static ArrayList<Customer> customers = new ArrayList<Customer>(){{add(customer1);add(customer2);}};





}
