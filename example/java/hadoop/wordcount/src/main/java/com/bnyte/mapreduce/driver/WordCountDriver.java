package com.bnyte.mapreduce.driver;

import com.bnyte.mapreduce.map.WordCountMap;
import com.bnyte.mapreduce.reducer.WordCountReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author bnyte
 * @since 2022/5/12 12:12
 */
public class WordCountDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        if (null == args) throw new RuntimeException("output file cannot be empty");
        List<String> argList = Arrays.asList(args);
        if (argList.size() < 2) {
            throw new RuntimeException("output file cannot be empty");
        }


        // 指定hadoop_home
//        System.setProperty("hadoop.home.dir", "D:\\software\\hadoop-3.1.0");
        // 加载hadoop.dll文件
//        System.load("D:\\software\\hadoop-3.1.0\\bin\\hadoop.dll");
        // 获取job实例对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);


        // 指定执行jar
        job.setJarByClass(WordCountDriver.class);

        // 设置mapper和reduce的jar路径
        job.setReducerClass(WordCountReduce.class);
        job.setMapperClass(WordCountMap.class);

        // 设置map阶段的key value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        // 指定输出到文件的key value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        // 指定输入文件和输出文件
//        FileInputFormat.setInputPaths(job, new Path("D:\\hadoop\\example\\input\\wordcount.txt"));
//        FileOutputFormat.setOutputPath(job, new Path("D:\\hadoop\\example\\output"));

        FileInputFormat.setInputPaths(job, new Path(argList.get(0)));
        FileOutputFormat.setOutputPath(job, new Path(argList.get(1)));
        // 提交job
        job.waitForCompletion(true); // 可以看到更详细的运行日志
        // 或者submit
//        job.submit();
    }

}
