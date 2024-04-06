package divar;

public class Check {
    Customer customer = new Customer();
    Seller seller = new Seller();
    Deliver deliver = new Deliver();
    public boolean checkCustomer(String userName, String phoneNumber, String email, String password) {

        if(customer.checkUserName(userName) && checkEmail(email) && checkPassword(password) && checkPhoneNumber(phoneNumber)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkDeliver(String userName, String phoneNumber, String email, String password) {

        if(deliver.check_userName(userName) && checkEmail(email) && checkPassword(password) && checkPhoneNumber(phoneNumber)){
            return true;
        }
        else {
            return false;
        }
    }


    public boolean checkSeller(String userName, String phoneNumber, String email, String password) {

        if(seller.check_userName(userName) && checkEmail(email) && checkPassword(password) && checkPhoneNumber(phoneNumber)){
            return true;
        }
        else {
            return false;
        }
    }


    public boolean checkEmail(String email){                                                    // check regex of email
        boolean temp = false;

        if(email.matches(".+\\@[a-zA-Z]+\\.com")){
            temp =true;

        }
        return temp;
    }

    public boolean checkPassword(String password){                                           // check regex of password
        boolean temp = false;

        if (password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$")){
            temp = true;
        }

        return temp;
    }

    public boolean checkPhoneNumber(String phoneNumber){                                 // check regex of phone number
        boolean temp = false;
        if(phoneNumber.matches("0\\d{10}")){
            temp = true;

        }
        return temp;

    }
    public boolean checkCardNumber(String cardNumber) {
        boolean temp = false;
        if (cardNumber.matches("\\d{16}")) {
            temp = true;
        }
        return temp;
    }

}
