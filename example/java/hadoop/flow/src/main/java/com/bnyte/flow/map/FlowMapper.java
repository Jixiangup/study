package com.bnyte.flow.map;

import com.bnyte.flow.entity.Flow;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author bnyte
 * @since 2022/5/13 13:23
 */
public class FlowMapper extends Mapper<LongWritable, Text, Text, Flow> {

    private static final Flow flow = new Flow();
    private static final Text outputKey = new Text();

    /**
     * 基于数据样式：
     *  1	13736230513	192.196.100.1	www.atguigu.com	2481	24681	200
     *  2	13846544121	192.196.100.2			264	0	200
     * 转换为:
     *  13736230513	2481 24681 sum(2481+24681)
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String sourceData = value.toString();

        if (StringUtils.isBlank(sourceData)) throw new RuntimeException("source data cannot be empty");

        List<String> sourceDataSplits = Arrays.asList(sourceData.split("\t"));
        String phoneNo = sourceDataSplits.get(1);
        Collections.reverse(sourceDataSplits);
        String upstream = sourceDataSplits.get(2);
        String downlink = sourceDataSplits.get(1);

        flow.setUpstream(Long.parseLong(upstream));
        flow.setDownlink(Long.parseLong(downlink));
        flow.setTotal();

        outputKey.set(phoneNo);
        context.write(outputKey, flow);
    }
}
