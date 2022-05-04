package CCT;

import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Browser {
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";

    public static void Search() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search: ");
        String searchTerm = scanner.nextLine();
        System.out.println("Number of searches:");
        int num = scanner.nextInt();
        scanner.close();
        String searchURL = GOOGLE_SEARCH_URL + "?q=" + searchTerm + "&num=" + num;
        Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();
        Elements results = doc.select("h3.r > a");

        for (Element result : results) {
            String linkHref = result.attr("href");
            String linkText = result.text();
            System.out.println("TEXT::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
        }
    }
}
