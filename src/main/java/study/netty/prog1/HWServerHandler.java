package study.netty.prog1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import study.netty.utils.StringReverseBySingleDot;

public class HWServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String id = ctx.channel().id().asLongText();
        GatewayService.addGatewayChannel(id,(SocketChannel)ctx.channel());
        System.out.println("a new connect come in: " + id);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String ret = msg.toString();
        ret = ret.replaceAll("\r|\n","");
        ret = StringReverseBySingleDot.reverse(ret);

        System.out.println(ctx.channel().remoteAddress()+"===>server: "+ret);
//        ctx.write("received your msg\r\n");
        ctx.write("ack:\t"+ret);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}