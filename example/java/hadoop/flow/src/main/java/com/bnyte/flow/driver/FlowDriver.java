package com.bnyte.flow.driver;

import com.bnyte.flow.entity.Flow;
import com.bnyte.flow.map.FlowMapper;
import com.bnyte.flow.reducer.FlowReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author bnyte
 * @since 2022/5/13 13:21
 */
public class FlowDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        // 指定hadoop_home
        System.setProperty("hadoop.home.dir", "D:\\software\\hadoop-3.1.0");
        // 加载hadoop.dll文件
//        System.load("D:\\software\\hadoop-3.1.0\\bin\\hadoop.dll");
        // 配置job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 启动jar
        job.setJarByClass(FlowDriver.class);

        // mapper 和 reduce的类路径
        job.setReducerClass(FlowReducer.class);
        job.setMapperClass(FlowMapper.class);

        // mapper阶段的输出key value类型
        job.setMapOutputValueClass(Flow.class);
        job.setMapOutputKeyClass(Text.class);

        // 输入输出文件
        FileInputFormat.setInputPaths(job, new Path("D:\\hadoop\\example\\input\\phone_data.txt"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\hadoop\\example\\output\\0"));


        // 输出key value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // 提交任务
        job.waitForCompletion(true);
    }

}
