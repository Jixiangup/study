package com.bnyte.flow.serializ;

import org.apache.hadoop.io.Writable;

/**
 * @author bnyte
 * @since 2022/5/13 13:26
 */
public interface HadoopSerializable<T> extends Comparable<T>, Writable {
}
