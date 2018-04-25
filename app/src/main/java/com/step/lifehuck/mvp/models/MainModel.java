package com.step.lifehuck.mvp.models;

import android.util.Log;

import com.step.lifehuck.entities.Good;
import com.step.lifehuck.entities.LifeHuck;
import com.step.lifehuck.mvp.contracts.MainContract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mamedov on 02.04.2018.
 */

public class MainModel implements MainContract.model {
    @Override
    public Observable<List<LifeHuck>> getHucks() {
        return Observable.fromCallable(()->{
            List<LifeHuck> hucks = new ArrayList<>();
            try{
                Document doc = Jsoup.connect("https://lifehacker.ru/").get();
                Elements slots = doc.select(".stripe__slot");

                for(Element slot:slots){
                    try {
                        Element img = slot.selectFirst(".stripe-item__img-background");
                        String imgSrc = img.attr("data-src");
                        Element nameElem = slot.selectFirst(".stripe-item__title");
                        String name = nameElem.html();
                        Element viwsElem = slot.selectFirst(".views-counter");

                        Log.i("--LGSX--",slot.selectFirst(".ab-link").toString());
                        int views;
                        try {
                            views = Integer.parseInt(viwsElem.html().replace(" ", ""));
                        } catch (Exception e) {
                            views = 0;
                        }
                        hucks.add(new LifeHuck(name, imgSrc, views,""));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return hucks;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<List<Good>> getGoods() {
        return Observable.fromCallable(() -> {
            List<Good> goods = new LinkedList<>();
            try {
                Document doc = Jsoup.connect("http://price.ua/skidki-dnepropetrovsk-t2.html").get();
                Elements goodContainers = doc.select(".sale-l-i");
                for (Element goodContainer : goodContainers) {
                    String imgSrc = goodContainer.selectFirst(".sales-product-info-img-block img").attr("src");
                    String name = goodContainer.selectFirst(".sale-title a").html();
                    String time =goodContainer.selectFirst(".sales-time-left").text();
                    String desc = goodContainer.selectFirst(".sales-characteristics").text();
                    String firma =goodContainer.selectFirst(".firmname").text();

                    String price;

                    try {

                        price    = goodContainer.selectFirst(".old-price").childNode(0).toString().replace("&nbsp;","").trim();

                    }catch (Exception e){
                        price= "0";
                    }
                    String link = goodContainer.selectFirst(".active-go").attr("href");

                    String pracetwo;

                    try {

                        pracetwo    = goodContainer.selectFirst(".sales-product-price").childNode(0).toString().replace("&nbsp;","").trim();

                    }catch (Exception e){
                        pracetwo="0" ;
                    }





                    goods.add(new Good(imgSrc, name, Double.parseDouble(price), desc, link,time,Double.parseDouble(pracetwo),firma));
                }
            } catch (Exception e) {
                Log.e("__","ERROR",e);
            }
            return goods;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
