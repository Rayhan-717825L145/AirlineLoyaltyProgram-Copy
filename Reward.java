
package airline;

public class Reward {
    protected int rewardId;
    protected String name;
    protected int milesRequired;
    protected int availability;

    public Reward(int rewardId, String name, int milesRequired, int availability) {
        this.rewardId = rewardId;
        this.name = name;
        this.milesRequired = milesRequired;
        this.availability = availability;
    }

    public int getRewardId() {
        return rewardId;
    }

    public String getName() {
        return name;
    }

    public int getMilesRequired() {
        return milesRequired;
    }

    public int getAvailability() {
        return availability;
    }

    public boolean redeem(Member member) {
        if (availability > 0 && member.redeemMiles(milesRequired)) {
            availability--;
            System.out.println(name + " redeemed successfully!");
            return true;
        }

        System.out.println("Redemption failed: insufficient miles or unavailable.");
        return false;
    }

    public void restock(int quantity) {
        if (quantity > 0) {
            availability += quantity;
        }
    }

    public void displayReward() {
        System.out.println("Reward ID: " + rewardId);
        System.out.println("Name: " + name);
        System.out.println("Miles Required: " + milesRequired);
        System.out.println("Availability: " + availability);
    }

    public boolean isAvailable() {
        return availability > 0;
    }
}
