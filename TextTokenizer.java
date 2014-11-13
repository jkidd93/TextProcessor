import java.io.*;
import java.util.Scanner;

/*
	The TextTokenizer tokenizes the text as described in the Processor class
*/
public class TextTokenizer
{
	Scanner fileScan;

	public TextTokenizer(Scanner scan)
	{
		fileScan = scan;
	}

	//Tokenizes the text
	public void tokenize()
	{
		String word;
		String text = "";
		System.out.println("\nTokenizing the text...\n");

		/*
			Replaces the punctuation with spaces, lowercases all letters, removes period
		 	from abbreviations
		*/
		while(fileScan.hasNext())
		{
			word = fileScan.next();

			if(word.contains("."))
			{
				//Words with less than 2 periods in them are probably not abbreviations
				if(countChar(word, '.') < 2)
				{
					word = word.replace(".", " ");
				}
				else
				{
					word = word.replace(".", "");
				}	
			}
			if(word.contains("'"))
			{
				word = word.replace("'", " ");
			}
			if(word.contains("\""))
			{
				word = word.replace("\"", " ");
			}
			if(word.contains(","))
			{
				word = word.replace(","," ");
			}
			if(word.contains("("))
			{
				word = word.replace("("," ");
			}
			if(word.contains(")"))
			{
				word = word.replace(")"," ");
			}
			if(word.contains(":"))
			{
				word = word.replace(":"," ");
			}
			if(word.contains("/"))
			{
				word = word.replace("/"," ");
			}
			if(word.contains("_"))
			{
				word = word.replace("_"," ");
			}
			if(word.matches(".*[A-Z].*"))
			{
				word = word.toLowerCase();
			}

			text += word + " ";
		}

		//Writes the ouput to a new file
		try
		{
			String fileName = "tokenizedText.txt";
			File file = new File(fileName);
			FileWriter fWriter = new FileWriter(file);
			BufferedWriter buffWriter = new BufferedWriter(fWriter);
			buffWriter.write(text);
			buffWriter.close();

			System.out.println("Output after tokenizing was written to file: " + fileName);
		}
		catch (java.io.IOException e)
		{
			System.out.println("Problem writing tokenized text to file");
		}
	}

	//Counts the number of occurences of a particular character in a word
	public int countChar(String word, char letter)
	{
		int count = 0;

		for(int i = 0; i < word.length(); i++)
		{
			if(word.charAt(i) == letter)
			{
				count++;
			}
		}

		return count;
	}
} 