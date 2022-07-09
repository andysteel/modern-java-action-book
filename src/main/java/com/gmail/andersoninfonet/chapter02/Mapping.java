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

import java.util.Arrays;
import java.util.List;

public class Mapping {
    
    public static void main(String[] args) {
        Integer[] numbers = {1,2,3,4,5};

        //[1, 4, 9, 16, 25]
        List<Integer> squares = Arrays.stream(numbers)
            .map(n -> n * n)
            .toList();

        System.out.println(squares);

        List<Integer> nums1 = Arrays.asList(1,2,3);
        List<Integer> nums2 = Arrays.asList(3,4);

        List<int[]> pairs = nums1.stream()
            .flatMap(i -> nums2.stream()
                            .map(j -> new int[]{i, j}))
            .toList();

        //(1,3)(1,4)(2,3)(2,4)(3,3)(3,4)
        pairs.forEach(p -> System.out.println("("+p[0]+","+p[1]+")"));

        System.out.println("pairs 2 ....");

        List<int[]> pairs2 = nums1.stream()
        .flatMap(i -> nums2.stream()
                        .filter(j -> (i+j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
        .toList();

        //(2,4)(3,3)
        pairs2.forEach(p -> System.out.println("("+p[0]+","+p[1]+")"));


        //[H, e, l, o, w, r, d]
        List<String> words = Arrays.asList("Hello", "world");
        List<String> uniqueCharacters =
                                        words.stream()
                                        .map(word -> word.split(""))
                                        .flatMap(Arrays::stream)
                                        .distinct()
                                        .toList();

        System.out.println(uniqueCharacters);
    }
}
