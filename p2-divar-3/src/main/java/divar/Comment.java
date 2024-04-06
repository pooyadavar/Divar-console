package divar;

public class Comment {
    private String customer;
    private String seller;
    private String comment;

    public Comment(String customer, String seller, String comment) {
        this.customer = customer;
        this.seller = seller;
        this.comment = comment;
    }


    public Comment() {

    }

    @Override
    public String toString() {
        return
                customer + " about " + seller + " : " + comment
                ;
    }
}


