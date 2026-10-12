
package airline;

public class FlightActivity {
    private int activityId;
    private String flightNo;
    private String date;
    private int distance;
    private String fareClass;
    private int milesEarned;

    public FlightActivity(int activityId, String flightNo, String date,
                          int distance, String fareClass) {
        this.activityId = activityId;
        this.flightNo = flightNo;
        this.date = date;
        this.distance = distance;
        this.fareClass = fareClass;
        this.milesEarned = 0;
    }

    public int getActivityId() {
        return activityId;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getDate() {
        return date;
    }

    public int getDistance() {
        return distance;
    }

    public String getFareClass() {
        return fareClass;
    }

    public int getMilesEarned() {
        return milesEarned;
    }

    public void calculateMiles() {
        double multiplier = 1.0;

        if (fareClass.equalsIgnoreCase("Business")) {
            multiplier = 1.5;
        } else if (fareClass.equalsIgnoreCase("First")) {
            multiplier = 2.0;
        }

        milesEarned = (int) (distance * multiplier);
    }

    public void setMilesEarned(int miles) {
        if (miles >= 0) {
            milesEarned = miles;
        }
    }

    public void displayActivity() {
        System.out.println("Activity ID: " + activityId);
        System.out.println("Flight No: " + flightNo);
        System.out.println("Date: " + date);
        System.out.println("Distance: " + distance);
        System.out.println("Fare Class: " + fareClass);
        System.out.println("Miles Earned: " + milesEarned);
    }
}
