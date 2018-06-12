package moe.hilaryoi.mc;

public class Parser {

	protected StringBuilder buffer;

	int index;
	char[] chars;

	public Parser () { buffer = new StringBuilder (); }

	public Parser (String text) { this (); loadText (text); }

	public void loadText (String text) { index = 0; chars = text.toCharArray (); }

	public void continueUntil (char c) { continueUntil (c, false);}

	public void continueUntil (char c, boolean loadLastChar) {

		try {

			while (getCurrChar () != c) {

				loadChar ();
				index++;

			}

			if (loadLastChar) loadChar ();

			index++;

		}

		catch (ArrayIndexOutOfBoundsException e) {

			System.err.println (String.format ("Reached end of buffer without finding character `%s`. Current buffer: `%s`", c, buffer.toString ()));
			System.exit (1);

		}

	}

	public void loadChar () { buffer.append (getCurrChar ()); }

	protected char getCurrChar () { return chars[index]; }

	// returns contents of buffer and clears it
	public String scrape () { String text = buffer.toString (); clearBuffer (); return text; }

	public boolean isFinished () { return index >= chars.length; }

	public void clearBuffer () { buffer.setLength (0); }


}