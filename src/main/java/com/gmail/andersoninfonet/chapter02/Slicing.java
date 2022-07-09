/*-
 * =====LICENSE-START=====
 * Java 17 Application
 * ------
 * Copyright (C) 2022 Anderson Dias
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */
package com.gmail.andersoninfonet.chapter02;

import java.util.Comparator;
import java.util.List;

import com.gmail.andersoninfonet.chapter02.models.Dish;
import com.gmail.andersoninfonet.chapter02.models.Dish.Type;

public class Slicing {
    
    public static void main(String[] args) {
        //need to be ordened first
        List<Dish> sorted = Dishes.toList();
        sorted.sort(Comparator.comparingInt(Dish::getCalories));

        //[[calories=121, name=season fruit], [calories=301, name=prawns]]
        List<Dish> taked = sorted
                                .stream()
                                .takeWhile(d -> d.getCalories() < 320)
                                .toList();

        System.out.println(taked);

        //[[calories=350, name=rice], [calories=400, name=chicken], [calories=400, name=salmon], [calories=533, name=french fries], [calories=555, name=pizza], [calories=701, name=beef], [calories=800, name=pork]]
        List<Dish> droped = sorted
                                .stream()
                                .dropWhile(d -> d.getCalories() < 320)
                                .toList();

        System.out.println(droped);

        //[[calories=350, name=rice], [calories=400, name=chicken]]
        List<Dish> limited = sorted
                                .stream()
                                .dropWhile(d -> d.getCalories() < 320)
                                .limit(2)
                                .toList();

        System.out.println(limited);

        //[[calories=400, name=salmon], [calories=533, name=french fries], [calories=555, name=pizza], [calories=701, name=beef], [calories=800, name=pork]]
        List<Dish> skiped = sorted
                                .stream()
                                .dropWhile(d -> d.getCalories() < 320)
                                .skip(2)
                                .toList();

        System.out.println(skiped);

        //[[calories=400, name=chicken], [calories=701, name=beef]]
        List<Dish> meats = sorted.stream()
            .filter(d -> d.getType() == Type.MEAT)
            .limit(2)
            .toList();

        System.out.println(meats);
    }
}
