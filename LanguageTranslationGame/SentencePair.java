import java.util.HashMap;
/**
 * Holds two sentences, one in French and one in English.
 *
 * @author Jenny Chen
 * @version 1.0 2017-05-03
 */
public class SentencePair
{
  // instance fields
  private String englishSentence;
  private String frenchSentence;
  private boolean correct;
  private static int count = 0;

   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument)
   {

   } // end of method main(String[] argument)

   /* constructors */

   /**
    * Constructs a pair of sentences with specified values.
    */
    public SentencePair(String englishSentence, String frenchSentence)
    {
      this.englishSentence = englishSentence;
      this.frenchSentence = frenchSentence;
      correct = false;
    } // end of constructor SentencePair(String, String)

    /* accessors */

    /**
     * Returns a string representation of this sentence pair.
     *
     * @return a string representation of this sentence pair
    */
    public String toString()
    {
      return
      getClass().getName()
      + "["
      + "english sentence: " + englishSentence
      + "french sentence: " + frenchSentence
      + "correct: " + correct
      + "]";
    } // end of method toString()

    /**
     * Returns the englishSentence value of this sentence pair.
     *
     * @return the englishSentence value of this sentence pair
    */
    public String getEnglishSentence()
    {
      return englishSentence;
    } // end of method getEnglishSentence()

    /**
     * Returns the frenchSentence value of this sentence pair.
     *
     * @return the frenchSentence value of this sentence pair
    */
    public String getFrenchSentence()
    {
     return frenchSentence;
   } // end of method getFrenchSentence()

    /**
     * Returns the correct value of this sentence pair.
     *
     * @return the correct value of this sentence pair
    */
    public boolean isCorrect()
    {
      return correct;
    } // end of method isCorrect()

    /* mutators */

    /**
     * Sets the englishSentence value of this sentence pair.
     *
     * @param englishSentence english sentence in pair
    */
    public void setEnglishSentence(String englishSentence)
    {
      this.englishSentence = englishSentence;
    } // end of method setEnglishSentence(String)

    /**
     * Sets the frenchSentence value of this sentence pair.
     *
     * @param frenchSentence french sentence in pair
    */
    public void setFrenchSentence(String frenchSentence)
    {
      this.frenchSentence = frenchSentence;
    } // end of method setFrenchSentence(String)

    /**
     * Sets the correct value of this sentence pair.
     *
     * @param correct correctness of answer
    */
    public void setCorrectness(boolean correct)
    {
      this.correct = correct;
    } // end of method setCorrectness(boolean)
} // end of class SentencePair
