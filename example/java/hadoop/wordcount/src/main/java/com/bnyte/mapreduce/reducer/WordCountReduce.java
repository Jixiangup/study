package com.bnyte.mapreduce.reducer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * KEYIN: 读取到的map阶段返回的key
 * VALUEIN: 读取到的map阶段返回的value
 * KEYOUT: 输出的key
 * VALUEOUT: 输出的value
 * @author bnyte
 * @since 2022/5/11 13:54ni
 */
public class WordCountReduce extends Reducer<Text, LongWritable, Text, LongWritable> {

    private static final Logger log = LoggerFactory.getLogger(WordCountReduce.class);
    private static final Text writeString = new Text();
    private static final LongWritable writeLong = new LongWritable();

    /**
     * reduce计算阶段执行的业务方法, 每个相同的key只会被执行一次
     * @param key map阶段返回的key 需要注意的是map阶段如果返回了多个相同的key那么会将这些相同key的value放到 values中，
     * @param values 每个key的value集合
     * @param context MapReduce全局上下文对象
     */
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        log.info("{} reduce key for '{}' and value for '{}'", this.getClass().getSimpleName(), key.toString(), values);
        long sum = 0L;
        for (LongWritable value : values) {
            sum += value.get();
        }
        writeLong.set(sum);
        context.write(key, writeLong);
        log.info("{} reduce write key for '{}' and write value for '{}'", this.getClass().getSimpleName(), key.toString(), writeLong);
    }
}
