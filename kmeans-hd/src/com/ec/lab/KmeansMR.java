package com.ec.lab;


import java.util.*;
import java.io.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapred.Reducer;

 @SuppressWarnings("deprecation")
 public class KmeansMR {
     public static String OUT = "outfile";
     public static String IN = "inputlarger";
     public static String CENTROID_FILE_NAME = "/centroidrun1.txt";
     public static String OUTPUT_FILE_NAME = "/part-00000";
     public static String DATA_FILE_NAME = "/voterun1.txt";
     public static String JOB_NAME = "KMeans";
     public static String SPLITTER = "\t| ";
     
     public static class Map extends MapReduceBase implements
             Mapper<Object, Text, IntWritable, Text> {
    	 public static List<String[]> mCenters;
    	 
         @Override
         public void configure(JobConf job) {
             try {
                 // Fetch the file from Distributed Cache Read it and store the
                 // centroid in the ArrayList
                 Path[] cacheFiles = DistributedCache.getLocalCacheFiles(job);
                 if (cacheFiles != null && cacheFiles.length > 0) {
                	 
                	 mCenters = readCentroids(cacheFiles[0].toString());
                 }
             } catch (IOException e) {
                 System.err.println("Exception reading DistribtuedCache: " + e);
             }
         }

         @Override
         public void map(Object key, Text value,
                 OutputCollector<IntWritable, Text> output,
                 Reporter reporter) throws IOException {
        	 
        	 String[] xy = value.toString().split(",");
        	 double[] res = new double[xy.length];
        	 int i = 0;
        	 for(String val : xy){
        		 if(i<xy.length-1){
        			 res[i] = Double.parseDouble(val);
            		 i++; 
        		 }
        	 }
             int index = 0;
        	 double minDistance = Double.MAX_VALUE;
             for (int ind = 0; ind < mCenters.size(); ind++) {
                 double distance = calculateDistance(Double.parseDouble(mCenters.get(ind)[0]), Double.parseDouble(mCenters.get(ind)[1]), Double.parseDouble(mCenters.get(ind)[2]), Double.parseDouble(mCenters.get(ind)[3]), Double.parseDouble(mCenters.get(ind)[4]), Double.parseDouble(mCenters.get(ind)[5]), Double.parseDouble(mCenters.get(ind)[6]), Double.parseDouble(mCenters.get(ind)[7]), Double.parseDouble(mCenters.get(ind)[8]), Double.parseDouble(mCenters.get(ind)[9]), Double.parseDouble(mCenters.get(ind)[10]), Double.parseDouble(mCenters.get(ind)[11]), Double.parseDouble(mCenters.get(ind)[12]), Double.parseDouble(mCenters.get(ind)[13]), Double.parseDouble(mCenters.get(ind)[14]), Double.parseDouble(mCenters.get(ind)[15]), res[0], res[1], res[2], res[3], res[4], res[5], res[6], res[7], res[8], res[9], res[10], res[11], res[12], res[13], res[14], res[15]);
                 if (distance < minDistance) {
                     index = ind;
                     minDistance = distance;
                 }
             }                     
             // Emit the nearest center and the point
             output.collect(new IntWritable(index), value);
         }
         
         public static List<String[]> readCentroids(String filename) throws IOException {
             FileInputStream fileInputStream = new FileInputStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
             return  readData(reader);
         }
         
         private static List<String[]> readData(BufferedReader reader) throws IOException {
             List<String[]> centroids = new ArrayList<>();
             String line;
             try {
                 while ((line = reader.readLine()) != null) {
                     String[] values = line.split("\t");
                     String[] temp = values[0].split(",");
                     String[] centroid = new String[temp.length];
                     for(int i=0;i<temp.length;i++)
                    	 centroid[i] = temp[i];

                     centroids.add(centroid);
                 }
             }
             finally {
                 reader.close();
             }
             return centroids;
         }
         
         public static double calculateDistance(double a1, double b1, double c1, double d1, double e1, double f1, double g1, double h1, double i1, double j1, double k1, double l1, double m1, double n1, double o1, double p1, double a2, double b2, double c2, double d2, double e2, double f2, double g2, double h2, double i2, double j2, double k2, double l2, double m2, double n2, double o2, double p2) {
             return Math.sqrt(Math.pow(a1 - a2, 2) + Math.pow(b1 - b2, 2) + Math.pow(c1 - c2, 2) + Math.pow(d1 - d2, 2) + Math.pow(e1 - e2, 2) + Math.pow(f1 - f2, 2) + Math.pow(g1 - g2, 2) + Math.pow(h1 - h2, 2) + Math.pow(i1 - i2, 2) + Math.pow(j1 - j2, 2) + Math.pow(k1 - k2, 2) + Math.pow(l1 - l2, 2) + Math.pow(m1 - m2, 2) + Math.pow(n1 - n2, 2) + Math.pow(o1 - o2, 2) + Math.pow(p1 - p2, 2) );
         }
     }

     public static class Reduce extends MapReduceBase implements
             Reducer<IntWritable, Text, Text, IntWritable> {

         @Override
         public void reduce(IntWritable key, Iterator<Text> values,
                 OutputCollector<Text, IntWritable> output, Reporter reporter)
                 throws IOException {
             
        	 Double[] democrat = new Double[16]; 
        	 int democrat_counter = 0;
        	 for(int i=0; i<16 ; i++)
        		 democrat[i] = 0d;          
             
             Double[] republic = new Double[16];        	
             int republic_counter = 0;
             for(int i=0; i<16; i++)
            	 republic[i] = 0d;            
                          
             String party ="";
        	 
             while (values.hasNext()) {           	 
            	 String[] temp = values.next().toString().split(",");            	 
            	 party = temp[16];
            	 if(temp[16].equals("democrat")){
            		 for(int i=0; i<16; i++)
            			 democrat[i] += Double.parseDouble(temp[i]);
                	 
                	 democrat_counter ++;
            	 }else{
            		 for(int i=0; i<16; i++)
            			 republic[i] += Double.parseDouble(temp[i]);

                	 republic_counter++;
            	 }
             }
        	 
             String centroid = "";
             // We have new center now
             if(party.equals("democrat")){
            	 for(int i=0; i<16; i++)
            		 democrat[i] = democrat[i] / democrat_counter;
            	 
                 centroid = democrat[0] + "," + democrat[1] + "," + democrat[2] + "," + democrat[3] + "," + democrat[4] + "," + democrat[5] + "," + democrat[6] + "," + democrat[7] + "," + democrat[8] + "," + democrat[9] + "," + democrat[10] + "," + democrat[11] + "," + democrat[12] + "," + democrat[13] + "," + democrat[14] + "," + democrat[15] + "," + party  ;
             }else{
            	 for(int i=0; i<16; i++)
            		 republic[i] = republic[i] / republic_counter;
            	
                 centroid = republic[0] + "," + republic[1] + "," + republic[2] + "," + republic[3] + "," + republic[4] + "," + republic[5] + "," + republic[6] + "," + republic[7] + "," + republic[8] + "," + republic[9] + "," + republic[10] + "," + republic[11] + "," + republic[12] + "," + republic[13] + "," + republic[14] + "," + republic[15] + "," + party  ;
              }        
             // Emit new center and point
             output.collect(new Text(centroid), key);
         }
     }

     public static void main(String[] args) throws Exception {
         run(args);
     }

     public static void run(String[] args) throws Exception {
         IN = args[0];
         OUT = args[1];
         String input = IN;
         String output = OUT + System.nanoTime();
         //String output = OUT;
         String again_input = output;

         // Reiterating till the convergence
         int iteration = 0;
         boolean hasConverged = false;
         
         
         while (hasConverged == false) {
             JobConf conf = new JobConf(KmeansMR.class);
             if (iteration == 0) {
                 Path hdfsPath = new Path(input + CENTROID_FILE_NAME);
                 // upload the file to hdfs. Overwrite any existing copy.
                 DistributedCache.addCacheFile(hdfsPath.toUri(), conf);
             } else {
                 Path hdfsPath = new Path(again_input + OUTPUT_FILE_NAME);
                 // upload the file to hdfs. Overwrite any existing copy.
                 DistributedCache.addCacheFile(hdfsPath.toUri(), conf);
             }

             conf.setJobName(JOB_NAME);
             conf.setMapOutputKeyClass(IntWritable.class);
             conf.setMapOutputValueClass(Text.class);
             conf.setOutputKeyClass(Text.class);
             conf.setOutputValueClass(IntWritable.class);
             conf.setMapperClass(Map.class);
             conf.setReducerClass(Reduce.class);
             conf.setInputFormat(TextInputFormat.class);
             conf.setOutputFormat(TextOutputFormat.class);

             FileInputFormat.setInputPaths(conf,
                     new Path(input + DATA_FILE_NAME));
             FileOutputFormat.setOutputPath(conf, new Path(output));

             JobClient.runJob(conf);

             Path ofile = new Path(output + OUTPUT_FILE_NAME);
             FileSystem fs = FileSystem.get(new Configuration());
             BufferedReader br = new BufferedReader(new InputStreamReader(
                     fs.open(ofile)));
             
             
             StringBuilder content = new StringBuilder();
            // List<Double> centers_next = new ArrayList<Double>();
             String line = br.readLine();
             while (line != null) {
            	 content.append(line).append("\n");
                 line = br.readLine();
             }
             br.close();
             
             String newCentroids = content.toString();
             
             String prev;
             if (iteration == 0) {
                 prev = input + CENTROID_FILE_NAME;
             } else {
                 prev = again_input + OUTPUT_FILE_NAME;
             }
             Path prevfile = new Path(prev);
             FileSystem fs1 = FileSystem.get(new Configuration());
             BufferedReader br1 = new BufferedReader(new InputStreamReader(
                     fs1.open(prevfile)));
             StringBuilder content1 = new StringBuilder();
             String l = br1.readLine();
             while (l != null) {
            	 content1.append(l).append("\n");
                 l = br1.readLine();
             }
             br1.close();            
             String prevCentroids = content1.toString();             
             if (prevCentroids.equals(newCentroids)) {
                 // it means that the iteration is finished
                 hasConverged = true;
             }

            // prevCentroids = newCentroids;
             ++iteration;
             again_input = output;
             output = OUT + System.nanoTime();
             //output = OUT;
         }
     }
 }