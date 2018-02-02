package study.netty.prog1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class HWClient {
    private  int port;
    private  String address;

    private static int numTest = 10;

    public HWClient(int port, String address) {
        this.port = port;
        this.address = address;
    }

    public void start(){
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer());

        try {
            ChannelFuture future = bootstrap.connect(address,port).sync();
//            future.channel().writeAndFlush("Hello Netty Server ,I am a common client");
//            future.channel().closeFuture().sync();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for(;;){
                String msg = reader.readLine();
                if(msg == null){
                    continue;
                }
//                future.channel().writeAndFlush(msg + "\r\n");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<20;i++){
                            String toSendStr = String.valueOf(numTest)+"."+msg;
                            numTest++;
                            System.out.println(toSendStr);
                            future.channel().writeAndFlush(toSendStr + "\r\n");
                        }
                    }
                }).start();


//                int iCount=20;
//                for(int i=0;i<iCount;i++){
//                    Thread t = new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            String toSendStr = String.valueOf(numTest)+"."+msg;
//                            numTest++;
//                            System.out.println(toSendStr);
//                            future.channel().writeAndFlush(toSendStr + "\r\n");
//                        }
//                    });
//                    t.start();
//
//                }




            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        HWClient client = new HWClient(7788,"127.0.0.1");
        client.start();
    }
}
