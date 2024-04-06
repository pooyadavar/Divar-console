package divar;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    public ArrayList<Seller> sellerobj=new ArrayList<>();

    public ArrayList<Advertizing> advertizingAdminSelectDeliver = new ArrayList<>();

    private String phoneNumber;
    private String email;
    private double wallet;


    public Admin(String userName, String password, String phoneNumber, String email, double wallet) {
        super(userName, password);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.wallet = wallet;
    }

    public Admin(){

    }
    public static void AdminMenu(Admin admin) {
        Run run = new Run();
        Customer customer = new Customer();
        Advertizing advertizing = new Advertizing();

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Sellers: ");
        System.out.println("2. Buyers: ");
        System.out.println("3. All ads: ");
        System.out.println("4. Request: ");
        System.out.println("5. Delivery Request:");
        System.out.println("0. back: ");
        System.out.println("------------------------------------");
        System.out.println("choose :");
        int num = scanner.nextInt();
        switch(num){
            case 1:
                admin.showSellers(admin);
                break;

            case 2:
                admin.showBuyers(admin);
                break;

            case 3:
                admin.showAdvertizing(admin);
                break;

            case 4:
                admin.showRequestesAdvertizing(admin);
                break;
            case 5:
                admin.selectDeliver(customer,admin);
                break;
            case 0:
                run.printStartMenu();
                break;
        }

    }
    public void selectDeliver( Customer customer,Admin admin) {

        System.out.format("location of customer :(%d,%d)\n" , customer.getX() , customer.getY());
        System.out.println("the location of all active drivers:");
//        for (Deliver deliverobj:Data.delivers) {
//            System.out.println(deliverobj.getUserName() + ": (" + deliverobj.getX() + "," +deliverobj.getY() +")");
//        }
        Data.delivers.stream().forEach(deliverobj -> System.out.println(deliverobj.getUserName() + ": (" + deliverobj.getX() + "," + deliverobj.getY() + ")"));

        System.out.println("----------------------------------------------");
        calculateNearestDeliver(customer,admin);


    }

    private void calculateNearestDeliver(Customer customer, Admin admin) {
        Scanner scanner = new Scanner(System.in);
        int length = Data.delivers.size();
        float distance[] = new float[length];
        for(int i=0 ; i<length ; i++){
             distance[i] = (float) Math.sqrt(Math.pow(customer.getX()-Data.delivers.get(i).getX(),2) + (Math.pow(customer.getY()-Data.delivers.get(i).getY(),2)));
        }

        for (int i=0 ; i<length ; i++){
            System.out.println("The distance of the customer from <" + Data.delivers.get(i).getUserName() + "> is <" + distance[i] +"> ");
        }
        System.out.println("----------------------------------------------");
        System.out.println("the nearest deliver:");
        float smallest = distance[0];
        int n=0;
        for (int i = 1; i <length; i++) {
            if (distance[i] < smallest) {
                smallest = distance[i] ;
                n++;
            }
        }//
        System.out.println(Data.delivers.get(n).getUserName() +": " + smallest);
        System.out.println("do you approve؟");
        System.out.println("1. yes");
        System.out.println("2. No & delete");
        int input = scanner.nextInt();

        switch (input){
            case 1:
                Data.delivers.get(n).getStatusDelivery().equals(StatusDelivery.Inactive);
                System.out.println("The product will be sent");
                advertizingAdminSelectDeliver.clear();
                AdminMenu(admin);
                break;
            case 2:
                advertizingAdminSelectDeliver.clear();
                AdminMenu(admin);
                break;

        }
}


    private void showRequestesAdvertizing(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        for (Advertizing advertizing1: Data.requesedAdvertizing) {
            System.out.println(i + ". " + advertizing1);
            i++;
        }
        if(i==1){
            System.out.println("your Request list is Empty!!");
            System.out.println("0. back");
            int x = scanner.nextInt();
            if(x==0){
                AdminMenu(admin);
            }
        }
        else {
            System.out.println("choose an advertizing");
            System.out.println("0. back");
            int x = scanner.nextInt();
            if (x == 0) {
                AdminMenu(admin);
            } else {
                System.out.println("1. accept advertizing :");
                System.out.println("2. remove advertizing from requested advertizing :");
                String input = scanner.next();
                switch (input){
                    case "1":
                        Advertizing advertizingObj = Data.requesedAdvertizing.get(x - 1);
                        Data.advertizingArrayList.add(advertizingObj);
                        Data.requesedAdvertizing.remove(x - 1);
                        System.out.println("This advertizing was approver by <" + admin.getUserName() +"> .");
                        break;
                    case "2":
                        Advertizing advertizingObjj = Data.requesedAdvertizing.get(x - 1);
                        Data.requesedAdvertizing.remove(advertizingObjj);
                        System.out.println("this advertizing was removed by <" + admin.getUserName() +"> .");
                        break;
                }
                System.out.println("-------------------------------------");
                AdminMenu(admin);
            }
        }
    }

    private  void showAdvertizing(Admin admin) {
        Scanner scanner = new Scanner (System.in);
        System.out.println("-----------<ADVERTIZING>-----------");
        for (Advertizing advertizing: Data.advertizingArrayList){
            System.out.println(advertizing);
        }
        System.out.println("0. back");
        int x = scanner.nextInt();
        if(x==0){
            AdminMenu(admin);
        }
    }

    private void showBuyers(Admin admin) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----------<BUYERS>-----------");
        for (Customer customer: Data.customers){
            System.out.println(customer);
        }

        System.out.println("do you want to delete any buyer?");
        System.out.println("1. yes");
        System.out.println("2. No & back");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                System.out.println("which buyer do you want to remove?");
                int n = scanner.nextInt();
                Data.customers.remove(n - 1);
                System.out.println("buyer removed by <" + admin.getUserName()+" >");
                AdminMenu(admin);
                break;
            case 2:
                AdminMenu(admin);
                break;
        }
    }

    private void showSellers(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------<SELLERS>-----------");
        int i = 1;
        for (Seller seler: Data.sellers){
            System.out.println(i+ ". "+ seler);
            i++;
        }
        System.out.println("do you want to delete any seller?");
        System.out.println("1. yes");
        System.out.println("2. No & back");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                System.out.println("which seller do you want to remove ?");
                int n = scanner.nextInt();
                Data.sellers.remove(n - 1);
                System.out.println("seller removed by <" + admin.getUserName()+" >");
                AdminMenu(admin);
                break;
            case 2:
                AdminMenu(admin);
                break;
        }
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }


    public void delivermenue(int n, Categories group, Customer customer) {

    }
}
