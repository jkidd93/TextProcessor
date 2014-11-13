import java.io.*;
import java.util.Scanner;

/*
	This class drives the textual processing of an input file. 
	1. The text is tokenized meaning: all abbreviations become strings without periods, the
		rest of the punctuation replaced with a space, all letters are lowercased
	2. The output from the previous step is now the input file for this step.
		The stop words listed from the inquery file are detected and removed from the text
	3. The output from the previous step is now the input file for this step. The text is
		processed through the Porter Stemmer.
*/
public class Processor
{
	public static void main(String [] args)throws FileNotFoundException, IOException
	{
		if(args.length != 1)
		{
			System.err.println("Incorrect argument format.\nFormat: java Processor textfile.txt\n");
			System.exit(1);
		}

		//Names of the files used
		String tokenizedFile = "tokenizedText.txt";
		String removedStops = "removedStops.txt";

		//File and scanner for tokenizer
		FileInputStream file = new FileInputStream(args[0]);
		Scanner fileScan = new Scanner(file);

		TextTokenizer tokenizer = new TextTokenizer(fileScan);
		
		//Runs tokenizer
		tokenizer.tokenize();

		//File for and scanner for stop word removal
		FileInputStream file2 = new FileInputStream(tokenizedFile);
		Scanner fileScan2 = new Scanner(file2);

		StopWordRemoval stopRemover = new StopWordRemoval(fileScan2);

		//Runs stop word removal
		stopRemover.removeStops();


		//Runs the Porter Stemmer
		char[] w = new char[501];
      Stemmer s = new Stemmer();
      for (int i = 0; i < args.length; i++)   
      try
      {
         FileInputStream in = new FileInputStream(removedStops);
         System.out.println("________________________________");
         System.out.println("Final Output After Stemming");
         System.out.println("________________________________\n");
         try
         { while(true)

           {  int ch = in.read();
              if (Character.isLetter((char) ch))
              {
                 int j = 0;
                 while(true)
                 {  ch = Character.toLowerCase((char) ch);
                    w[j] = (char) ch;
                    if (j < 500) j++;
                    ch = in.read();
                    if (!Character.isLetter((char) ch))
                    {
                       
                       for (int c = 0; c < j; c++) s.add(w[c]);

                       s.stem();
                       {  String u;

                          u = s.toString();
                          System.out.print(u);
                       }
                       break;
                    }
                 }
              }
              if (ch < 0) break;
              System.out.print((char)ch);
           }
         }
         catch (IOException e)
         {  System.out.println("error reading " + args[i]);
            break;
         }
      }
      catch (FileNotFoundException e)
      {  System.out.println("file " + tokenizedFile + " not found");
         break;
      }
		
	}
}