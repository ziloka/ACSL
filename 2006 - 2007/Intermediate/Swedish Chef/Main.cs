// sudo apt-get update && sudo apt-get install mono-complete && sudo apt-get upgrade -y
// mcs -out:Main.exe Main.cs && mono Main.exe

using System;
using System.Text.RegularExpressions;

namespace ACSL {
    class MainClass {

        public static String getResult(String english) {
            english = english.ToLower();
            english = Regex.Replace(english, @"(?<=\W)$", " BORK BORK BORK!");
            english = Regex.Replace(english, "the", "ZEE", RegexOptions.Multiline);
            english = Regex.Replace(english, "an", "UN", RegexOptions.Multiline);
            english = Regex.Replace(english, "au", "OO", RegexOptions.Multiline);
            english = Regex.Replace(english, @"a(?!\b)", "E", RegexOptions.Multiline);
            english = Regex.Replace(english, @"ow", "OO", RegexOptions.Multiline);
            english = Regex.Replace(english, @"o", "U", RegexOptions.Multiline);
            english = Regex.Replace(english, @"ir", "UR", RegexOptions.Multiline);
            english = Regex.Replace(english, @"tion", "SHUN", RegexOptions.Multiline);
            english = Regex.Replace(english, @"(?<=\s[^i]+)i", "EE", RegexOptions.Multiline);
            english = Regex.Replace(english, @"en\b", "EE", RegexOptions.Multiline);
            english = Regex.Replace(english, @"f", " FF", RegexOptions.Multiline);
            english = Regex.Replace(english, @"e\b", "E-A", RegexOptions.Multiline);
            english = Regex.Replace(english, @"(?!\bu)u", "OO", RegexOptions.Multiline);
            english = Regex.Replace(english, @"v", "F", RegexOptions.Multiline);
            english = Regex.Replace(english, @"w", "V", RegexOptions.Multiline);
            english = english.ToUpper();
            return english;
        }

        public static void Main (string[] args) {
            string[] lines = System.IO.File.ReadAllLines(@"./Test.in");

            // Display the file contents by using a foreach loop.
            foreach (string line in lines) {
                Console.WriteLine("INPUT: " + line);
                Console.WriteLine("Output: " + getResult(line) + "\n");
            }
        }
    }
}
