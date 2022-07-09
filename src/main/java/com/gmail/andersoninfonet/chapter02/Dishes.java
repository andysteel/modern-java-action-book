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

import java.util.ArrayList;
import java.util.List;

import com.gmail.andersoninfonet.chapter02.models.Dish;

public class Dishes {

    private Dishes() {}

    public static List<Dish> toList() {
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("pork", false, 800, Dish.Type.MEAT));
        dishes.add(new Dish("beef", false, 701, Dish.Type.MEAT));
        dishes.add(new Dish("chicken", false, 400, Dish.Type.MEAT));
        dishes.add(new Dish("french fries", true, 533, Dish.Type.OTHER));
        dishes.add(new Dish("rice", true, 350, Dish.Type.OTHER));
        dishes.add(new Dish("season fruit", true, 121, Dish.Type.OTHER));
        dishes.add(new Dish("pizza", true, 555, Dish.Type.OTHER));
        dishes.add(new Dish("prawns", false, 301, Dish.Type.FISH));
        dishes.add(new Dish("salmon", false, 400, Dish.Type.FISH));
        return dishes;
    }
}
