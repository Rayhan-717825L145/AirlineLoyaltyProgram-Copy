
package airline;

import java.util.ArrayList;
import java.util.List;

public class LoyaltyService {

    private List<Member> members;
    private List<FlightActivity> activities;
    private List<Reward> rewards;
    private int nextMemberId;
    private int nextActivityId;
    private int totalMilesCredited;
    private int totalRedemptions;

    public LoyaltyService() {
        members = new ArrayList<>();
        activities = new ArrayList<>();
        rewards = new ArrayList<>();
        nextMemberId = 1001;
        nextActivityId = 1;
        totalMilesCredited = 0;
        totalRedemptions = 0;
    }

    public Member enrollMember(String name, String email) {
        Member member = new SilverMember(nextMemberId++, name, email);
        members.add(member);
        System.out.println("Member enrolled: " + member.getName());
        return member;
    }

    public FlightActivity addFlightActivity(String flightNo, String date,
                                            int distance, String fareClass) {
        FlightActivity activity = new FlightActivity(
                nextActivityId++, flightNo, date, distance, fareClass);
        activities.add(activity);
        return activity;
    }

    public int creditMiles(Member member, int distance) {
        return creditMiles(member, distance, "Economy");
    }

    public int creditMiles(Member member, int distance, String fareClass) {
        double fareMultiplier = 1.0;

        if (fareClass.equalsIgnoreCase("Business")) {
            fareMultiplier = 1.5;
        } else if (fareClass.equalsIgnoreCase("First")) {
            fareMultiplier = 2.0;
        }

        int baseMiles = (int) (distance * fareMultiplier);
        int earnedMiles = (int) (baseMiles * member.getBonusMultiplier());

        member.addMiles(earnedMiles);
        totalMilesCredited += earnedMiles;

        if (member instanceof SilverMember) {
            ((SilverMember) member).recordAnnualMiles(earnedMiles);
        } else if (member instanceof GoldMember) {
            ((GoldMember) member).recordAnnualMiles(earnedMiles);
        } else if (member instanceof PlatinumMember) {
            ((PlatinumMember) member).recordAnnualMiles(earnedMiles);
        }

        evaluateTier(member);
        return earnedMiles;
    }

    public void evaluateTier(Member member) {
        int annualMiles = 0;

        if (member instanceof SilverMember) {
            annualMiles = ((SilverMember) member).getAnnualMiles();
        } else if (member instanceof GoldMember) {
            annualMiles = ((GoldMember) member).getAnnualMiles();
        } else if (member instanceof PlatinumMember) {
            annualMiles = ((PlatinumMember) member).getAnnualMiles();
        }

        if (annualMiles >= 30000 && !(member instanceof PlatinumMember)) {
            replaceMemberTier(member, new PlatinumMember(
                    member.getMemberId(), member.getName(), member.getEmail()));
        } else if (annualMiles >= 15000 && member instanceof SilverMember) {
            replaceMemberTier(member, new GoldMember(
                    member.getMemberId(), member.getName(), member.getEmail()));
        }
    }

    private void replaceMemberTier(Member oldMember, Member newMember) {
        newMember.addMiles(oldMember.getMilesBalance());

        if (oldMember instanceof SilverMember) {
            newMember = copyAnnualMiles(newMember,
                    ((SilverMember) oldMember).getAnnualMiles());
        } else if (oldMember instanceof GoldMember) {
            newMember = copyAnnualMiles(newMember,
                    ((GoldMember) oldMember).getAnnualMiles());
        }

        int index = members.indexOf(oldMember);
        if (index >= 0) {
            members.set(index, newMember);
            System.out.println(newMember.getName()
                    + " upgraded to " + newMember.getTier() + "!");
        }
    }

    private Member copyAnnualMiles(Member member, int miles) {
        if (member instanceof GoldMember) {
            ((GoldMember) member).recordAnnualMiles(miles);
        } else if (member instanceof PlatinumMember) {
            ((PlatinumMember) member).recordAnnualMiles(miles);
        }
        return member;
    }

    public Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public void addReward(Reward reward) {
        rewards.add(reward);
    }

    public boolean redeemReward(Member member, int rewardId) {
        for (Reward reward : rewards) {
            if (reward.getRewardId() == rewardId) {
                boolean success = reward.redeem(member);
                if (success) {
                    totalRedemptions++;
                }
                return success;
            }
        }
        System.out.println("Reward not found.");
        return false;
    }

    public void printStatement(Member member) {
        System.out.println("\n===== LOYALTY STATEMENT =====");
        member.displayDetails();

        System.out.println("Flight Activities:");
        for (FlightActivity activity : activities) {
            activity.displayActivity();
        }

        System.out.println("=============================");
    }

    public void displayTierDistribution() {
        int silver = 0;
        int gold = 0;
        int platinum = 0;

        for (Member member : members) {
            if (member instanceof PlatinumMember) {
                platinum++;
            } else if (member instanceof GoldMember) {
                gold++;
            } else {
                silver++;
            }
        }

        System.out.println("\n===== TIER DISTRIBUTION =====");
        System.out.println("Silver: " + silver);
        System.out.println("Gold: " + gold);
        System.out.println("Platinum: " + platinum);
    }

    public void displayRewards() {
        for (Reward reward : rewards) {
            reward.displayReward();
        }
    }

    public int getTotalMembers() {
        return members.size();
    }

    public int getTotalMilesCredited() {
        return totalMilesCredited;
    }

    public int getTotalRedemptions() {
        return totalRedemptions;
    }
}
