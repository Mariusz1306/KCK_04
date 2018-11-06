package sample;

public class Data {
    String month;
    Number salary;

    public Data(String month, int salary) {
        this.month = month;
        this.salary = salary;
    }

    public String getMonth() {
        return month;
    }

    public Number getSalary() {
        return salary;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
