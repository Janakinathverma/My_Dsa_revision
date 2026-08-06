class Best_time_to_Sell {
    public static void main(String[] args) {
        min_price=Integer.MAX_VALUE, max_profit=0;
        for(int price: prices){
            if (price < min_price)    min_price = price;
            else if (price - min_price > max_profit) max_profit = price - min_price;
        }
        return max_profit;
    }
}