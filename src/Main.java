import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Toy> Won_Toys = new ArrayList<>();
        ArrayList<Toy> my_t = new ArrayList<>();
        while (true) {
            int m = PlayGame(Won_Toys, my_t);
            if (m == 2) {
                break;
            }
        }


    }

    static public Toy addToy(String name, int num, int chance) {//созддание игрушки
        return new Toy(name, num, chance);
    }

    static public int PlayGame(ArrayList<Toy> Won_Toys, ArrayList<Toy> my_t) {//основная часть со всеми операторами
        System.out.println("Введите оперцию > (add, change, play, get, stop)");
        Scanner scan = new Scanner(System.in);
        String op = scan.next();
        int c = Toy.c;
        if (op.toLowerCase().equals("add")) {//добавление
            System.out.println("введите название игрушки ");
            String name = scan.next();
            int num = GetNum("введите колличество игрушек с этим названием");
            int chance = GetNum("введите шанс выпадения (1-100), учитывайте, что сумма шансов всех игрушек вместе должна быть ровна 100");
            my_t.add(addToy(name, num, chance));
            return 0;
        } else if (op.toLowerCase().equals("play")) {//розыгрыш игрушки
            Won_Toys.add(Toy.Game());
            if (Won_Toys.get(c) == null) {
                Won_Toys.remove(c);
                System.out.println("Вы не выйграли игрушку, или они закончились, можете попробовать ещё раз");
            } else {
                System.out.println("вы выйграли игрушку " + Won_Toys.get(c));
                Toy.c += 1;
            }
            return 0;
        } else if (op.toLowerCase().equals("change")) {
            System.out.println("введите имя игрушки, шанс которой хотите поменять");
            String n = scan.next();
            int o = 0;
            for (Toy i : my_t
            ) {
                if (n.equals(i.getName())) {
                    i.setChance(GetNum("Введите новый шанс"));
                    o = 1;
                    break;
                }
            }
            if (o > 0) {
                System.out.println("Изменено");

            } else {
                System.out.println("Нет такой игрушки");
            }

        } else if (op.equals("get")) {//получение игрушки и запись в файл

            GetToy(Won_Toys);
            return 0;
        } else if (op.equals("stop")) {//остановка программы
            System.out.println("Все неполученные игрушки не сохранятся");
            return 2;

        } else {//при неправильном операторе
            System.out.println("Incorrect operator");
            return 3;
        }
        return 0;

    }

    static public void GetToy(ArrayList<Toy> Won_Toys) {//Метод получения игрушки
        try (FileWriter writer = new FileWriter("WON_TOYS.txt", true)) {
            String text = "Получена игрушка " + Won_Toys.get(0).getName() + ";\n";
            Won_Toys.remove(0);
            Toy.c -= 1;
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {

            System.out.println("err");
        }
    }

    static public int GetNum(String msg) {//Получение валидного числа
        Scanner scan = new Scanner(System.in);
        System.out.println(msg);
        try {
            int y = scan.nextInt();
            return y;
        } catch (Exception err) {
            System.out.println("Введено не число");
            return GetNum(msg);
        }
    }

}
