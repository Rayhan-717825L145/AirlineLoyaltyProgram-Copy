
package airline;

public class Member {
    private int memberId;
    private String name;
    private String email;
    protected String tier;
    protected int milesBalance;

    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.tier = "Silver";
        this.milesBalance = 0;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTier() {
        return tier;
    }

    public int getMilesBalance() {
        return milesBalance;
    }

    public void addMiles(int miles) {
        if (miles > 0) {
            milesBalance += miles;
        }
    }

    public boolean redeemMiles(int miles) {
        if (miles > 0 && milesBalance >= miles) {
            milesBalance -= miles;
            return true;
        }
        return false;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBonusMultiplier() {
        return 1.0;
    }

    public void displayDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Tier: " + tier);
        System.out.println("Miles Balance: " + milesBalance);
    }
}
