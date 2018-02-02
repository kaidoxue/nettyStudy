package study.netty.prog1;

import com.sun.org.apache.xpath.internal.SourceTree;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

public class HWServer {
    private int port;

    public HWServer(int port) {
        this.port = port;
    }

    public void start(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap().group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer());

        try {
            ChannelFuture future = server.bind(port).sync();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for(;;){
                String msg = reader.readLine();
                if(msg == null){
                    continue;
                }

                Map<String, SocketChannel> map = GatewayService.getChannels();
                Iterator<String> it = map.keySet().iterator();
                while(it.hasNext()){
                    String key = it.next();
                    SocketChannel obj = map.get(key);
//                    System.out.println("channel id is: " + key);
//                    System.out.println("channel: " + obj.isActive());
                    obj.writeAndFlush(msg);
                }

//                future.channel().writeAndFlush(msg + "\r\n");
            }
//            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        HWServer server = new HWServer(7788);
        server.start();
    }
}
