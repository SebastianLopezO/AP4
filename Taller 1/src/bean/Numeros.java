package bean;

import java.util.ArrayList;

public class Numeros {
    private ArrayList<Integer> Nums;

    public Numeros(ArrayList<Integer> nums) {
        Nums = nums;
    }

    public ArrayList<Integer> getNums() {
        return Nums;
    }

    public void setNums(ArrayList<Integer> nums) {
        Nums = nums;
    }
}
