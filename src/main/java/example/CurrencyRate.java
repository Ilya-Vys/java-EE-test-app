package example;

public class CurrencyRate {

    private final double japaneseYen;
    private final double usDollar;
    private final double poundSterling;
    private final double russianRouble;
    private final double canadianDollar;
    private final double chineseYuan;


    public CurrencyRate(double japaneseYen,
                        double usDollar,
                        double poundSterling,
                        double russianRouble,
                        double canadianDollar,
                        double chineseYuan) {
        this.japaneseYen = japaneseYen;
        this.usDollar = usDollar;
        this.poundSterling = poundSterling;
        this.russianRouble = russianRouble;
        this.canadianDollar = canadianDollar;
        this.chineseYuan = chineseYuan;
    }

    public double getJapaneseYen() {
        return japaneseYen;
    }

    public double getUsDollar() {
        return usDollar;
    }

    public double getPoundSterling() {
        return poundSterling;
    }

    public double getRussianRouble() {
        return russianRouble;
    }

    public double getCanadianDollar() {
        return canadianDollar;
    }

    public double getChineseYuan() {
        return chineseYuan;
    }

}
