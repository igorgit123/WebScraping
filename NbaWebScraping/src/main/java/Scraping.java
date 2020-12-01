import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Scanner;


public class Scraping {

    public static void main (String args []){

        Scanner in = new Scanner(System.in);

        String igralec=in.nextLine();
        String sUrl=searchUrl(igralec);
        String url=url(sUrl);
        parseHtml(url);



            }

    //Parameter url(profil) igralca, Izpise zeljenje podatke
    public static void parseHtml(String url){
        try{
            Document doc = Jsoup.connect(url).get();

            //Pridobimo blok kode za html tabelo"
            Elements tabela = doc.getElementsByAttributeValue("id", "per_game");

            //Gremo cez vsaki <tr> v tabeli
            for (Element row: tabela.select("tr")){

                //Izlocimo vrstice ki nas ne zanimajo
                if(row.getElementsByAttributeValue("data-stat", "season").text().matches("Season|Career|")||
                        row.getElementsByAttributeValue("data-stat", "season").text().contains("season")) {
                    continue;
                }else{

                    //izpise sezono
                    System.out.print(row.getElementsByAttributeValue("data-stat", "season").text() + "  ");
                    //izpise 3PA za to sezono
                    System.out.println(row.getElementsByAttributeValue("data-stat", "fg3a_per_g").text());
                }
            }

            }catch (Exception e){
                e.printStackTrace();
            }


    }

    //Parameter: Ime in priimek igralca, Return: url search result-a
    public static String searchUrl(String igralec){

        String keyword=igralec.replace(" ", "+");

        String search = "https://www.basketball-reference.com/search/search.fcgi?search="+keyword;

        return search;
    }


    //Parameter: url search resulta, Return: url (profil) igralca
    public static String url(String searchUrl){
        String url="";
        try{
            final Document doc =  Jsoup.connect(searchUrl).get();

            //Pridobimo direktorij url-a igralca primer: /players/d/doncilu01.html

            Elements link = doc.select("div.search-item-name > strong > a");
            url = "https://www.basketball-reference.com"+link.attr("href");


        }catch (Exception e){
            e.printStackTrace();
        }

        return url;
    }
}
