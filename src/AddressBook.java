import java.util.ArrayList;

public class AddressBook {
    private ArrayList<BuddyInfo>buddyInfos;

    public AddressBook(){
        buddyInfos = new ArrayList<>();
        //System.out.println("Address Book");
    }

    public void addBuddy(BuddyInfo buddy) {
        if(buddy != null) {
            buddyInfos.add(buddy);
        }
    }

    public BuddyInfo removeBuddy(int index) {
        if(index >= 0 && index < buddyInfos.size()){
            return buddyInfos.remove(index);
        }
        return null;
    }

    public static void main(String[] args){
        BuddyInfo buddy = new BuddyInfo("Tom", "Carleton", "613");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.removeBuddy(0);
    }

    public void newBranchMethod(){
        System.out.println("Address Book. This part was added online. This part was added in new branch.");
    }


}
