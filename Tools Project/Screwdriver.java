
/**
 * A tool used to manipulate screws.
 * 
 * @author Jenny Chen
 * @version 1.0 2017-02-21
 */
public class Screwdriver
{
   
    // instance fields
    private boolean magnetic;
    private double size;
    private String type;
    
    
   //instance methods
   /**
    * Tests and manipulates objects of this class.
    *
    * @param argument not used
    */
   public static void main(String[] argument)
   {
      Screwdriver myScrewdriver = new Screwdriver("philips", 4.5, true);
      System.out.println(myScrewdriver.toString());
    } // end of method main(String[] argument)

   
   // constructors
   /**
    * Constructs a screwdriver with default characteristics.
    */
   public Screwdriver()
   {
       type = "";
       size = 0;
       magnetic = false;
    } // end of constructor method Screwdriver()
   
   
   /**
    * Constructs a screwdriver with a specified type.
    * 
    * @param type type of head
    */
   public Screwdriver(String type)
   {
       this.type = type;
       size = 0;
       magnetic = false;
    } // end of constructor method Screwdriver(String type)
    
    /**
     * Constructs a screwdriver with a specified size.
     * 
     * @param size diameter of head
     */
    public Screwdriver(double size)
    {
        type = "";
        this.size = size;
        magnetic = false;
    } // end of constructor method Screwdriver(double size)
    
    /**
     * Constructs a screwdriver with a specified magnetism.
     * 
     * @param magnetic magnetism of head
     */
    public Screwdriver(boolean magnetic)
    {
        type = "";
        size = 0;
        this.magnetic = magnetic;
    } // end of constructor method Screwdriver(boolean magnetic)
    
    /**
     * Constructs a screwdriver with specified values.
     * 
     * @param type type of head
     * @param size size of head
     * @param magetic magnetism of head
     */
    public Screwdriver(String type, double size, boolean magnetic)
    {
        this.type = type;
        this.size = size;
        this.magnetic = magnetic;
    } // end of constructor method Screwdriver(String type, double size, boolean magnetic)
    
    
    // accessors 
    /**
     * Returns a string representation of this screwdriver.
     * 
     * @return a string representation of this screwdriver
     */
    public String toString()
    {
        return 
        getClass().getName()
        + "["
        + "type: " + type
        + ", size: " + size
        + ", magnetism: " + magnetic
        + "]";
    } // end of accessor method toString
    
    /**
     * Returns the type of this screwdriver.
     * 
     * @return the type of this screwdriver
     */
    public String getType()
    {
        return type;
    } // end of accessor method getType()
    
    /**
     * Returns the size of this screwdriver.
     * 
     * @return the size of this screwdriver
     */
    public double getSize()
    {
        return size;
    } // end of accessor method getSize()
    
    /**
     * Returns the magnetism of this screwdriver. 
     * 
     * @return the magnetism of this screwdriver 
     */
    public boolean isMagnetic()
    {
        return magnetic;
    } // end of accessor method isMagnetic()
    
    
    // mutators
    /**
     * Sets the type of this screwdriver.
     * 
     * @param type type of head
     */
    public void setType(String type)
    {
        this.type = type;
    } // end of mutator method setType(String type)
    
    /**
     * Sets the size of this screwdriver.
     * 
     * @param size diameter of head in centimetres 
     */
    public void setSize(double size)
    {
        if (size > 0)
        {
            this.size = size;
        } // end of if (size > 0)
    } // end of mutator method setSize(double size)
    
    /**
     * Sets the magnetism of this screwdriver.
     * 
     * @param magnetic magnetism of head
     */
    public void setMagnetism(boolean magnetic)
    {
        this.magnetic = magnetic;
    } // end of mutator method setMagnetism(boolean magnetic)
    
} // end of class Screwdriver