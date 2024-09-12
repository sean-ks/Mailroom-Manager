/**
 * This class represents a package which has a recipient, an arrival date, and a weight
 *
 * @author Sean Shiroma
 *      sean.shiroma@stonybrook.edu
 *      id: 115872064
 *      Recitation 2
 */
public class Package {
    private String recipient; //The name of the recipient
    private int arrivalDate; //The arrival date of the package
    private double weight; //The weight of the package in lbs

    /**
     * This constructor creates an instance of a Package object
     * @param recipient
     * The name of the recipient of the package
     * @param arrivalDate
     * The arrival date of the package
     * @param weight
     * the weight of the package in lbs
     *
     * Post-Conditions:
     * This object has been initialized to a package object with specified recipient, arrival date and weight.
     */
    public Package(String recipient, int arrivalDate, double weight){
        this.recipient = recipient;
        this.arrivalDate = arrivalDate;
        this.weight = weight;
    }

    /**
     * this method gets the name of the recipient of the package
     *
     * @return
     * recipient
     */
    public String getRecipient(){
        return recipient;
    }

    /**
     * This method gets the arrival date of the package
     *
     * @return
     * arrival date
     */
    public int getArrivalDate(){
        return arrivalDate;
    }

    /**
     * This method gets the weight of the package in lbs
     *
     * @return
     * weight in lbs of package
     */
    public double getWeight() {
        return weight;
    }

    /**
     * This method sets the name of the recipient
     *
     * @param recipient
     * The name of the recipient to set
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * This method sets the arrival date of the package
     *
     * @param arrivalDate
     * arrival date to be set
     */
    public void setArrivalDate(int arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * This method sets the weight of the package in lbs
     *
     * @param weight
     * weight to be set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * This method gives a string representation of the package object
     *
     * @return
     * A string representation of the package object
     */
    public String toString(){
        return "["+recipient+ " " + arrivalDate + "]";
    }
}
