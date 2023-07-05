import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AviaSoulsTest {
    Ticket ticket = new Ticket("Москва",
            "Брянск",
            3000,
            20,
            22);
    Ticket ticket4 = new Ticket("Москва",
            "Брянск",
            2500,
            10,
            13);
    Ticket ticket2 = new Ticket("Москва",
            "Брянск",
            6500,
            15,
            23);

    Ticket ticket3 = new Ticket("Москва",
            "Брянск",
            4000,
            7,
            12);

    Ticket ticket5 = new Ticket("Москва",
            "Брянск",
            4500,
            12,
            17);
@Test                             ///Тест метода compareTo
    public void TicketTestCompareTo() {

        ticket.compareTo(ticket);
        ticket.compareTo(ticket2);

        int expected = -1;
        int actual = ticket.compareTo(ticket2);

        Assertions.assertEquals(expected,actual);
    }
    @Test                             ///Тест метода compareTo
    public void TicketTestCompareTo2() {

        ticket.compareTo(ticket);
        ticket.compareTo(ticket2);

        int expected = 1;
        int actual = ticket2.compareTo(ticket);

        Assertions.assertEquals(expected,actual);
    }
    @Test                             ///Тест метода compareTo
    public void TicketTestCompareTo3() {

        ticket.compareTo(ticket);
        ticket.compareTo(ticket3);

        int expected = -1;
        int actual = ticket.compareTo(ticket3);

        Assertions.assertEquals(expected,actual);
    }
    @Test                            //////////////////Тест метода search c добавлением сортировки по цене////////////////
    public void TicketTestsSearch() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket); //price 3000  "Москва","Брянск"
        aviaSouls.add(ticket2);//price 6500  "Москва","Брянск"
        aviaSouls.add(ticket3);//price 4000  "Москва","Брянск"
        aviaSouls.add(ticket4); //price 2500  "Москва","Брянск"
        aviaSouls.add(ticket5); //price 4500  "Москва","Брянск"

        //Т.к. сортируемые билеты отсортированы по Откуда/куда,
        // то после Arrays.sort(result) следующий параметр для сортировки будет - цена по возрастанию

        Ticket[] expected = {ticket4,ticket,ticket3,ticket5,ticket2};
        Ticket[] actual = aviaSouls.search("Москва","Брянск");

        Assertions.assertArrayEquals(expected,actual);
    }
    @Test                            //////////////////Тест метода compare////////////////
    public void TicketTestsComparator() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket); //time1 = 2
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);//time2 = 2
        aviaSouls.add(ticket5);


        int expected = -1;
        int actual = timeComparator.compare(ticket,ticket4);

        Assertions.assertEquals(expected,actual);
    }

    @Test                            //////////////////Тест метода searchAndSortBy////////////////
    public void TicketTestsComparator2() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket); //time1 = 2
        aviaSouls.add(ticket2);//time2 = 8
        aviaSouls.add(ticket3);//time3 = 5
        aviaSouls.add(ticket4);//time4 = 3
        aviaSouls.add(ticket5);//time5 = 5

        Ticket[] expected = {ticket,ticket4,ticket3,ticket5,ticket2};
        Ticket[] actual = aviaSouls.searchAndSortBy("Москва","Брянск",timeComparator);

        Assertions.assertArrayEquals(expected,actual);
}
}
