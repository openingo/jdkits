package org.openingo.jdkits;

/**
 * INumberTypeCast
 *
 * @author Qicz
 */
public interface INumberTypeCast<T> {

     Integer toInteger(T obj);
    Long toLong(T obj);
    Double toDouble(T obj);
    Float toFloat(T obj);
}
