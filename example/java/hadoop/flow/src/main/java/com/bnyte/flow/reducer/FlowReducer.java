package com.bnyte.flow.reducer;

import com.bnyte.flow.entity.Flow;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author bnyte
 * @since 2022/5/13 13:28
 */
public class FlowReducer extends Reducer<Text, Flow, Text, Text> {

    private static final Flow outputValue = new Flow();
    private static final Text outputString = new Text();

    @Override
    protected void reduce(Text key, Iterable<Flow> values, Context context) throws IOException, InterruptedException {

        Long upstreamTotal = 0L;
        Long downlinkTotal = 0L;
        for (Flow value : values) {
            upstreamTotal += value.getUpstream();
            downlinkTotal += value.getDownlink();
        }

        outputValue.initialization(upstreamTotal, downlinkTotal);
        outputString.set(outputValue.toString());

        context.write(key, outputString);
    }
}
