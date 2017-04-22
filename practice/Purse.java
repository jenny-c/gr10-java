/**
 * Write a description of class Purse here.
 *
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
public class Purse
{
   // static fields
   private int dimes;
   private int nickels;
   private int quarters;
   private int[] coin = new int[3];


   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument)
   {

   } // end of method main(String[] argument)


   /**
   * Constructs an instance of this purse with default values.
   */
   public Purse()
   {
     dimes = 0;
     nickels = 0;
     quarters = 0;
     coin = new int[] {dimes, nickels, quarters};
   } // end of constructor Purse()

   /**
   * Constructs an instance of this purse with give values.
   */
   public Purse(int dimes, int nickels, int quarters)
   {
     this.dimes = dimes;
     this.nickels = nickels;
     this.quarters = quarters;
     coin = new int[] {dimes, nickels, quarters};
   } // end of constructor Purse(int dimes, int nickels, int quarters)


   public String coinMatch(int numberOfCoins)
   {
     String matchingCoins = "";

     if (numberOfCoins == dimes)
     {
       matchingCoins = "dimes";
     }
     if (numberOfCoins == nickels)
     {
       if (matchingCoins.equals(""))
       {
        matchingCoins = "nickels";
       }
       else
       {
         matchingCoins = matchingCoins + ", nickels";
       }
     }
     if (numberOfCoins == quarters)
     {
       if (matchingCoins.equals(""))
       {
        matchingCoins = "quarters";
       }
       else
       {
         matchingCoins = matchingCoins + ", quarters";
       }
     }
     return matchingCoins;
   } // end of method coinMatch(int numberOfCoins)


   public String coinMost()
   {
     // set default values
     String mostCoins = "dimes";
     int currentMostCoin = dimes;

     // change type of highest number of coins if necessary
     if (nickels > currentMostCoin)
     {
       currentMostCoin = nickels;
       mostCoins = "nickels";
     }
     else if (nickels == currentMostCoin)
     {
       mostCoins = mostCoins + ", nickels";
     } // end of if (nickels > currentMostCoin)
     if (quarters > currentMostCoin)
     {
       currentMostCoin = quarters;
       mostCoins = "quarters";
     }
     else if (quarters == currentMostCoin)
     {
       mostCoins = mostCoins + ", quarters";
     } // end of if (quarters > currentMostCoin)

     return mostCoins;
   } // end of method coinMost()


   public String coinLeast()
   {
     // set default values
     String leastCoins = "dimes";
     int currentLeastCoin = dimes;

     // change type of lowest number of coins if necessary
     if (nickels < currentLeastCoin)
     {
       currentLeastCoin = nickels;
       leastCoins = "nickels";
     }
     else if (nickels == currentLeastCoin)
     {
       leastCoins = leastCoins + ", nickels";
     } // end of if (nickels < currentLeastCoin)
     if (quarters < currentLeastCoin)
     {
       currentLeastCoin = quarters;
       leastCoins = "quarters";
     }
     else if (quarters == currentLeastCoin)
     {
       leastCoins = leastCoins + ", quarters";
     } // end of if (quarters < currentLeastCoin)

     return leastCoins;
   } // end of method coinLeast()

} // end of class Purse
