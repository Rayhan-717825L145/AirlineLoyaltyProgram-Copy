
package airline;

public class SilverMember extends Member {

    private double bonusMultiplier;
    private int upgradeThreshold;
    private int annualMiles;
    private String benefit;

    public SilverMember(int memberId, String name, String email) {
        super(memberId, name, email);
        this.tier = "Silver";
        this.bonusMultiplier = 1.0;
        this.upgradeThreshold = 5000;
        this.annualMiles = 0;
        this.benefit = "Standard miles";
    }

    @Override
    public double getBonusMultiplier() {
        return bonusMultiplier;
    }

    public int getUpgradeThreshold() {
        return upgradeThreshold;
    }

    public int getAnnualMiles() {
        return annualMiles;
    }

    public String getBenefit() {
        return benefit;
    }

    public void recordAnnualMiles(int miles) {
        if (miles > 0) {
            annualMiles += miles;
        }
    }

    public boolean isEligibleForUpgrade() {
        return annualMiles >= upgradeThreshold;
    }

    public void displayTierBenefits() {
        System.out.println("Tier: " + tier);
        System.out.println("Bonus Multiplier: " + bonusMultiplier);
        System.out.println("Upgrade Threshold: " + upgradeThreshold);
        System.out.println("Benefit: " + benefit);
    }
}
