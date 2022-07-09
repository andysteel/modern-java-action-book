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

import java.util.List;

import com.gmail.andersoninfonet.chapter02.models.Dish;

public class Filtering {
    
    public static void main(String[] args) {
        
        List<Dish> vegetarianMenu = Dishes.toList()
                                        .stream()
                                        .filter(Dish::isVegetarian)
                                        .toList();
        //[french fries, rice, season fruit, pizza]
        System.out.println(vegetarianMenu);

        List<Dish> evenCalories = Dishes.toList()
            .stream()
            .filter(d -> d.getCalories() % 2 == 0)
            .distinct()
            .toList();

        //[[calories=800, name=pork], [calories=400, name=chicken], [calories=350, name=rice]]
        System.out.println(evenCalories);
        
    }
}
