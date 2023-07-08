import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AviaSoulsTest {
    Ticket ticket = new Ticket("Москва", "Брянск", 3000, 20, 22);
    Ticket ticket4 = new Ticket("Москва", "Брянск", 2500, 10, 13);
    Ticket ticket2 = new Ticket("Москва", "Брянск", 6500, 15, 23);
    Ticket ticket3 = new Ticket("Москва", "Брянск", 4000, 7, 12);
    Ticket ticket5 = new Ticket("Москва", "Брянск", 4500, 12, 17);
    Ticket ticket6 = new Ticket("Москва", "Саранск", 4500, 18, 23);
    Ticket ticket7 = new Ticket("Москва", "Сочи", 6700, 18, 23);
    Ticket ticket8 = new Ticket("Астрахань", "Брянск", 5800, 18, 23);

    @Test                             ///Тест метода compareTo. prise1 < prise2
    public void TicketTestCompareTo() {

        ticket.compareTo(ticket);
        ticket.compareTo(ticket3);

        int expected = -1;
        int actual = ticket.compareTo(ticket3);

        Assertions.assertEquals(expected, actual);
    }

    @Test                             ///Тест метода compareTo. prise1 > prise2
    public void TicketTestCompareTo2() {

        ticket.compareTo(ticket2);
        ticket.compareTo(ticket);

        int expected = 1;
        int actual = ticket2.compareTo(ticket);

        Assertions.assertEquals(expected, actual);
    }

    @Test                             ///Тест метода compareTo. prise1 = prise2
    public void TicketTestCompareTo3() {

        ticket.compareTo(ticket5);
        ticket.compareTo(ticket6);

        int expected = 0;
        int actual = ticket5.compareTo(ticket6);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    //////////////////Тест метода search c добавлением сортировки по цене////////////////
                                 //множество совпадений//
    public void TicketTestsSearch() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket); //price 3000  "Москва","Брянск"
        aviaSouls.add(ticket2);//price 6500  "Москва","Брянск"
        aviaSouls.add(ticket3);//price 4000  "Москва","Брянск"
        aviaSouls.add(ticket4); //price 2500  "Москва","Брянск"
        aviaSouls.add(ticket5); //price 4500  "Москва","Брянск"

        //Т.к. сортируемые билеты отсортированы по Откуда/куда,
        // то после Arrays.sort(result) следующий параметр для сортировки будет - цена по возрастанию

        Ticket[] expected = {ticket4, ticket, ticket3, ticket5, ticket2};
        Ticket[] actual = aviaSouls.search("Москва", "Брянск");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    //////////////////Тест метода search c добавлением сортировки по цене////////////////
                                   //только 1 севпадение//
    public void TicketTestsSearch2() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket5); //price 4000  "Москва","Брянск"
        aviaSouls.add(ticket6);//price 4500  "Москва","Саранск"
        aviaSouls.add(ticket7);//price 6000  "Москва","Сочи"


        //Т.к. сортируемые билеты отсортированы по Откуда/куда,
        // то после Arrays.sort(result) следующий параметр для сортировки будет - цена по возрастанию

        Ticket[] expected = {ticket5};
        Ticket[] actual = aviaSouls.search("Москва", "Брянск");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    //////////////////Тест метода search c добавлением сортировки по цене////////////////
                                     //НЕТ совпадений//
    public void TicketTestsSearch3() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket6); //price 4500  "Москва","Саранск"
        aviaSouls.add(ticket7);//price 6700  "Москва","Сочи"
        aviaSouls.add(ticket8);//price 5800  "Астрахань","Брянск"


        //Т.к. сортируемые билеты отсортированы по Откуда/куда,
        // то после Arrays.sort(result) следующий параметр для сортировки будет - цена по возрастанию

        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.search("Москва", "Брянск");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test                            //////////////////Тест метода compare////////////////
                                                 //time1<time2//
    public void TicketTestsComparator() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket); //time1 = 2
        aviaSouls.add(ticket4);//time2 = 3

        int expected = -1;
        int actual = timeComparator.compare(ticket, ticket4);

        Assertions.assertEquals(expected, actual);
    }

    @Test                            //////////////////Тест метода compare////////////////
                                                  //time1>time2//
    public void TicketTestsComparator2() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket2); //time1 = 8
        aviaSouls.add(ticket4); //time2 = 3

        int expected = 1;
        int actual = timeComparator.compare(ticket2, ticket4);

        Assertions.assertEquals(expected, actual);
    }

    @Test                            //////////////////Тест метода compare////////////////
                                                  // time1=time2//
    public void TicketTestsComparator3() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket6); //time1 = 5
        aviaSouls.add(ticket7); //time2 = 5

        int expected = 0;
        int actual = timeComparator.compare(ticket6, ticket7);

        Assertions.assertEquals(expected, actual);
    }


    @Test                            //////////////////Тест метода searchAndSortBy////////////////
                                // множество совпадений по from/to + сортировка по возрастанию времени//
    public void SearchAndSortByTestsComparator() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket); //time1 = 2
        aviaSouls.add(ticket2);//time2 = 8
        aviaSouls.add(ticket3);//time3 = 5
        aviaSouls.add(ticket4);//time4 = 3
        aviaSouls.add(ticket5);//time5 = 5

        Ticket[] expected = {ticket, ticket4, ticket3, ticket5, ticket2};
        Ticket[] actual = aviaSouls.searchAndSortBy("Москва", "Брянск", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test                            //////////////////Тест метода searchAndSortBy////////////////
                                                     //1 совпадение по from/to //
    public void SearchAndSortByTestsComparator2() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket5); //time1 = 5
        aviaSouls.add(ticket6);//time2 = 5
        aviaSouls.add(ticket7);//time3 = 5
        aviaSouls.add(ticket8);//time4 = 5


        Ticket[] expected = {ticket5};
        Ticket[] actual = aviaSouls.searchAndSortBy("Москва", "Брянск", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test                            //////////////////Тест метода searchAndSortBy////////////////
                                                  //НЕТ совпадение по from/to //
    public void SearchAndSortByTestsComparator3() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket6);//time2 = 5
        aviaSouls.add(ticket7);//time3 = 5
        aviaSouls.add(ticket8);//time4 = 5


        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.searchAndSortBy("Москва", "Брянск", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}
