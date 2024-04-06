package divar;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Deliver extends User {

    private String phoneNumber;
    private String email;
    private float wallet;

    private StatusDelivery statusDelivery;
    private int x;
    private int y;

    public Deliver(String userName, String password, String phoneNumber, String email, float wallet, StatusDelivery statusDelivery, int x, int y) {
        super(userName, password);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.wallet = wallet;
        this.statusDelivery = statusDelivery;
        this.x = x;
        this.y = y;
    }



    public static User selectRandomMember(ArrayList<Admin> admins) {                    //random admin
        Random random = new Random();
        int randomIndex = random.nextInt(admins.size());
        User randomMember = admins.get(randomIndex);
        return randomMember;
    }




    public Deliver(){

    }

    public boolean check_userName(String userName){                                             // check username
        Data data = new Data();
        return !data.delivers.stream().anyMatch(deliver -> deliver.getUserName().equals(userName));
    }


    public void deliverMenu( int n ,Categories group, Customer customer) {
        Admin admin = new Admin();
        Scanner scanner = new Scanner(System.in);
        System.out.println("do you need a deliver to send the product?");
        System.out.println("1. yes");
        System.out.println("2. No & back");
        String input = scanner.next();
        switch(input){
            case "1":
                if(group.equals(Categories.Car)){
                    System.out.println("<<< sorry , this product can not be shipped. >>>");
                    customer.customerMenu(customer);

                }
                if(group.equals(Categories.Clothes) || group.equals(Categories.Phone) || group.equals(Categories.Stationery)){
                    System.out.println("<<< your product will send you with BIKE-DELIVERY >>>");
                    User SelectedAdmin = selectRandomMember(Data.admins);

                    admin.advertizingAdminSelectDeliver.add(Data.advertizingArrayList.get(n));
                    System.out.println(SelectedAdmin.getUserName());//for presentation

                    customer.customerMenu(customer);//

                }
                if(group.equals(Categories.HomeAppliances)){
                    System.out.println("<<< your product wii send you with VANTY-DELIVERY >>>");

                }
                break;

            case "2" :
                customer.customerMenu(customer);

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

    public float getWallet() {
        return wallet;
    }

    public void setWallet(float wallet) {
        this.wallet = wallet;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public StatusDelivery getStatusDelivery() {
        return statusDelivery;
    }

    public void setStatusDelivery(StatusDelivery statusDelivery) {
        this.statusDelivery = statusDelivery;
    }



}
