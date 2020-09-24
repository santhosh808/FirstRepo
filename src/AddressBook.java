import java.util.ArrayList;

public class AddressBook {
    private ArrayList<BuddyInfo>buddyInfos;

    public AddressBook(){
        buddyInfos = new ArrayList<>();
        System.out.println("Address Book");
    }

    public void addBuddy(BuddyInfo buddy) {
        buddyInfos.add(buddy);
    }

    public BuddyInfo removeBuddy() {
        return null;
    }


}
