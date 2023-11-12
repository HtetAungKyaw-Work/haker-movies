package com.haker.hakermovies

interface Mapper<Input, Output> {
    fun map(input: Input): Output
}
