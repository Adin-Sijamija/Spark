package e.adin_pc.spark;

/**
 * Created by Adin-PC on 3/29/2018.
 */

public class Flights {
            int id;
            String start_destination;
            String end_destination;
            String flight_start_time;
            String Flight_end_time;
            Float Price;


     Flights(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setStart_destination(String start_destination) {
        this.start_destination = start_destination;
    }

    public void setEnd_destination(String end_destination) {
        this.end_destination = end_destination;
    }

    public void setFlight_start_time(String flight_start_time) {
        this.flight_start_time = flight_start_time;
    }

    public void setFlight_end_time(String flight_end_time) {
        Flight_end_time = flight_end_time;
    }

    public void setPrice(Float price) {
        Price = price;
    }

    public int getId() {

        return id;
    }

    public String getStart_destination() {
        return start_destination;
    }

    public String getEnd_destination() {
        return end_destination;
    }

    public String getFlight_start_time() {
        return flight_start_time;
    }

    public String getFlight_end_time() {
        return Flight_end_time;
    }

    public Float getPrice() {
        return Price;
    }

    public Flights(int id, String start_destination, String end_destination, String flight_start_time, String flight_end_time, Float price) {

        this.id = id;
        this.start_destination = start_destination;
        this.end_destination = end_destination;
        this.flight_start_time = flight_start_time;
        Flight_end_time = flight_end_time;
        Price = price;
    }
}
