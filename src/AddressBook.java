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

    public void removeBuddy(BuddyInfo buddy) {
        /*if(index >= 0 && index < buddyInfos.size()){
            return buddyInfos.remove(index);
        }
        return null;*/
        /*for (BuddyInfo b : buddyInfos) {
            if (b.equals(buddy)) {
                System.out.println(b.getName() + " was removed.");
                buddyInfos.remove(b);
                return;

            }
        }*/
        buddyInfos.remove(buddy);
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
