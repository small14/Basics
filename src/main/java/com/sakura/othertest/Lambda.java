package com.sakura.othertest;

import com.sakura.entity.Name;
import sun.security.provider.MD5;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lambda {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.add(6);
//
//        list.forEach((integer) -> System.out.println(integer));

//        Thread old = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    for (int i = 0; i < 10; i++) {
//                        System.out.println("以前线程");
//                        Thread.sleep(1000);
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//
//            }
//        });
//
//        Thread now = new Thread(()-> System.out.println("现在线程") );
//        now.start();
//        old.start();


//        Clock clock = Clock.systemDefaultZone();
//        Instant instant = clock.instant();
//        Date legacyDate = Date.from(instant);
//        System.out.println(legacyDate.toString());
//        System.out.println();


//        try{
//            File file = new File("E:\\test\\test.txt");
//            File file2 = new File("E:\\test\\sakura.txt");
//            FileInputStream in = new FileInputStream(file);
//            FileOutputStream out = new FileOutputStream(file2);
//            FileChannel inChannel = in.getChannel();
//            FileChannel outChannel = out.getChannel();
//            ByteBuffer buffer = ByteBuffer.allocate(1);
//            int length = inChannel.read(buffer);
//            while ( inChannel.read(buffer) != -1){
//                inChannel.read(buffer);
//                buffer.flip();
//                outChannel.write(buffer);
//                buffer.clear();
//                inChannel.read(buffer);
//            }
//            inChannel.close();
//            in.close();
//            byte[] bytes = "Hello World!".toString().getBytes();
//            ByteBuffer buffer1 = ByteBuffer.allocate(bytes.length);
//
//            outChannel.write(buffer1.put(bytes));
//            buffer1.flip();
//            outChannel.write(buffer1);
//            out.close();
//
//
//
//
////            FileOutputStream out = new FileOutputStream(file);
////            FileChannel channel = out.getChannel();
////            String s = "你好啊,世界!";
////            byte[] bytes = s.getBytes();
////            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
////            buffer.put(bytes);
////            buffer.flip();
////            channel.write(buffer);
////            channel.close();
////            out.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }


        Name name = new Name();
        name.setName("dada");
        name.setID(456465);

        try{
          Method method = name.getClass().getMethod("getName");
          System.out.println( method.invoke(name));
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
