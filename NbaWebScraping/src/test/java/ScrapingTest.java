import static org.junit.jupiter.api.Assertions.*;

class ScrapingTest {

    @org.junit.jupiter.api.Test
    void searchUrl() {
        Scraping s=new Scraping();
        assertEquals("https://www.basketball-reference.com/search/search.fcgi?search=luka+doncic",
                s.searchUrl("luka doncic"));

    }

    @org.junit.jupiter.api.Test
    void url() {
        Scraping s=new Scraping();

        assertEquals("https://www.basketball-reference.com/players/d/doncilu01.html",
                s.url("https://www.basketball-reference.com/search/search.fcgi?search=luka+doncic") );
    }
}