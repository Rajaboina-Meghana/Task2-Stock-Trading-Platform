import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class StockTradingPlatform{
    static class Stock{
        String symbol;
        String name;
        double price;
        double change;
        Stock(String symbol,String name,double price){
            this.symbol=symbol;
            this.name=name;
            this.price=price;
            this.change= 0.0;

        }
        void updatePrice(double newPrice){
            this.change=newPrice-this.price;
            this.price=newPrice;
        }

    }
    static class Portfolio{
        Map<String,Integer> holdings;
        double cash;
        List<String>transactionHistory;
        Portfolio(double initialCash){
            this.holdings=new HashMap<>();
            this.cash=initialCash;
            this.transactionHistory=new ArrayList<>();

        }
        void buyStock(Stock stock,int quantity){
            double cost=stock.price*quantity;
            if(cost<=cash){
                holdings.put(stock.symbol,holdings.getOrDefault(stock.symbol,0)+quantity);
                cash-=cost;
                transactionHistory.add("Bought" +quantity+"shares of"+stock.symbol+"at $"+stock.price);

            }
            else{
                System.out.println("Insufficient funds to buy"+quantity+"shares of"+stock.symbol);
            }
        }
        void sellStock (Stock stock,int quantity){
            int currentQuantity=holdings.getOrDefault(stock.symbol,0);
            if (currentQuantity>=quantity) {
                holdings.put(stock.symbol,currentQuantity-quantity);
                if(holdings.get(stock.symbol)==0){
                    holdings.remove(stock.symbol);
                }   
                double proceeds=stock.price* quantity;
                cash+=proceeds;
                transactionHistory.add("Sold"+quantity+"shares of"+stock.symbol+"at $"+stock.price);        
            }
            else{
                System.out.println("Not enough shares to sell"+quantity+"of"+stock.symbol);          }
        }
    }
    double getPortfolioValue(Map<String,Stock> market){
double value=cash;
for (Map.Entry<String,Integer> entry:holdings.entrySet()){
    value +=market.get(entry.getKey()).price*entry.getValue();
}
return value;
    }
    void displayPortfolio(Map<String, stock>market){
        System.out.println("\nPortfolio Summary:");
        System.out.println("Cash:$"+String.format("%.2f",cash));
        System.out.println("Holdo=ings:");
        for(Map.Entry<String,Integer>entry:holdings.entrySet()){
            Stock stock=market.get(entry.getkey());
            System.out.println(stock.symbol +"("+stock.name+"):"+entry.getValue()+"shares@$"+String.format("%2.f",stock.price));

        }
    System.out.println("Total Portfolio Value:$"+String.format("%.2f",getPortfolioValue(market)));
    System.out.println("\n Transaction History:");
    for(String transaction: transactionHistory){
System.out.println("transaction");
    }
    }
} 
private Map<String,Stock>market;
private Portfolio portfolio;
private Scanner scanner;

public Stock TradingPlatform(){
market = new HashMap<>();
portfolio = new Portfolio(10000.0);
// Starting with$10,000cash
scanner=new Scanner(System.in);
initializeMarket();
}
private void initializeMarket(){
    market.put("AAPL",new Stock("AAPL","Apple Inc.",190.25));
    market.put("GOOGL",new Stock("GOOGL", "Alphabet inc.",175.50));
    market.put("MSFT",new Stock("MSFT", "Microsoft Corporation",420.75));
    market.put("AMZN",new Stock("AMZN","Amazon.com Inc.", 180.30));
}
private void displayMarketData(){
    System.out.println("\nMarket Data:");
    System.out.println("Symbol\tName\t\t\tPrice\tChange");
    System.out.println("----------------------------------------------");
    for(Stock stock:market.values()){
        System.out.printf("%s\t%s\t$%.2f\t$%.2f%n",
        stock.symbol,
        String.format("%-20s",stock.name),
        stock.price,stock.change);    }
}

private void updateMarketPrices(){
    for(Stock stock:market.values()){
        double change=(Math.random()* 10)-5;
        stock.updatePrice(stock.price+change);
    }
}
private void buyStock(){
    System.out.print("Enter stock symbol to buy");
String symbol=
 scanner.nextLine().toUpperCase();
if(!market.containsKey(symbol)){
    System.out.println("Invalid stock symbol!");
    return;
}
System.out.print("Enter quantity to buy:");
int quantity;
try{
    quantity=
    Integer.parseInt(scanner.nextLine());
    if(quantity<=0){
        System.out.println("Quantity must be positive!");
        return;
    }
}
catch(NumberFormatException e){
    System.out.println("Invakid quantity!");
    return;
}
portfolio.buyStock(market.get(symbol),quantity);
}
private void sellStock(){ 
 System.out.print("Enter  stock sumbol to sell:");
 String symbol=
 scanner.nextLine().toUpperCase();
 if(!market.containsKey(symbol)){
    System.out.println("Invalid stock symbol!");
    return;
 }
 System.out.print("Enter quantity to sell:");
 int quantity;
 try{
    quantity=Integer.parseInt(scanner.nextLine());
    if(quantity<=0){
        System.out.println("Quantity must be positive!");
        return;
    }
  }catch(NumberFormatException e){
    System.out.println("Invalid quantity!");
    return;
  }
  portfolio.sellStock(market.get(symbol),quantity);
}
public void run(){
while (true){
    updateMarketPrice();
    System.out.println("\nStock Trading Platform");
System.out.println("1.View Market Data");
System.out.println("2.View Portfolio");
System.out.println("3.Buy Stock");
System.out.println("4.Sell Stock");
System.out.println("5.Exit");
System.out.print("Choose an option:");
String choice=scanner.nextLine();
switch(choice){
    case"1":
    displayMarketData();
    break;
    case"2":
    portfolio.displayPortfolio(market);
    break;
    case"3":
    buyStock();
    break;
    case"4":
    sellStock();
    break;
    case"5":
    System.out.println("Exiting platform....");
    scanner.close();
    default:
    System.out.println("Invalid option! please try again.");
}
}
}
public static void main(String[] args){
    StockTradingPlatform platform=new StockTradingPlatform();
    platform.run();
}
