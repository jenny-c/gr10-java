
/**
 * A sweet biscuit. 
 * 
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
public class Cookie
{
   
   // instance fields
   private int calorieCount;
   private String description;
   private double weight;
   
   
   // instance methods 
   /**
    * Tests and manipulates objects of this class.
    * 
    * @param argument not used
    */
   public static void main(String[] argument)
   {
       Cookie cookie = new Cookie();
       cookie.setWeight(6.7);
       cookie.setCalorieCount(200);
       cookie.setDescription("Chocolate chip cookie");
       System.out.println("Hello world");
       System.out.println(cookie.getDescription());
   } // end of method main(String[] argument)

   
   // constructor 
   /**
    * Constructs a sweet biscuit with default characteristics.
    */
   public Cookie()
   {
       weight = 0;
       calorieCount = 0;
       description = "";
    } // end of constructor method
    
    
    // accessors
    /**
     * Returns a string representation of this cookie.
     * 
     * @return a string representation of this coookie
     */                                                                                                                                                                                                                                                                                                                                                             
    public String toString()
    {
        return 
        getClass().getName()    
        + "["
        + "weight: " + weight 
        + ", calories: " + calorieCount 
        + ", description: " + description 
        + "]";
    } // end of method toString 
    
    /**
     * Returns the weight value of this cookie.
     * 
     * @return the weight value of this cookie
     */
    public double getWeight()
    {
        return weight;
    } // end of method getWeight 
    
    /**
     * Returns the calorieCount value of this cookie.
     * 
     * @return the calorieCount value of this cookie
     */
    public int getCalorieCount()
    {
        return calorieCount;
    } // end of method getCalorieCount
    
    /**
     * Returns the description of this cookie.
     * 
     * @return the description of this cookie
     */
    public String getDescription()
    {
        return description;
    } // end of method getDescription
    
    
    // mutators
    /**
     * Sets the weight of this cookie.
     * 
     * @param weight weight in grams
     */
    public void setWeight(double weight)
    {
        this.weight = weight;
    } // end of method setWeight
    
    /**
     * Sets the calorie count of this cookie.
     * 
     * @param calorieCount number of calories
     */
    public void setCalorieCount(int calorieCount)
    {
        this.calorieCount = calorieCount;
    } // end of method setCalorieCount
    
    /**
     * Sets the description of this cookie.
     * 
     * @param description description of cookie
     */
    public void setDescription(String description)
    {
        this.description = description;
    } // end of method setDescription
} // end of class Cookie