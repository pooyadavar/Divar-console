package divar;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Customer extends User {
    private String phoneNumber;
    private String email;
    private double wallet;
    private int x;
    private int y;

    private ArrayList<Chat> userChat = new ArrayList<>();
    private ArrayList<Advertizing> advertizingSort = new ArrayList<>();



    private ArrayList<Advertizing> customerSavebox = new ArrayList<>();                //arraylist for each customer for save box
    private ArrayList<Advertizing> customerHistory = new ArrayList<Advertizing>();              //arraylist for each customer for history

    public void addToHistory(Advertizing advertizing) {
        this.customerHistory.add(advertizing);
    }
    public void addToSaveBox(Advertizing advertizing){
        this.customerSavebox.add(advertizing);
    }

    public Customer(String userName, String password, String phoneNumber, String email, double wallet, int x, int y) {
        super(userName, password);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.wallet = wallet;
        this.x = x;
        this.y = y;

    }

    public Customer() {

    }

    public void printCustomer() {
        for (Customer customer1 : Data.customers) {
            System.out.println(customer1);
        }
    }

    @Override
    public String toString() {
        return "user name :" + getUserName() +
                "\npassword :" + getPassword() +
                "\nphone number :" + phoneNumber +
                "\nEmail :" + email +
                "\n---------------------------------------------"
                ;
    }

    public static void customerMenu(Customer customer) {
        Seller seller = new Seller();
        Advertizing advertizing = new Advertizing();
        Run run = new Run();

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. profile :");
        System.out.println("2. save box : ");
        System.out.println("3. history : ");
        System.out.println("4. Sales ads :");
        System.out.println("5. edit profile :");
        System.out.println("0. back :");
        System.out.println("------------------------------------------");
        System.out.println("choose :");
        String input = scanner.next();
        switch (input) {
            case "1":
                customer.showProfile(customer);
                break;
            case "2":
                customer.showSaveBox(customer);
                break;
            case "3":
                customer.showHistory(customer);
                break;
            case "5":
                customer.changeInfo(customer);
                break;
            case "4":
                System.out.println("1. show advertizing without sorting");
                System.out.println("2. sort");
                input = scanner.next();
                switch (input) {
                    case "1":
                        int i = 1;
                        System.out.println("---------< AVAILABLE ADVERTIZING >---------");
                        for (Advertizing advertizing1 : Data.advertizingArrayList) {
                            System.out.println(i + ". " + advertizing1);
                            i++;
                        }
                        if (i == 1) {
                            System.out.println("Available advertizing list is Empty!!");
                            System.out.println("0. back");
                            int x = scanner.nextInt();
                            if (x == 0) {
                                customerMenu(customer);
                            }
                        } else {
                            System.out.println("what do you want to do?\n");
                            System.out.println("1. buy ads");
                            System.out.println("2. add to save box");
                            System.out.println("3. chat ");
                            System.out.println("4. comment");
                            System.out.println("5. show comments about sellers");
                            System.out.println("6. search ads:");
                            System.out.println("0. back");

                            System.out.println("--------------------------------------------------------------");
                            input = scanner.next();
                            switch (input) {
                                case "1":
                                    customer.buyAdvertizing(customer);

                                    break;
                                case "2":
                                    System.out.println("0. back");
                                    System.out.println("choose which Advertizing you want to add savebox?");
                                    int n = scanner.nextInt();
                                    if (n == 0) {
                                        customerMenu(customer);
                                    } else {
                                        Advertizing advertizingObj = Data.advertizingArrayList.get(n - 1);
                                        customer.addToSaveBox(advertizingObj);
                                        System.out.println("<<<<<< the product was successfully add to save box >>>>>");
                                        System.out.println("------------------------------");
                                        System.out.println("0. back");
                                        n = scanner.nextInt();
                                        if (n == 0) {
                                            customerMenu(customer);
                                        }
                                    }
                                    break;
                                case "3":
                                    System.out.println("0. back");
                                    System.out.println("choose which Advertizing do you want to chat with the seller?");
                                    n = scanner.nextInt();
                                    if (n == 0) {
                                        customerMenu(customer);
                                    } else {
                                        customer.chat(customer,Data.advertizingArrayList.get(n-1).getSeller());
                                    }
                                    break;
                                case "4":
                                    customer.commentMenu(customer);
                                    break;
                                case "5":
                                    i=1;
                                    System.out.println("-------------<COMMENTS>---------------");
                                    for (Comment commentobj : Data.comments) {
                                        System.out.println(commentobj);
                                        i++;
                                    }
                                    if(i==1){
                                        System.out.println("no comment yet");
                                    }
                                    System.out.println("------------------------------------------");
                                    customerMenu(customer);
                                    break;
                                case "6":
                                    customer.searchAds(customer);
                                    break;
                                default:
                                    System.out.println("your input is invalid please try again!");
                                    customerMenu(customer);
                            }
                        }
                        break;
                    case "2":
                        System.out.println("1.sort by price");
                        System.out.println("2.sort by category");
                        System.out.println("3.filter");
                        int q = scanner.nextInt();
                        if(q==3){
                            System.out.println("enter min price:");
                            float min = scanner.nextFloat();
                            System.out.println("enter max price:");
                            float max = scanner.nextFloat();
                            i=1;
                            for (Advertizing advertizingobj:Data.advertizingArrayList) {
                                if (advertizingobj.getPrice()>=min && advertizingobj.getPrice()<=max){
                                    System.out.println(i + ". " + advertizingobj);
                                }
                            }
                        }
                        else if(q==1){
                            System.out.println("1. sort low to high");
                            System.out.println("2. sort high to low");
                            q = scanner.nextInt();
                            switch (q){
                                case 1:
                                    customer.sortLowToHigh(customer);
                                    break;
                                case 2:
                                   customer.sortHighToLow(customer);
                                    break;
                            }
                        }
                        else if(q==2){
                            System.out.println("1. show cars:");
                            System.out.println("2. show Phone:");
                            System.out.println("3. show HomeAppliances:");
                            System.out.println("4. show Stationery:");
                            System.out.println("5. show Clothes:");
                        }
                        input = scanner.next();
                        switch (input){
                            case "1":
                                customer.sortCar(customer);
                            case "2":
                                customer.sortPhone(customer);
                            case "3":
                                customer.sortHomeAppliances(customer);
                            case "4":
                                customer.sortStationery(customer);
                            case "5":
                               customer.sortClothes(customer);
                        }
                        break;
                }
                break;
            case "0":
                run.printStartMenu();
                break;
        }
    }

    private void searchAds(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. search by ads name:");
        System.out.println("2. search by ads seller:");
        int input = scanner.nextInt();
        switch(input){
            case 1:
                customer.searchAdsByName(customer);
                break;
            case 2:
                customer.searchAdsBySeller(customer);
        }
    }

    private void searchAdsBySeller(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("search full advertizing seller name or a part of that :");
        String input = scanner.next();
//        int i=0;
//        for (Advertizing advertizingobj: Data.advertizingArrayList) {
//            if(advertizingobj.getSeller().getUserName().contains(input)){
//                System.out.println(i+". "+advertizingobj);
//            }
//        }

        List<Integer> indices = IntStream.range(0, Data.advertizingArrayList.size())
                .boxed()
                .collect(Collectors.toList());

        List<Advertizing> matchingAds = indices.stream()
                .filter(i -> Data.advertizingArrayList.get(i).getSeller().getUserName().contains(input))
                .map(i -> Data.advertizingArrayList.get(i))
                .collect(Collectors.toList());

        for (int i = 0; i < matchingAds.size(); i++) {
            System.out.println((i+1) + ". " + matchingAds.get(i));
        }
        System.out.println("//kharid");
        customerMenu(customer);

    }

    private void searchAdsByName(Customer customer){
        Scanner scanner = new Scanner(System.in);
        System.out.println("search full advertizing name or a part of that :");
        String input = scanner.next();
//        int i=0;
//        for (Advertizing advertizingobj: Data.advertizingArrayList) {
//            if(advertizingobj.getAdsName().contains(input)){
//                System.out.println(i+". "+advertizingobj);
//            }
//        }
        List<Integer> indices = IntStream.range(0, Data.advertizingArrayList.size())
                .boxed()
                .collect(Collectors.toList());

        List<Advertizing> matchingAds = indices.stream()
                .filter(i -> Data.advertizingArrayList.get(i).getAdsName().contains(input))
                .map(i -> Data.advertizingArrayList.get(i))
                .collect(Collectors.toList());

        for (int i = 0; i < matchingAds.size(); i++) {
            System.out.println((i+1) + ". " + matchingAds.get(i));
        }

        System.out.println("kharid");
        customerMenu(customer);
    }

    private void chat(Customer customer, Seller seller) {
        Scanner scanenr= new Scanner(System.in);

        Chat chat = haveOldeChat(customer, seller);
        if(chat.getReceiver()== null && chat.getSender()==null){
            chat = new Chat(customer,seller);
            customer.setUserChat(chat);
            seller.userChat.add(chat);
        }
        else{
            int i= 0;
            for (String str :chat.getChatMassage()) {
                if(i%2==0){
                    System.out.println(customer.getUserName() + ": " +str + "\n");
                }
                else{
                    System.out.println(chat.getReceiver().getUserName() + ": "+str + "\n");
                }
                i++;
            }
        }

        System.out.println("enter your massage:");
        String input = scanenr.nextLine();
        chat.setChatMassage(input);
        System.out.println("\n<< your massage sent >>");
        customerMenu(customer);
    }

    private Chat haveOldeChat(Customer customer, Seller seller) {
//        Chat chat = new Chat();
//
//        for (Chat chatobj:customer.getUserChat()) {
//            if(chatobj.getReceiver().equals(seller)){
//                chat = chatobj;
//                break;
//            }
//        }
//        return chat;
        Optional<Chat> matchingChat = customer.getUserChat().stream()
                .filter(chat -> chat.getReceiver().equals(seller))
                .findFirst();

        return matchingChat.orElse(new Chat());
    }


    private  void sortClothes(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        advertizingSort.addAll(Data.advertizingArrayList);
        Data.advertizingArrayList.clear();

        Data.advertizingArrayList.addAll(advertizingSort.stream()
                .filter(advertizing -> advertizing.getGroup().equals(Categories.Clothes))
                .collect(Collectors.toList()));

        advertizingSort.clear();

        IntStream.range(0, Data.advertizingArrayList.size())
                .forEach(i -> System.out.println((i + 1) + ". " + Data.advertizingArrayList.get(i)));

        customer.actionMenu(customer);
    }


    private  void sortStationery(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        advertizingSort.addAll(Data.advertizingArrayList);
        Data.advertizingArrayList.clear();

//        for (Advertizing advertizingobj: advertizingSort) {
//            if(advertizingobj.getGroup().equals(Categories.Stationery)){
//                Data.advertizingArrayList.add(advertizingobj);
//            }
//        }
        List<Advertizing> stationeryAds = advertizingSort.stream()
                .filter(advertising -> advertising.getGroup().equals(Categories.Stationery))
                .collect(Collectors.toList());

        advertizingSort.clear();
        int i=1;
        for (Advertizing advertizingobj:Data.advertizingArrayList) {
            System.out.println(i+". " + advertizingobj);
            i++;
        }
        customer.actionMenu(customer);
    }


    private void sortHomeAppliances(Customer customer) {
        Scanner scanner = new Scanner(System.in);

        List<Advertizing> homeAppliances = Data.advertizingArrayList.stream()
                .filter(advertizing -> advertizing.getGroup().equals(Categories.HomeAppliances))
                .collect(Collectors.toList());

        Data.advertizingArrayList.clear();
        Data.advertizingArrayList.addAll(homeAppliances);

        int i = 1;
        for (Advertizing advertizingobj : Data.advertizingArrayList) {
            System.out.println(i + ". " + advertizingobj);
            i++;
        }
        customer.actionMenu(customer);
    }

    private void sortPhone(Customer customer) {
        Scanner scanner = new Scanner(System.in);

        List<Advertizing> phones = Data.advertizingArrayList.stream()
                .filter(advertizing -> advertizing.getGroup().equals(Categories.Phone))
                .collect(Collectors.toList());

        Data.advertizingArrayList.clear();
        Data.advertizingArrayList.addAll(phones);

        int i = 1;
        for (Advertizing advertizingobj : Data.advertizingArrayList) {
            System.out.println(i + ". " + advertizingobj);
            i++;
        }
        customer.actionMenu(customer);
    }

    private void sortCar(Customer customer) {
        Scanner scanner = new Scanner(System.in);

        List<Advertizing> cars = Data.advertizingArrayList.stream()
                .filter(advertizing -> advertizing.getGroup().equals(Categories.Car))
                .collect(Collectors.toList());

        Data.advertizingArrayList.clear();
        Data.advertizingArrayList.addAll(cars);

        int i = 1;
        for (Advertizing advertizingobj : Data.advertizingArrayList) {
            System.out.println(i + ". " + advertizingobj);
            i++;
        }
        customer.actionMenu(customer);
    }

    private  void sortHighToLow(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        int size = Data.advertizingArrayList.size();
        double[] prices = new double[size];
        int i=0;
        for (Advertizing advertizingobj:Data.advertizingArrayList){
            prices[i]=advertizingobj.getPrice();
            i++;
        }
        Arrays.sort(prices);
        for(int j=size-1 ; j>=0 ; j--) {
            for (i = 0; i < size; i++) {
                if (prices[j] == Data.advertizingArrayList.get(i).getPrice()) {
                    advertizingSort.add(Data.advertizingArrayList.get(i));
                }
            }
        }
        Data.advertizingArrayList.clear();
        Data.advertizingArrayList.addAll(advertizingSort);
        advertizingSort.clear();

        i=1;
        for (Advertizing advertizingobj:Data.advertizingArrayList) {
            System.out.println(i+". " + advertizingobj);
            i++;
        }
        customer.actionMenu(customer);


    }

    private void sortLowToHigh(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        int size = Data.advertizingArrayList.size();
        double[] prices = new double[size];
        int i=0;
        for (Advertizing advertizingobj:Data.advertizingArrayList){
            prices[i]=advertizingobj.getPrice();
            i++;
        }
        Arrays.sort(prices);
        for(int j=0 ; j<size ; j++) {
            for (i = 0; i < size; i++) {
                if (prices[j] == Data.advertizingArrayList.get(i).getPrice()) {
                    advertizingSort.add(Data.advertizingArrayList.get(i));
                }
            }
        }
        Data.advertizingArrayList.clear();
        Data.advertizingArrayList.addAll(advertizingSort);
        advertizingSort.clear();

        i=1;
        for (Advertizing advertizingobj:Data.advertizingArrayList) {
            System.out.println(i+". " + advertizingobj);
            i++;
        }
        customer.actionMenu(customer);

    }
    private  void actionMenu(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("what do you want to do?\n");
        System.out.println("1. buy ads");
        System.out.println("2. add to save box");
        System.out.println("3. chat ");
        System.out.println("4. comment");
        System.out.println("5. show comments about sellers");
        System.out.println("0. back");

        System.out.println("--------------------------------------------------------------");
        String input = scanner.next();
        switch (input) {
            case "1":
                customer.buyAdvertizing(customer);

                break;
            case "2":
                System.out.println("0. back");
                System.out.println("choose which Advertizing you want to add savebox?");
                int n = scanner.nextInt();
                if (n == 0) {
                    customerMenu(customer);
                } else {
                    Advertizing advertizingObj = Data.advertizingArrayList.get(n - 1);
                    customer.addToSaveBox(advertizingObj);
                    System.out.println("<<<<<< the product was successfully add to save box >>>>>");
                    System.out.println("------------------------------");
                    System.out.println("0. back");
                    n = scanner.nextInt();
                    if (n == 0) {
                        customerMenu(customer);
                    }
                }
                break;
            case "3":
                System.out.println("0. back");
                System.out.println("choose which Advertizing do you want to chat with the seller?");
                n = scanner.nextInt();
                if (n == 0) {
                    customerMenu(customer);
                } else {
                    //Data.advertizingArrayList.get(n-1).customerChat(customer);

                }
                break;
            case "4":
                customer.commentMenu(customer);
                break;
            case "5":
                System.out.println("-------------<COMMENTS>---------------");
                for (Comment commentobj : Data.comments) {
                    System.out.println(commentobj);
                }
                System.out.println("------------------------------------------");
                customerMenu(customer);
                break;
            default:
                System.out.println("your input is invalid please try again!");
                customerMenu(customer);
        }
    }

    private void commentMenu(Customer customer) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("which seller do you want to comment on?");
        int i = 1;
        for (Seller sellerobj : Data.sellers) {
            System.out.println(i + ". " + sellerobj.getUserName());
            i++;
        }

        int n = scanner.nextInt();
        System.out.println("write your comment.");
        scanner.nextLine();
        String input = scanner.nextLine();
        Comment comment = new Comment(customer.getUserName(),Data.sellers.get(n-1).getUserName() ,input);
        Data.comments.add(comment);
        System.out.println("<<< Your comment has been registered >>>");
        System.out.println("-----------------------------------------");
        customerMenu(customer);

    }

    private void buyAdvertizing(Customer customer) {
        Deliver deliver = new Deliver();
        MainAdmin mainAdmin = new MainAdmin();
        Scanner scanner = new Scanner(System.in);

        System.out.println("choose which Advertizing you want to buy? (your wallet balance is " + customer.wallet + " )");
        int n = scanner.nextInt();
        if (n == 0) {
            customerMenu(customer);
        } else {
            int sign = 0;
            Advertizing advertizingObj = Data.advertizingArrayList.get(n - 1);

            if (advertizingObj.getPrice() <= customer.wallet) {
                sign++;
                customer.wallet = customer.wallet - advertizingObj.getPrice();

                Data.advertizingArrayList.get(n - 1).getSeller().setWallet(Data.advertizingArrayList.get(n - 1).getSeller().getWallet() + 0.9 * advertizingObj.getPrice());
                mainAdmin.setWallet(mainAdmin.getWallet() + 0.1 * advertizingObj.getPrice());
            }

            if (advertizingObj.getStatus().equals(Status.Ready_To_Sell)) {
                sign = sign + 2;
            }
            if (sign == 0) {
                System.out.println("<<< sorry this product is unavailable also your wallet doesnt have enough balance >>>");
                System.out.println("0. back");
                n = scanner.nextInt();
                if (n == 0) {
                    customerMenu(customer);
                }
            }

            if (sign == 1) {
                System.out.println("<<<< sorry! this product is unavailable. >>>>>");
                System.out.println("-----------------------------------------");
                System.out.println("0. back");
                n = scanner.nextInt();
                if (n == 0) {
                    customerMenu(customer);
                }
            } else if (sign == 2) {

                System.out.println("lack of wallet balance , charge your wallet and try again !");
                System.out.println("1. charge wallet");
                System.out.println("0. back");
                String input = scanner.next();
                switch (input){
                    case "0":
                        customerMenu(customer);
                        break;
                    case "1":
                        System.out.println("      *** You are entering the Shaprak system ***    ");
                        System.out.println("---------------------------------------------------------------");
                        chargeWallet(customer);
                        break;
                    default:
                        System.out.println("your input is invalid , please try again. ");
                        buyAdvertizing(customer);

               }
                System.out.println("-----------------------------------------");
                System.out.println("0. back");
                n = scanner.nextInt();
                if (n == 0) {
                    customerMenu(customer);
                }

            } else if (sign == 3) {
                customer.addToHistory(advertizingObj);

                System.out.println("<<<<<< the product was successfully purchased >>>>>");
                System.out.println("<<<<<< to see the list of purchases, go to 'History' section >>>>>>");
                System.out.println("<<<<<< your wallet balance is ( " + customer.wallet + " ) >>>>>>");
                System.out.println("--------------------------------------------------------------------");
                Data.advertizingArrayList.get(n - 1).setStatus(Status.Bought);
                deliver.deliverMenu(n - 1, Data.advertizingArrayList.get(n - 1).getGroup(), customer);
            }


        }
    }

    private void chargeWallet(Customer customer) {
        Check check = new Check();
        Scanner scanner = new Scanner(System.in);
        System.out.println("input your card number (16 digit) :");
        String card_number = scanner.nextLine();
        boolean temp = check.checkCardNumber(card_number);
        if(!temp){
            System.out.println(" << Your card number is incorrect. It must contain 16 digits>>");
            chargeWallet(customer);
        }
        else{
            System.out.println("How much money do you want to transfer?");
            float money = scanner.nextFloat();
            customer.setWallet(customer.getWallet()+money);

        }
        System.out.println("-----------------------------------------");
        System.out.println("<<< Your wallet has been charged successfully >>>");
        System.out.println("<<< Your wallet balance is " + customer.getWallet()+" >>>");
        System.out.println("------------------------------------------");
        customerMenu(customer);
    }

    private void showHistory(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        for (Advertizing advertizing1 : customer.customerHistory) {
            System.out.println(i + ". " + advertizing1);
            i++;
        }
        if (i == 1) {
            System.out.println("your History is Empty !!");

        }
        System.out.println("0. back");
        String x = scanner.next();
        if (x.equals("0")) {
            customerMenu(customer);
        }
        else{
            System.out.println("invalid input , please try again");
            showHistory(customer);
        }

    }

    private void showSaveBox(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        for (Advertizing advertizing1 : customer.customerSavebox) {
            System.out.println(i + ". " + advertizing1);
            i++;
        }
        if (i == 1) {
            System.out.println("your save box is Empty !!");

        }
        System.out.println("0. back");
        int x = scanner.nextInt();
        if (x == 0) {
            customerMenu(customer);
        }
    }

    private void showProfile(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("user name : " + customer.getUserName());
        System.out.println("password : ********");
        System.out.println("Email : " + customer.email);
        System.out.println("phone number : " + customer.phoneNumber);
        System.out.format("location : (%d,%d)\n",customer.x , customer.y);
        System.out.println("------------------------------------------");
        System.out.println("do you want show your password ?");
        System.out.println("1.yes");
        System.out.println("2.No");
        String input = scanner.next();
        switch (input) {
            case "1":
                System.out.println("password : " + customer.getPassword());
                System.out.println("0. back :");
                int x = scanner.nextInt();
                if (x == 0) {
                    customerMenu(customer);
                }
                break;
            case "2":
                    customerMenu(customer);
                break;

        }
    }

    private void changeInfo(Customer customer) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("which information do you want to edit? ");
        System.out.println("1. edit password");
        System.out.println("2. edit email");
        System.out.println("3. edit phone number");
        System.out.println("4. edit location");
        String num = scanner.nextLine();
        switch(num){
            case "1" :
                changePassword(customer);

                break;
            case "3" :
                changePhonrNumber(customer);
                break;
            case "2" :
                changeEmail(customer);

                break;
            case "4" :
                changeLocation(customer);
                break;
        }
        }

    private static void changeLocation(Customer customer) {
        Check check = new Check();
        Scanner scanner = new Scanner(System.in);
        System.out.format("your old location is :(%d , %d)\n" , customer.getX() , customer.getY());
        System.out.println("input your new location");
        int x = scanner.nextInt();
        Data.customers.remove(customer);
        customer.setX(x);
        Data.customers.add(customer);
        int y = scanner.nextInt();
        Data.customers.remove(customer);
        customer.setY(y);
        Data.customers.add(customer);
        System.out.println("<<<< your location changed >>>>");
        System.out.println("---------------------------------------");
        changeLocation(customer);
    }

    private static void changeEmail(Customer customer) {
        Check check = new Check();
        Scanner scanner = new Scanner(System.in);
        System.out.println("your old email is :" + customer.getEmail());
        System.out.println("input your new email");
        String input = scanner.nextLine();
        if(check.checkEmail(input)){
            Data.customers.remove(customer);
            customer.setEmail(input);
            Data.customers.add(customer);
            System.out.println("<<<< your email changed >>>>");
            System.out.println("---------------------------------------");
            customerMenu(customer);
        }
        else{
            System.out.println("regex problem try again");
            changeEmail(customer);
        }
    }

    private static void changePhonrNumber(Customer customer) {
        Check check = new Check();
        Scanner scanner = new Scanner(System.in);
        System.out.println("your old phone number is :" + customer.getPhoneNumber());
        System.out.println("input your new phone number");
         String input = scanner.nextLine();
        if(check.checkPhoneNumber(input)){
            Data.customers.remove(customer);
            customer.setPhoneNumber(input);
            Data.customers.add(customer);
            System.out.println("<<<< your phone number changed >>>>");
            System.out.println("---------------------------------------");
            customerMenu(customer);
        }
        else{
            System.out.println("regex problem try again");
            changePhonrNumber(customer);
        }
    }

    private static void changePassword(Customer customer) {
        Check check = new Check();
        Scanner scanner = new Scanner(System.in);
        System.out.println("your old password is :" + customer.getPassword());
        System.out.println("input your new password");
        String input = scanner.nextLine();
        if(check.checkPassword(input)){
            System.out.println("input your password again");
            String input1 = scanner.nextLine();
            if(input.equals(input1)) {
                Data.customers.remove(customer);
                customer.setPassword(input);
                Data.customers.add(customer);
                System.out.println("<<<< your password changed >>>>");
                System.out.println("---------------------------------------");
                customerMenu(customer);
            }
            else{
                System.out.println("<<< regex error , please try again. >>> ");
                System.out.println("---------------------------------------");
                changePassword(customer);
            }
        }
        else{
            System.out.println("regex problem try again");
            changePassword(customer);
        }
    }


    public boolean checkUserName(String userName){                                             // check username
            boolean temp = true;
            Data data = new Data();
            for (Customer customer : data.customers) {
                if (customer.getUserName().equals(userName)) {
                    temp = false;
                }
            }
            return temp;
        }

        public String getPhoneNumber () {
            return phoneNumber;
        }

        public void setPhoneNumber (String phoneNumber){
            this.phoneNumber = phoneNumber;
        }

        public String getEmail () {
            return email;
        }

        public void setEmail (String email){
            this.email = email;
        }

        public double getWallet () {
            return wallet;
        }

        public void setWallet ( double wallet){
            this.wallet = wallet;
        }

        public int getX () {
            return x;
        }

        public void setX ( int x){
            this.x = x;
        }

        public int getY () {
            return y;
        }

        public void setY ( int y){
            this.y = y;
        }

        public ArrayList<Advertizing> getCustomerHistory () {
            return customerHistory;
        }

        public void setCustomerHistory (ArrayList < Advertizing > customerHistory) {
            this.customerHistory = customerHistory;
        }

    public ArrayList<Advertizing> getCustomerSavebox() {
        return customerSavebox;
    }

    public void setCustomerSavebox(ArrayList<Advertizing> customerSavebox) {
        this.customerSavebox = customerSavebox;
    }
    public ArrayList<Chat> getUserChat() {
        return userChat;
    }

    public void setUserChat(Chat chat) {
        userChat.add(chat);
    }

}
