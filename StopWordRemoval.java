import java.util.Scanner;
import java.io.*;

/*
	This class removes the stop words from the text based on the list of stop words from
	the inquery file
*/
public class StopWordRemoval
{
	Scanner fileScan;

	public StopWordRemoval(Scanner scan)
	{
		fileScan = scan;
	}

	//Removes the stop words and outputs the resulting text to a file
	public void removeStops()throws FileNotFoundException, IOException
	{
		String inqueryFile = "inquery";
		String text = "";
		String line = "";
		String word = "";
		String removedStops = "";

		FileReader fReader = new FileReader(inqueryFile);
		BufferedReader buffReader = new BufferedReader(fReader);

		//Concatenates the words from the inquery list into one string for easier comparing
		while((line = buffReader.readLine()) != null)
		{
			text += line + "\n";
		}

		System.out.println("\nRemoving stop words from text...\n");

		while(fileScan.hasNext())
		{
			word = fileScan.next();

			//If a stop word in the input file is found from the concatenated string of 
			//stop words, it will be skipped over
			if(text.contains(word))
			{
				//do nothing... just skip them
			}
			//If the word is not a stop word, then continue 
			//creating a string without any stopwords
			else
			{
				removedStops += word + " ";
			}
		}

		//Write the output to a text file
		try
		{
			String fileName = "removedStops.txt";
			FileWriter fWriter = new FileWriter(fileName);
			BufferedWriter buffWriter = new BufferedWriter(fWriter);
			buffWriter.write(removedStops);
			buffWriter.close();

			System.out.println("\nOutput after stop words have been removed was written to file : " + fileName + "\n");
		}
		catch(java.io.IOException e)
		{
			System.out.println("Problem writing text to file");
		}
	}
}