
import java.io.*;
import java.util.Scanner;

public class PhoneBookEntry {

    private PhoneBookEntry[] book;
    private int cur_entry;

    public PhoneBookEntry() {
        book = new PhoneBookEntry[10];
        cur_entry = 0;
    }

    public boolean full() {
        return (cur_entry == 10);
    }

    public boolean add(PhoneBookEntry e) {
        if (cur_entry == 10) return false;

        book[cur_entry] = e;
        cur_entry++;
        return true;
    }

    public void printBook() {

        System.out.println("Вот все текущие записи: ");
        for (int i=0; i<cur_entry; i++)
            System.out.println(i+". "+book[i]);
    }

    public void search(String lastname) {

        for (int i=0; i<cur_entry; i++)
            if (book[i].sameLastName(lastname))
                System.out.println(i+". "+book[i]);
    }

    private boolean sameLastName(String lastname) {
        return false;
    }

    public int searchNumMatches(String lastname) {

        int cnt = 0;
        for (int i=0; i<cur_entry; i++)
            if (book[i].sameLastName(lastname))
                cnt++;
        return cnt;
    }

    public int getIndex(String lastname) {

        for (int i=0; i<cur_entry; i++)
            if (book[i].sameLastName(lastname))
                return i;
        return -1;
    }

    public void changeLastName(int index, String newlastname) {
        book[index].changeLastName(index, newlastname);
    }

    public void changeNumber(int index, int newnum) {
        book[index].changeNumber(newnum, newnum);
    }

    public static void menu() {

        System.out.println("Выберите действие: ");
        System.out.println("1. Добавить запись в телефонную книгу. ");
        System.out.println("2. Распечатать все записи телефонной книги. ");
        System.out.println("3. Поиск записи. ");
        System.out.println("4. Изменить записанную фамилию. ");
        System.out.println("5. Изменить записанный номер телефона. ");
        System.out.println("6. Выход. ");
    }

    public void doChangeName(Scanner stdin) throws IOException {

        System.out.println("Какую фамилию вы хотите изменить? ");
        String last = stdin.next();
        int nummatches = searchNumMatches(last);

        if (nummatches == 0)
            System.out.println("Извините, нет записей, соответствующих этой фамилии. ");

        else if (nummatches == 1) {
            System.out.println("Совпала ровно одна запись: ");
            search(last);

            System.out.println("Какая новая фамилия? ");
            String newln = stdin.next();
            changeLastName(getIndex(last), newln);
        }

        else {
            System.out.println("Вот совпадающие записи: ");
            search(last);

            System.out.println("Введите номер записи, которую вы хотите изменить. ");
            int changei = stdin.nextInt();

            System.out.println("Какая новая фамилия? ");
            String newln = stdin.next();
            changeLastName(changei, newln);
        }
    }

    public void doChangeNumber(Scanner stdin) throws IOException {

        System.out.println("Какая фамилия у номера, который вы хотите изменить? ");
        String last = stdin.next();
        int nummatches = searchNumMatches(last);

        if (nummatches == 0)
            System.out.println("Извините, нет записей, соответствующих этой фамилии. ");

        else if (nummatches == 1) {
            System.out.println("Совпала ровно одна запись: ");
            search(last);

            System.out.println("Какой номер телефона? ");
            int num = stdin.nextInt();
            changeNumber(getIndex(last), num);
        }

        else {
            System.out.println("Вот совпадающие записи: ");
            search(last);
            System.out.println("Введите номер записи, которую вы хотите изменить. ");
            int changei = stdin.nextInt();

            System.out.println("Какой новый номер телефона? ");
            int num = stdin.nextInt();
            changeNumber(changei, num);
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner stdin = new Scanner(System.in);
        PhoneBookEntry book = new PhoneBookEntry();

        menu();
        int ans = stdin.nextInt();
        while (ans != 6) {
            if (ans == 1) {
                if (book.full())
                    System.out.print("Извините, телефонная книга заполнена, запись не может быть добавлена. ");
                else {
                    PhoneBookEntry e = PhoneBookEntry.getEntry();
                    book.add(e);
                    System.out.println("Запись была добавлена. ");
                }
            }

            else if (ans == 2)
                book.printBook();

            else if (ans == 3) {
                System.out.println("Какая фамилия для вашего поиска? ");
                String last = stdin.next();
                if (book.searchNumMatches(last) > 0) {
                    System.out.println("Вот записи, соответствующие вашему запросу. ");
                    book.search(last);
                }
                else
                    System.out.println("Извините, по вашему запросу ничего не найдено. ");
            }

            else if (ans == 4)
                book.doChangeName(stdin);

            else if (ans == 5)
                book.doChangeNumber(stdin);

            else if (ans != 6)
                System.out.println("Извините, это неверный выбор меню.\n");

            menu();
            ans = stdin.nextInt();
        }
    }

    private static PhoneBookEntry getEntry() {
        return null;
    }
}
