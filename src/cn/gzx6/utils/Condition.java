package cn.gzx6.utils;

public class Condition {

    private int foodTypeId;
    private String foodName;

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public String toString() {
        return "[foodName=" + foodName + ", foodType_id=" + foodTypeId + "]";
    }
}
