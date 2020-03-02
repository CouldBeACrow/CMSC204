/**
 * 
 * @author jake
 * Donation Package class
 */
public class DonationPackage {

	String description;
	double weight;
	
	/**
	 * Default constructor
	 * @param
	 * @return
	 */
	public DonationPackage() {
		description = null;
		weight = 0.0;
	}
	
	/**
	 * Constructor
	 * @param description
	 * @param weight
	 */
	public DonationPackage(String description, double weight) {
		this.description = description;
		this.weight = weight;
	}
	
	/**
	 * Get description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**Test if weight is heavy (>= 20)
	 * @return true or false
	 */
	public boolean isHeavy() {
		if (weight >= 20.0) {
			return true;
		}
		return false;
	}
}
