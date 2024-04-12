package bean;

public class Numeros {
    private Integer n1, n2, n3;

    public Numeros(int num1, int num2, int num3) {
        n1 = num1;
        n2 = num2;
        n3 = num3;
    }

    public Numeros() {
        n1 = null;
        n2 = null;
        n3 = null;
    }

    public Numeros(int[] nums) {
        if (nums.length >= 3) {
            n1 = nums[0];
            n2 = nums[1];
            n3 = nums[2];
        } else {
            System.out.println("no contiene suficientes numeros, son nullos");
            n1 = null;
            n2 = null;
            n3 = null;
        }

    }

    // getters and setters

    public Integer getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public Integer getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public Integer getN3() {
        return n3;
    }

    public void setN3(int n3) {
        this.n3 = n3;
    }
}
