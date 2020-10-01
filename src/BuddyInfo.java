public class BuddyInfo {

    private String name;     //remember to make variables static.
    private String address;  //101100880
    private String number;

    /*public static void main(String[] args) {
        name = "Homer";
        address = "123 Doesn'tmatter Crescent";
        number = "(613)123-4567";
        //System.out.println("Hello " + getName());
    }*/

    public BuddyInfo(String name, String address, String number)
    {
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public String getName() {    // generate getter function is at the top and not static.
        return name;
    }

    public String getAddress() {        //didn't make this static because it wasn't used in the lab.
        return address;
    }

    public String getNumber() {
        return number;
    }
}
