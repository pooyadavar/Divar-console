package divar;

import java.util.Scanner;

public class MainAdmin extends User {
    private String phoneNumber;
    private String email;
    private double wallet;

    public MainAdmin(String userName, String password, String phoneNumber, String email, double wallet) {
        super(userName, password);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.wallet = wallet;
    }

    public MainAdmin(){

    }

    public void MainAdminMenu(MainAdmin mainadmin) {
        Run run = new Run();
        Customer customer = new Customer();
        Advertizing advertizing = new Advertizing();

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. profile:");
        System.out.println("2. Sellers: ");
        System.out.println("3. Buyers: ");
        System.out.println("4. All ads: ");
        System.out.println("5. Request: ");
        System.out.println("6. giving salary: ");

        System.out.println("0. back: ");
        System.out.println("------------------------------------");
        System.out.println("choose :");
        int num = scanner.nextInt();
        switch(num){
            case 1:
                mainadmin.showProfile(mainadmin);
                break;
            case 2:
                mainadmin.showSeller(mainadmin);
                break;

            case 3:
                mainadmin.ShowCustomers(mainadmin);
                break;
            case 4:
                mainadmin.showAdvertizing(mainadmin);
                break;

            case 5:
                mainadmin.showRequestedAdvertizing(mainadmin);
                break;
            case 6:
                paymentMenu(mainadmin);
                String q = scanner.next();
                switch (q){
                    case "1" :
                        System.out.println("---------------< PAYMENT PAGE >---------------");
                        adminPay(mainadmin);
                        break;
                    case "2" :
                        System.out.println("---------------< PAYMENT PAGE >---------------");
                        deliverPay(mainadmin);
                        break;
                    case "0" :
                        MainAdminMenu(mainadmin);
                        break;
                    default:
                        System.out.println("your input is invalid please try again .");
                        paymentMenu(mainadmin);
                }
                break;
            case 0:
                run.printStartMenu();
                break;
        }

    }

    private void showRequestedAdvertizing(MainAdmin mainadmin) {
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
                MainAdminMenu(mainadmin);
            }
        }
        else {
            System.out.println("choose which Advertizing you want to accept");
            System.out.println("0. back");
            int x = scanner.nextInt();
            if (x == 0) {
                MainAdminMenu(mainadmin);
            } else {
                Advertizing advertizingObj = Data.requesedAdvertizing.get(x - 1);
                Data.advertizingArrayList.add(advertizingObj);
                Data.requesedAdvertizing.remove(x - 1);
                System.out.println("This advertizing was approver by <" + mainadmin.getUserName() +"> .");

            }
        }
    }

    private void showAdvertizing(MainAdmin mainadmin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------<ADVERTIZING>-----------");
        for (Advertizing advertizingobj: Data.advertizingArrayList){
            System.out.println(advertizingobj);
        }
        System.out.println("0. back");
        int x = scanner.nextInt();
        if(x==0){
            MainAdminMenu(mainadmin);
        }
    }

    private void ShowCustomers(MainAdmin mainadmin) {
        Scanner scanner =new Scanner(System.in);
        System.out.println("-----------<BUYERS>-----------");
        for (Customer customer: Data.customers){
            System.out.println(customer);
        }
        System.out.println("0. back: ");
        int x=scanner.nextInt();
        switch(x){
            case 0:
                MainAdminMenu(mainadmin);
                break;
            default:
                MainAdminMenu(mainadmin);
                break;
        }
    }

    private void showSeller(MainAdmin mainadmin) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----------<SELLERS>-----------");
        for (Seller seler: Data.sellers){
            System.out.println(seler);
        }
        System.out.println("0. back");
        int x = scanner.nextInt();
        if(x==0){
            MainAdminMenu(mainadmin);
        }
    }

    private void showProfile(MainAdmin mainadmin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("user name : " + mainadmin.getUserName());
        System.out.println("password : ********");
        System.out.println("Email : " + mainadmin.email);
        System.out.println("phone number : " + mainadmin.phoneNumber);
        System.out.println("wallet : "+ mainadmin.getWallet());
        System.out.println("------------------------------------------");
        System.out.println("do you want show your password ?");
        System.out.println("1.yes");
        System.out.println("2.No");
        int num = scanner.nextInt();
        if (num == 1) {
            System.out.println("password : " + mainadmin.getPassword());
            System.out.println("0. back :");
            int x = scanner.nextInt();
            if (x == 0) {
                MainAdminMenu(mainadmin);
            }
        } else {
            MainAdminMenu(mainadmin);
        }
    }

    private void paymentMenu(MainAdmin mainadmin) {
        System.out.println("your wallet balance is < " + mainadmin.getWallet() + " >");
        System.out.println("         -------------------          ");
        System.out.println("which group do you want to pay?");
        System.out.println("1. Admins");
        System.out.println("2. Delivers");
        System.out.println("0. back");
    }

    private void deliverPay(MainAdmin mainadmin) {
        Scanner scanner  = new Scanner (System.in);
        int i=1;
        System.out.println("which deliver do you want to pay?");
        for (Deliver deliver: Data.delivers){
            System.out.println(i + ". " + deliver.getUserName());
            i++;
        }
        int x = scanner.nextInt();
        System.out.println("how much money do you want to give to the desired deliver?");
        float cost = scanner.nextFloat();
        Data.delivers.get(x-1).setWallet( Data.delivers.get(x-1).getWallet() +  cost );
        Data.mainadmins.get(0).setWallet( Data.mainadmin.getWallet()-cost );
        System.out.println("your wallet ballace is: < " + mainadmin.getWallet() + " >");
        System.out.println("do you want pay salary to another delivers?");
        System.out.println("1. yes");
        System.out.println("2. No & back");
        String input = scanner.next();
        switch(input){
            case "1":
                deliverPay(mainadmin);
                break;
            case "2" :
                MainAdminMenu(mainadmin);
                break;
        }
    }

    private void adminPay(MainAdmin mainadmin) {
        Scanner scanner  = new Scanner (System.in);
        int i=1;
        System.out.println("which admin do you want to pay?");
        for (Admin admin: Data.admins){
            System.out.println(i + ". " + admin.getUserName());
            i++;
        }
        int x = scanner.nextInt();
        System.out.println("how much money do you want to give to the desired admin?");
        float cost = scanner.nextFloat();
        Data.admins.get(x-1).setWallet( Data.admins.get(x-1).getWallet() +  cost );
        Data.mainadmins.get(0).setWallet( Data.mainadmin.getWallet()-cost );
        System.out.println("your wallet ballace is: < " + mainadmin.getWallet() + " >");
        System.out.println("do you want pay salary to another admin?");
        System.out.println("1. yes");
        System.out.println("2. No & back");
        String input = scanner.next();
        switch(input){
            case "1":
                adminPay(mainadmin);
                break;
            case "2" :
                MainAdminMenu(mainadmin);
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


}
