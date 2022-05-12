package com.bnyte.mapreduce.map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * KEYIN: map阶段读取源数据(文件)偏移量
 * VALUEIN: map阶段读取源数据(文件)的数据
 * KEYOUT: 输出key
 * VALUEOUT: 输出值
 *
 * @author bnyte
 * @since 2022/5/11 13:44
 */
public class WordCountMap extends Mapper<LongWritable, Text, Text, LongWritable> {

    private static final Logger log = LoggerFactory.getLogger(WordCountMap.class);
    private static final Text writeString = new Text();
    private static final LongWritable writeLong = new LongWritable(1);

    /**
     * 重写map方法进行结果拼接
     * @param key 读取偏移量
     * @param value 读取到的值
     * @param context MapReduce全局应用上下文
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        log.info("{} map key for '{}' and value for '{}'", this.getClass().getSimpleName(), key.get(), value.toString());
        String inputValue = value.toString();
        String[] splitInputs = inputValue.split(" ");
        for (String splitInput : splitInputs) {
            writeString.set(splitInput);
            context.write(writeString, writeLong);
            log.info("{} write key for '{}' and write value for '{}'", this.getClass().getSimpleName(), writeString, writeLong);
        }
    }
}
