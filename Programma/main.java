//package Programma;

import Market.Market;
public class main {
    public static void main (String[] args) {
        Market market = new Market();
        int n = 10; //Количество добавляемых моделей игрушек в магазин
        int m = 10; //Количество проводимых розыгрышей(не должно привышать общее количество всех игрушек в магазине)
        for (int i=0;i<n;i++){
            market.addToy();
        }
        market.setProbability();//Задать конкретную вероятность для определенной модели игрушки(по умолчанию вероятность распределяется равномерно)
        for (int i=0;i<m;i++){
            market.drawToys();
        }
        market.issuance();//Выдача разыгранных игрушек(запись в файл) 
    }

}