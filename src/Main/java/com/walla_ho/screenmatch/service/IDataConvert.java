package com.walla_ho.screenmatch.service;

public interface IDataConvert {

    <T> T consumeData (String json, Class<T> classe );
}
