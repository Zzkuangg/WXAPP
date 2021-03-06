package com.boyier.net;


import com.alibaba.fastjson.JSON;
import com.boyier.SpringBeanUtil;
import com.boyier.mapper.CondMapper;
import com.boyier.pojo.Cond;
import com.boyier.service.CondService;
import com.boyier.service.CondServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Logger;

@WebListener
public class UDPServer implements ServletContextListener {
    public static Logger logger = Logger.getLogger(UDPServer.class.getName());
    public static final int MAX_UDP_DATA_SIZE = 4096;
    public static final int UDP_PORT = 10086;
    public static DatagramPacket packet = null;
    public static DatagramSocket socket = null;


    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            logger.info("========启动一个线程，监听UDP数据报.PORT:" + UDP_PORT + "=========");
            // 启动一个线程，监听UDP数据报
            new Thread(new UDPProcess(UDP_PORT)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class UDPProcess implements Runnable {

        public UDPProcess(final int port) throws SocketException {
            //创建服务器端DatagramSocket，指定端口
            socket = new DatagramSocket(port);
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            logger.info("=======创建数据报，用于接收客户端发送的数据======");
            while (true) {
                byte[] buffer = new byte[MAX_UDP_DATA_SIZE];
                packet = new DatagramPacket(buffer, buffer.length);
                try {
                    logger.info("=======此方法在接收到数据报之前会一直阻塞======");
                    socket.receive(packet);
                    new Thread(new Process(packet)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    class Process implements Runnable {

        @Autowired
        CondService condService = SpringBeanUtil.getBean(CondServiceImpl.class);

        public Process(DatagramPacket packet) throws UnsupportedEncodingException {

            // TODO Auto-generated constructor stub
            logger.info("=======接收到的UDP信息======");
            byte[] buffer = packet.getData();// 接收到的UDP信息，然后解码
//            String srt1 = new String(buffer, "GBK").trim();
            //            logger.info("=======Process srt1 GBK======" + srt1);
            String srt2 = new String(buffer, "UTF-8").trim();
            logger.info("=======Process srt2 UTF-8======" + srt2);
            Cond cond = JSON.parseObject(srt2, Cond.class);
            System.out.println("--------------" + condService);
            logger.info("=======对象======" + cond);
            String res = condService.addCond(cond);

//            String srt3 = new String(buffer, "ISO-8859-1").trim();
//            logger.info("=======Process srt3 ISO-8859-1======" + srt3);
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            logger.info("====过程运行=====");
            try {
                logger.info("====向客户端响应数据=====");
                //1.定义客户端的地址、端口号、数据
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                byte[] data2 = "{'request':'alive','errcode':'0'}".getBytes();
                //2.创建数据报，包含响应的数据信息
                DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
                //3.响应客户端
                socket.send(packet2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("========UDPListener摧毁=========");
    }


}