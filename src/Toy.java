import java.util.ArrayList;

public class Toy {
    //поля класса
    private int id;
    private String name;
    private int number;
    private int chance;

    public static int getCount() {
        return count;
    }

    static int count = 1;
    static int c = 0;
    static ArrayList<Toy> toys = new ArrayList<>();
    public ArrayList<Integer> won_numbers = new ArrayList<Integer>();


    ///////////////////////////////////////////////////////Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }
///////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
    Toy(String name, int num, int chance) {//сборщик игрушки
        this.id = count;
        count += 1;
        this.name = name;
        this.number = num;
        this.chance = chance;

        for (int i = 1; i <= getChance(); i++) {
            this.won_numbers.add(getCount());
            count += 1;
        }
        toys.add(this);
        int sum_of_chancec = 0;
        for (Toy i : this.toys) {
            sum_of_chancec += i.chance;

        }
        if (sum_of_chancec > 100) {
            System.out.println("InvalidChancec");
        } else if (sum_of_chancec == 100) {
            System.out.println("There are enough toys");

        } else System.out.println("NotEnoughToys");

    }
/////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////методы
    @Override
    public String toString() {
        return this.name;
    }

    static Toy Game() {//алгоритм розыгрыша игрушки, с уменьшением количества
        double i = 1 + Math.random() * 101;
        int a = ((int) i);

        for (Toy m : toys) {
            if (m.getNumber() == 0) {
                double n = 1 + Math.random() * 101;
                a = ((int) n);
            } else {
                for (int j : m.won_numbers) {

                    if (j == a) {
                        Toy won_o = m;
                        int y = m.getNumber() - 1;
                        m.setNumber(y);
                        return won_o;
                    }
                }
            }
        }
        return null;
    }
}



