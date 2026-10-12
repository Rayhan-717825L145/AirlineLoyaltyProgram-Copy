
package airline;

public class PlatinumMember extends Member {

    private double bonusMultiplier;
    private int upgradeThreshold;
    private int annualMiles;
    private String benefit;

    public PlatinumMember(int memberId, String name, String email) {
        super(memberId, name, email);
        this.tier = "Platinum";
        this.bonusMultiplier = 2.0;
        this.upgradeThreshold = 30000;
        this.annualMiles = 0;
        this.benefit = "Lounge access and priority upgrades";
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
