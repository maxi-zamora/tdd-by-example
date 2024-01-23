package guru.springframework;



public class Money implements Expression {
    protected int amount;
    protected String currency;

    public Money(int amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }
    protected String currency(){
        return currency;
    }

    @Override
    public Expression times(int multiplier){
        return new Money(amount * multiplier, this.currency);
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount){
        return new Money(amount, "CHF");
    }

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount
                && this.currency == money.currency;
    }

    public Money reduce(Bank bank, String to){
     //int rate = (currency.equals("CHF") && to.equals("USD")) ? 2: 1;
     return new Money(amount / bank.rate(this.currency,to), to);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public Expression plus(Expression addend){
        return new Sum(this, addend);
    }
}
