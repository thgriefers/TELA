package com.aun.tela.alphabets.application.generic;

/**
 * Created by Joseph Dalughut on 29/12/15 at 10:45 PM.
 * Project name : Alphabets.
 * Copyright (c) 2015 Meengle. All rights reserved.
 */

/**
 * A simple generic Consumer interface which returns a data type by consuming another
 * @param <T> the data type to be returned
 * @param <V> the data type to be consumed
 */
public interface Consumer<T, V> {
    T consume(V v);
}
