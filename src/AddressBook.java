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

    public void newBranchMethod(){
        System.out.println("Address Book. This part was added online. This part was added in new branch.");
    }

    public ArrayList<BuddyInfo> getBuddyInfos() {
        return buddyInfos;
    }

    public void setBuddyInfos(ArrayList<BuddyInfo> buddyInfos) {
        this.buddyInfos = buddyInfos;
    }
}
