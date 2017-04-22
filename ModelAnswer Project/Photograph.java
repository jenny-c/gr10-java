
/**
 * A picture made from a camera.
 * 
 * @author Jenny Chen
 * @version 1.0 2017-02-27
 */
public class Photograph
{
    
   // instance fields
   private boolean blackAndWhite;
   private String picture;
   private int size;
   
   
   //constructors
   /**
    * Constucts a photograph with specified characteristics.
    * 
    * @param size size selection of this photograph
    * @param picture picture in this photograph 
    * @param blackAndWhite whether this photograph is black and white
    */
   public Photograph(int size, String picture, boolean blackAndWhite)
   {
       this.size = size;
       this.picture = picture;
       this.blackAndWhite = blackAndWhite;
    } // end of constructor method Photograph(int size, String picture, boolean blackAndWhite)
    
    
   //accessors
   /**
    * Returns a string representation of this photograph.
    * 
    * @return a string a representation of this photograph
    */
   public String toString()
   {
      return
      getClass().getName()
      + "["
      + "Size: " + size
      + ", Picture: " + picture
      + ", Is in black and white: " + blackAndWhite
      + "]";
    } // end of accessor method toString()
     
     
   //mutators
   /**
    * Sets the size of this photograph.
    * 
    * @param size size selection of this photograph
    */
   public void setSize(int size)
   {
      if (size > 0 && size < 4)
      {
          this.size = size;
       } // end of if (size > 0 && size < 4)
    } // end of mutator method setSize(size)
    
    
   /**
    * Tests and maniupulates objects of this class.
    *
    * @param argument not used
    */
   public static void main(String[] argument)
   {
      
   } // end of method main(String[] argument)

} // end of class Photograph