package divar;
import java.util.*;


import static java.lang.System.out;

public class Run {
    Customer customer = new Customer();
    Seller seller = new Seller();
    Deliver deliver = new Deliver();


    public void printStartMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\u001B[33mselect:\033[0m");
        System.out.println("1.Sign in:");
        System.out.println("2.Sign up:");
        out.println("0. exit");
        String x = scanner.nextLine();
        switch(x){
            case "1":
                inputInfo();
                break;
            case "2":
                signUpMenu();
                break;
            case "0":
                return;
            default:
                out.println("your input is invalid please try aqain");
                printStartMenu();
        }


    }

    private void signUpMenu() {
        Scanner scanner= new Scanner(System.in);
        out.println("--------<sign up>--------");
        out.println("1.Cutomer:");
        out.println("2.Seller:");
        out.println("3.Deliver;");
        String x = scanner.nextLine();
        switch (x){
            case "1":
                out.println("\u001B[32m----------------------< SIGN-UP CUSTOMER >---------------------\033[0m");
                signupCustomerMenu(scanner);
                break;
            case "2":
                out.println("\u001B[32m----------------------< SIGN-UP SELLER >---------------------\033[0m");
                signupSellerMenu(scanner);
                break;
            case "3":
                out.println("\u001B[32m----------------------< SIGN-UP DELIVER >---------------------\033[0m");
                signupDeliverMenu(scanner);
                break;
            default:
                out.println("your input is invalid please try aqain");
                signUpMenu();
        }

    }

    private void signupDeliverMenu(Scanner scanner) {
        Check check = new Check();

        out.println("username:");
        String userName = scanner.next();
        deliver.setUserName(userName);

        out.println("password: ");
        String password = scanner.next();

        out.println("Enter your password again:");
        String password2 = scanner.next();

        if (password2.equals(password)) {
            deliver.setPassword(password);
        } else {
            out.println("please correct your password !!");
            signupSellerMenu(scanner);
        }

        out.println("email: ");
        String email = scanner.next();
        deliver.setEmail(email);

        out.println("phone Number : ");
        String phoneNumber = scanner.next();
        deliver.setPhoneNumber(phoneNumber);

        out.println("enter your x:");
        int x = scanner.nextInt();
        deliver.setX(x);

        out.println("enter your x:");
        int y = scanner.nextInt();
        deliver.setY(y);

        if (check.checkDeliver(userName, phoneNumber, email, password)) {

            Deliver deliver = new Deliver(userName, password, phoneNumber, email,0,StatusDelivery.Active,x,y);
            Data.delivers.add(deliver);

        }
        else {
            out.println("something is wrong try again !! ");
            out.println("----------------------------------");
            signupDeliverMenu(scanner);
        }


    }

    private void signupSellerMenu(Scanner scanner) {
        Check check = new Check();


        out.println("username:");
        String userName = scanner.next();
        seller.setUserName(userName);

        out.println("password: ");
        String password = scanner.next();

        out.println("Enter your password again:");
        String password2 = scanner.next();

        if (password2.equals(password)) {
            seller.setPassword(password);
        } else {
            out.println("please correct your password !!");
            signupSellerMenu(scanner);
        }

        out.println("email: ");
        String email = scanner.next();
        seller.setEmail(email);

        out.println("phone Number : ");
        String phoneNumber = scanner.next();
        seller.setPhoneNumber(phoneNumber);

        out.println("enter your x:");
        int x = scanner.nextInt();
        seller.setX(x);

        out.println("enter your x:");
        int y = scanner.nextInt();
        seller.setY(y);

        if (check.checkSeller(userName, phoneNumber, email, password)) {

            Seller seller = new Seller(userName, password, phoneNumber, email,0,x,y);
            Data.sellers.add(seller);
            Seller.sellerMenu(seller);

        }
        else {
            out.println("something is wrong try again !! ");
            out.println("----------------------------------");
            signupSellerMenu(scanner);
        }
    }

    private void signupCustomerMenu(Scanner scanner) {
        Check check = new Check();


        out.println("username:");
        String userName = scanner.next();
        customer.setUserName(userName);

        out.println("password: ");
        String password = scanner.next();

        out.println("Enter your password again:");
        String password2 = scanner.next();

        if (password2.equals(password)) {
            customer.setPassword(password);
        } else {
            out.println("please correct your password !!");
            signupCustomerMenu(scanner);
        }

        out.println("email: ");
        String email = scanner.next();
        customer.setEmail(email);

        out.println("phone Number : ");
        String phoneNumber = scanner.next();
        customer.setPhoneNumber(phoneNumber);

        out.println("enter your x:");
        int x = scanner.nextInt();
        customer.setX(x);

        out.println("enter your x:");
        int y = scanner.nextInt();
        customer.setX(y);

        if (check.checkCustomer(userName, phoneNumber, email, password)) {
            Customer customer = new Customer(userName, password, phoneNumber, email,0,x,y);
            Data.customers.add(customer);
            Customer.customerMenu(customer);



        }
        else {
            out.println("something is wrong try again !! ");
            out.println("----------------------------------");
            signupCustomerMenu(scanner);
        }
    }


    private void inputInfo() {
        Scanner scanner = new Scanner(System.in);
        out.println("Enter your username : ");
        String username = scanner.nextLine();
        out.println("Enter your password : ");
        String password = scanner.nextLine();

        Data user = new Data();
        int tag=0;

        if(tag==0){
            Optional<MainAdmin> matchingMainAdmin = user.mainadmins.stream()
                    .filter(mainadmin -> mainadmin.getUserName().equals(username) && mainadmin.getPassword().equals(password))
                    .findFirst();

            if (matchingMainAdmin.isPresent()) {
                tag++;
                out.println("\u001B[32m----------------------< WELCOME MAIN-ADMIN >---------------------\033[0m");
                matchingMainAdmin.get().MainAdminMenu(matchingMainAdmin.get());
            }
        }


        if(tag==0){
            Optional<Deliver> matchingDeliver = user.delivers.stream()
                    .filter(deliver -> deliver.getUserName().equals(username) && deliver.getPassword().equals(password))
                    .findFirst();

            if (matchingDeliver.isPresent()) {
                tag++;
                out.println("\u001B[32m----------------------< WELCOME DELIVER>---------------------\033[0m");
            }
        }

        if(tag==0){
            Optional<Admin> matchingAdmin = user.admins.stream()
                    .filter(admin -> admin.getUserName().equals(username) && admin.getPassword().equals(password))
                    .findFirst();

            if (matchingAdmin.isPresent()) {
                tag++;
                out.println("\u001B[32m----------------------< WELCOME ADMIN>---------------------\033[0m");
                Admin.AdminMenu(matchingAdmin.get());
            }
        }

        if(tag==0) {
            Optional<Customer> matchingCustomer = user.customers.stream()
                    .filter(customer -> customer.getUserName().equals(username) && customer.getPassword().equals(password))
                    .findFirst();

            if (matchingCustomer.isPresent()) {
                tag++;
                out.println("\u001B[32m----------------------< WELCOME CUSTOMER >---------------------\033[0m");
                Customer.customerMenu(matchingCustomer.get());
            }
        }

        if(tag==0) {
            Optional<Seller> matchingSeller = user.sellers.stream()
                    .filter(seller -> seller.getUserName().equals(username) && seller.getPassword().equals(password))
                    .findFirst();

            if (matchingSeller.isPresent()) {
                tag++;
                out.println("\u001B[32m----------------------< WELCOME SELLER >---------------------\033[0m");
                Seller.sellerMenu(matchingSeller.get());
            }
        }


    }
}
