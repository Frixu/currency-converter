package com.nbpconverter.repository;


public interface IRemoteRepository {
    /**
     * Pobiera zasób pod URL i zwraca bajty.
     */
    byte[] get(String url) throws Exception;
}

