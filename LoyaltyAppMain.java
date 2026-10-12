
package airline;

public class LoyaltyAppMain {

    public static void main(String[] args) {

        LoyaltyService service = new LoyaltyService();

        System.out.println("===== AIRLINE LOYALTY PROGRAM =====");

        // Enroll members
        Member member1 = service.enrollMember(
                "Arun", "arun@gmail.com");
        Member member2 = service.enrollMember(
                "Priya", "priya@gmail.com");

        // Add rewards
        service.addReward(new Reward(1, "Flight", 5000, 5));
        service.addReward(new Reward(2, "Upgrade", 3000, 3));
        service.addReward(new Reward(3, "Voucher", 1000, 10));

        // Add flight activities and credit miles
        FlightActivity flight1 = service.addFlightActivity(
                "AI101", "2026-10-10", 6000, "Economy");
        flight1.calculateMiles();

        int miles1 = service.creditMiles(member1, 6000, "Economy");
        System.out.println("Arun earned " + miles1 + " miles.");

        FlightActivity flight2 = service.addFlightActivity(
                "AI202", "2026-10-11", 10000, "Business");
        flight2.calculateMiles();

        int miles2 = service.creditMiles(member1, 10000, "Business");
        System.out.println("Arun earned " + miles2 + " miles.");

        int miles3 = service.creditMiles(member2, 2000, "Economy");
        System.out.println("Priya earned " + miles3 + " miles.");

        // Display member details
        System.out.println("\n===== MEMBER DETAILS =====");
        Member updatedMember1 = service.findMember(member1.getMemberId());

        if (updatedMember1 != null) {
            updatedMember1.displayDetails();
        }

        Member updatedMember2 = service.findMember(member2.getMemberId());

        if (updatedMember2 != null) {
            updatedMember2.displayDetails();
        }

        // Successful reward redemption
        System.out.println("\n===== REWARD REDEMPTION =====");
        if (updatedMember1 != null) {
            service.redeemReward(updatedMember1, 3);
        }

        // Insufficient miles test
        if (updatedMember2 != null) {
            service.redeemReward(updatedMember2, 1);
        }

        // Display available rewards
        System.out.println("\n===== AVAILABLE REWARDS =====");
        service.displayRewards();

        // Print loyalty statement
        if (updatedMember1 != null) {
            service.printStatement(updatedMember1);
        }

        // Display tier distribution and statistics
        service.displayTierDistribution();

        System.out.println("\nTotal Members: " + service.getTotalMembers());
        System.out.println("Total Miles Credited: "
                + service.getTotalMilesCredited());
        System.out.println("Total Redemptions: "
                + service.getTotalRedemptions());

        System.out.println("\n===== PROGRAM COMPLETED =====");
    }
}
