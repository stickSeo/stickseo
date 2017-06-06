package Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author LeeYongil
 */
public class FileRead extends PhoneBook{
    
	public static void readTXT() throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("test.txt"));

		String view = null;

		String line1 = in.readLine();

		line = Integer.parseInt(line1);

		line /= 3;

		int i = 1, na = 0, te = 0, ma = 0;

		while (i <= (line * 3) + 1) {

			view = in.readLine();

			if (view == null)

				break;

			if (i % 3 == 1) {

				name[na] = view;

				na++;

			} else if (i % 3 == 2) {

				tel[te] = view;

				te++;

			} else if (i % 3 == 0) {

				mail[ma] = view;

				ma++;

			}

			i++;

		}// end while

		in.close();

	}// end readMethod

}
